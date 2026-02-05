DROP TABLE IF EXISTS exercici;
CREATE TABLE user (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nivell INT NOT NULL,
    tipus VARCHAR(100) NOT NULL,
    durada INT NOT NULL,
    material VARCHAR(100),
    imagen VARCHAR(500) NULL,
    ultimAcces timestamp NULL DEFAULT NULL,
    dataCreated timestamp NULL DEFAULT NULL,
    dataUpdated timestamp NULL DEFAULT NULL
);