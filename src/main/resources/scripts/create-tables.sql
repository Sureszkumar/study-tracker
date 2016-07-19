
CREATE
  TABLE USER
  (
    ID                    INTEGER NOT NULL AUTO_INCREMENT ,
    MOBILE                VARCHAR(35) NOT NULL,
    EMAIL                 VARCHAR(255),
    NAME                  VARCHAR(255),
    AUTH_TOKEN            VARCHAR(255),
    OTP                   VARCHAR(255),
    OTP_VERIFIED          BOOLEAN,
    CREATION_DATE_TIME    TIMESTAMP NOT NULL,
    LAST_CHANGE_TIMESTAMP TIMESTAMP NOT NULL,
    PRIMARY KEY (ID)
  );