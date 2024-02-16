package com.patryk.app.webapp.Service;

import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.InvalidAccessTokenException;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.FileMetadata;
import com.dropbox.core.v2.files.Metadata;
import com.dropbox.core.v2.files.WriteMode;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;

@Service
public class DropboxCommunicationService {
    private static String ACCESS_TOKEN = "sl.BtFTXpmy4y1l1knSN9DrclF4yRPz7VS6JoZtAQGRC5KDiX_n2mzDy_8gPLkj1OdCpvuGMG7HbWT29J7rqwydwKU0rTkhZsbiWExihPqQWJ8i_yQjeqOw3WenrZnfrysLAUB_W7jmnqrhmb4";
    DbxRequestConfig config = DbxRequestConfig.newBuilder("dropbox/java-tutorial").build();

    private final String clientId = "pgur7x6tw12qoh1";
    private final String clientSecret = "n3vbftnk5sfemym";
    private final String tokenUrl = "https://api.dropbox.com/oauth2/token";
    private final String refreshToken = "-c_nmGpq9Y8AAAAAAAAAAfnh1lmXjfCakCuuVq9aa4PHPwHSjY2iMp95cDjG0Vgz";


    public String saveImage(MultipartFile image, String path) throws IOException, DbxException {
        String imageSharedLink = "";
        DbxClientV2 client = new DbxClientV2(config, ACCESS_TOKEN);

        try {
            Metadata uploadedFile = uploadImage(client, image, path);
            imageSharedLink = getImageShareUrl(client, uploadedFile);
        } catch(InvalidAccessTokenException e) {
            ACCESS_TOKEN = generateNewAccessToken();
            client = new DbxClientV2(config, ACCESS_TOKEN);
            Metadata uploadedFile = uploadImage(client, image, path);
            imageSharedLink = getImageShareUrl(client, uploadedFile);
        }
        return imageSharedLink;
    }

    public FileMetadata uploadImage(DbxClientV2 client, MultipartFile image, String path) throws IOException, DbxException {
        return client.files().uploadBuilder(path)
                .withMode(WriteMode.ADD)
                .uploadAndFinish(image.getInputStream());
    }

    private String getImageShareUrl(DbxClientV2 client, Metadata image) throws DbxException {
        String sharedLink = client.sharing().createSharedLinkWithSettings(image.getPathDisplay()).getUrl();
        sharedLink = UriComponentsBuilder.fromUriString(sharedLink)
                .queryParam("dl", 1)
                .build()
                .toString();
        return sharedLink;
    }

    public String generateNewAccessToken() throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("grant_type", "refresh_token");
        map.add("refresh_token", refreshToken);
        map.add("client_id", clientId);
        map.add("client_secret", clientSecret);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, httpHeaders);
        ResponseEntity<String> response = restTemplate.postForEntity(tokenUrl, request, String.class);
        ObjectMapper objectMapper = new ObjectMapper();

        if (response.getStatusCode().is2xxSuccessful()) {
            String newAccessToken = response.getBody();
            JsonNode jsonNode = objectMapper.readTree(newAccessToken);
            String accessToken = jsonNode.get("access_token").asText();
            return accessToken;
        } else {
            return null;
        }
    }
}
