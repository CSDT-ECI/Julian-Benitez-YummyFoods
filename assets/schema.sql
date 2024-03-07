CREATE DATABASE userportalschema;

CREATE TABLE login
(
    userId       VARCHAR(255) NOT NULL,
    userPassword VARCHAR(255),
    PRIMARY KEY (userId)
);

CREATE TABLE user
(
    userId      VARCHAR(255) NOT NULL,
    userName    VARCHAR(255) NOT NULL,
    emailId     VARCHAR(255) NOT NULL,
    recipeRated VARCHAR(255),
    PRIMARY KEY (userId)
);

CREATE TABLE recipe
(
    recipeId        INT AUTO_INCREMENT,
    name            VARCHAR(255),
    forPeople       INT,
    ingredients     VARCHAR(255),
    directions      VARCHAR(255),
    fileName        VARCHAR(255),
    pic             LONGBLOB,
    contentType     VARCHAR(255),
    userId          VARCHAR(255),
    currentRating   FLOAT,
    noOfPeopleRated INT,
    PRIMARY KEY (recipeId),
    FOREIGN KEY (userId) REFERENCES user (userId)
);

ALTER TABLE login
    ADD CONSTRAINT FK_login_userId FOREIGN KEY (userId) REFERENCES user (userId);