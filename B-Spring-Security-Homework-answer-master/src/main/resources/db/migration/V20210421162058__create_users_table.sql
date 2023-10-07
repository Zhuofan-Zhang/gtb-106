CREATE TABLE `users`
(
    username VARCHAR(64)  NOT NULL PRIMARY KEY,
    password VARCHAR(255) NOT NULL,
    enabled  BOOLEAN      NOT NULL
) DEFAULT CHARSET = utf8mb4
  COLLATE utf8mb4_bin;