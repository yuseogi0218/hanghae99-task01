DROP TABLE IF EXISTS `Review`;
DROP TABLE IF EXISTS `Product`;

CREATE TABLE `Product` (
                           `id`          BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                           `reviewCount` BIGINT NOT NULL,
                           `score`       FLOAT NOT NULL
);

CREATE TABLE `Review` (
                          `id`          BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                          `productId`   BIGINT NOT NULL,
                          `userId`      BIGINT NOT NULL,
                          `score`       INT NOT NULL,
                          `content`     VARCHAR(255) NOT NULL,
                          `imageUrl`    VARCHAR(255),
                          `createdAt`   TIMESTAMP NOT NULL,
                          FOREIGN KEY (`productId`) REFERENCES `Product`(`id`)
);