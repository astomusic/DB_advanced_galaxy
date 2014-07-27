

USE popidb ;

-- -----------------------------------------------------
-- Table `mydb`.`galaxy`
-- -----------------------------------------------------
DROP TABLE IF exists galaxy;
CREATE TABLE IF NOT EXISTS galaxy (
  GID TINYINT NOT NULL,
  NAME VARCHAR(16) NULL,
  HP INT(11) NULL,
  PRIMARY KEY (GID)
);


-- -----------------------------------------------------
-- Table `mydb`.`user`
-- -----------------------------------------------------
DROP TABLE IF exists user;
CREATE TABLE IF NOT EXISTS user (
  UID INT(11) NOT NULL,
  GID TINYINT NOT NULL,
  PRIMARY KEY (UID, GID),
  FOREIGN KEY (GID)
  REFERENCES galaxy (GID)
);



-- -----------------------------------------------------
-- Table `mydb`.`slog`
-- -----------------------------------------------------
DROP TABLE IF exists slog;
CREATE TABLE IF NOT EXISTS slog (
  LOGID INT(11) NOT NULL,
  UID INT(11) NULL,
  LOG VARCHAR(64) NULL,
  LOG_TIME TIMESTAMP NOT NULL,
  PRIMARY KEY (LOGID),
  FOREIGN KEY (UID)
    REFERENCES user (UID)
);


-- -----------------------------------------------------
-- Table `mydb`.`ship`
-- -----------------------------------------------------
DROP TABLE IF exists ship;
CREATE TABLE IF NOT EXISTS ship (
  SID INT(11) NOT NULL,
  UID INT(11) NULL,
  GID TINYINT NULL,
  ATK INT(11) NULL,
  TYPE CHAR(1) NULL,
  PRIMARY KEY (SID),
  FOREIGN KEY (UID , GID)
  REFERENCES user (UID , GID)
);

show tables;