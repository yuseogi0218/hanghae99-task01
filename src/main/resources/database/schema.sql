DROP TABLE IF EXISTS `Review` CASCADE;
DROP TABLE IF EXISTS `Product` CASCADE;

CREATE TABLE `Product` (
                                         `id`          BIGINT(20) NOT NULL AUTO_INCREMENT PRIMARY KEY,
                                         `reviewCount` BIGINT(20) NOT NULL,
                                         `score`       FLOAT  NOT NULL
) ENGINE = InnoDB CHARSET = utf8;

CREATE TABLE `Review` (
                          `id`          BIGINT(20) NOT NULL AUTO_INCREMENT PRIMARY KEY,
                          `productId`   BIGINT(20) NOT NULL,
                          `userId`      BIGINT(20) NOT NULL,
                          `score`       INT  NOT NULL,
                          `content`     VARCHAR(255) NOT NULL,
                          `imageUrl`    VARCHAR(255) NULL,
                          `createdAt`   DATETIME(3) NOT NULL,
                          FOREIGN KEY (`productId`) REFERENCES Product(`id`)
) ENGINE = InnoDB CHARSET = utf8;

CREATE INDEX idx_product_user ON Review (productId, userId);