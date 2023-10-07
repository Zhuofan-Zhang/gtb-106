-- init users
-- admin:123
INSERT INTO users (username, password, enabled)
VALUES ('admin', '{bcrypt}$2a$10$3TVm3V.Cijn0zoDAqIY/C./kFZDUgo9bugdTMuxXoh1COuR43.FUO', 1);

-- init authorities
INSERT INTO authorities (username, authority)
VALUES ('admin', 'ROLE_ADMIN');
