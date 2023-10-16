CREATE TABLE urls
(ID INT AUTO_INCREMENT PRIMARY KEY,
LongURL VARCHAR(2048) NOT NULL,
ShortURL VARCHAR(255) NOT NULL UNIQUE,
CreationDate DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
ExpirationDate DATETIME,
ClickCount INT NOT NULL DEFAULT 0,
IsActive BOOLEAN NOT NULL DEFAULT TRUE);