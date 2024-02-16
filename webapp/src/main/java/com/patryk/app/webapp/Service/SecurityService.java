package com.patryk.app.webapp.Service;

import com.patryk.app.webapp.Model.User;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
@AllArgsConstructor
public class SecurityService {
    public void authenticate(Authentication authentication, Model model) throws NullPointerException {
        boolean isAuthenticated = authentication != null && authentication.isAuthenticated();
        if(isAuthenticated) {
            long sessionUserId = ((User)authentication.getPrincipal()).getId();
            model.addAttribute("sessionUserId", sessionUserId);
        }
        model.addAttribute("isAuthenticated", isAuthenticated);
    }
}
