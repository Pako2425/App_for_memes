CREATE TABLE users (
  id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(255),
  email VARCHAR(255),
  password VARCHAR(255),
  user_role VARCHAR(255),
  blocked BIT(1),
  enabled BIT(1)
);
INSERT INTO users (name, email, password, user_role, blocked, enabled)
VALUES ('Pako2425', 'patkoc11@interia.pl', '$2a$10$3/lvhHwYGHpE8FpDIKNgBefoXTAHoeJ8h0LlxFYH/P29iaUzvMv1S', 'ROLE_USER', 0, 1);
INSERT INTO users (name, email, password, user_role, blocked, enabled)
VALUES ('admin', 'admin@g.com', '$2a$10$tXMTbxksxuLmHNoGfrk0eepBfNcPiJUoyx/SX86JKcM8CSMSVO9Cy', 'ROLE_ADMIN', 0, 1);

CREATE TABLE memes (
  id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
  title VARCHAR(255),
  user_id BIGINT(20),
  image_id BIGINT(20),
  blocked BIT(1),
  likes_number INT(11),
  comments_number INT(11)
);
INSERT INTO memes (title, user_id, image_id, blocked, likes_number, comments_number)
VALUES ('ofensywa', 1, 1, 0, 0, 0);
INSERT INTO memes (title, user_id, image_id, blocked, likes_number, comments_number)
VALUES ('poczta', 1, 2, 0, 0, 0);
INSERT INTO memes (title, user_id, image_id, blocked, likes_number, comments_number)
VALUES ('podobna do ojca', 1, 3, 0, 0, 0);
INSERT INTO memes (title, user_id, image_id, blocked, likes_number, comments_number)
VALUES ('Porządek, kubek z kawą', 1, 4, 0, 0, 0);
INSERT INTO memes (title, user_id, image_id, blocked, likes_number, comments_number)
VALUES ('Dzwon na skrzyżowaniu', 1, 5, 0, 0, 0);
INSERT INTO memes (title, user_id, image_id, blocked, likes_number, comments_number)
VALUES ('Wyjątkowa noc', 1, 6, 0, 0, 0);
INSERT INTO memes (title, user_id, image_id, blocked, likes_number, comments_number)
VALUES ('Silniki jdm', 1, 7, 0, 0, 0);
INSERT INTO memes (title, user_id, image_id, blocked, likes_number, comments_number)
VALUES ('Supra na loterii', 1, 8, 0, 0, 0);
INSERT INTO memes (title, user_id, image_id, blocked, likes_number, comments_number)
VALUES ('Komplementy', 1, 9, 0, 0, 0);
INSERT INTO memes (title, user_id, image_id, blocked, likes_number, comments_number)
VALUES ('Słoiki', 1, 10, 0, 0, 0);

CREATE TABLE images (
  id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
  file_path VARCHAR(255),
  user_id BIGINT(20),
  meme_id BIGINT(20)
);
INSERT INTO images (file_path, user_id, meme_id)
VALUES ('https://www.dropbox.com/scl/fi/mbyx9jx1kae5zmjer47vu/ofensywa.jpg?rlkey=4nkupm3x74fy4o6zpana69njo&dl=0&dl=1', 1, 1);
INSERT INTO images (file_path, user_id, meme_id)
VALUES ('https://www.dropbox.com/scl/fi/3dztxzjgo4k6qs2t2dwr4/poczta.jpg?rlkey=rxw15xv63iproqtfbz8w9nda5&dl=0&dl=1', 1, 2);
INSERT INTO images (file_path, user_id, meme_id)
VALUES ('https://www.dropbox.com/scl/fi/ztxifzkk7ykprf76ao0w9/podobna-do-ojca.jpg?rlkey=2qhuh8u1z0c0g8bihh2mwmm36&dl=0&dl=1', 1, 3);
INSERT INTO images (file_path, user_id, meme_id)
VALUES ('https://www.dropbox.com/scl/fi/pc1vqhh9j7b5qf31xwbax/Porz-dek-kubek-z-kaw.jpg?rlkey=nx6u4taiab96h2q1yywmtp2em&dl=0&dl=1', 1, 4);
INSERT INTO images (file_path, user_id, meme_id)
VALUES ('https://www.dropbox.com/scl/fi/790wu07jh2b6vebqqbw4r/Dzwon-na-skrzy-owaniu.jpg?rlkey=0qi7m1jkainmwds1iufd757h8&dl=0&dl=1', 1, 5);
INSERT INTO images (file_path, user_id, meme_id)
VALUES ('https://www.dropbox.com/scl/fi/ptquz79327xsw0w4h9mew/Wyj-tkowa-noc.jpg?rlkey=v62846a9f6574e9dmfs61ern7&dl=0&dl=1', 1, 6);
INSERT INTO images (file_path, user_id, meme_id)
VALUES ('https://www.dropbox.com/scl/fi/jxms97dowmoar2gke4t1y/Silniki-jdm.jpg?rlkey=8ax63wn77tjza9s2pna120a3w&dl=0&dl=1', 1, 7);
INSERT INTO images (file_path, user_id, meme_id)
VALUES ('https://www.dropbox.com/scl/fi/w5735xmlph03d1idxxqmk/Supra-na-loterii.jpg?rlkey=h1hymrp4o06hfr91208hsarm4&dl=0&dl=1', 1, 8);
INSERT INTO images (file_path, user_id, meme_id)
VALUES ('https://www.dropbox.com/scl/fi/0i9bzbz6kvwc5sgel7n35/Komplementy.jpg?rlkey=vzynj7tivvz4vjya2ay5i6wtg&dl=0&dl=1', 1, 9);
INSERT INTO images (file_path, user_id, meme_id)
VALUES ('https://www.dropbox.com/scl/fi/4vmh7qe9bjyv9pykq5e5v/S-oiki.jpg?rlkey=symtxe81qnnaj317fokrs62m6&dl=0&dl=1', 1, 10);

CREATE TABLE comments (
  id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
  meme_id BIGINT(20),
  username VARCHAR(255),
  content TEXT,
  parent_comment_id BIGINT(20)
);

CREATE TABLE likes (
  id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
  meme_id BIGINT(20),
  user_id BIGINT(20)
);
