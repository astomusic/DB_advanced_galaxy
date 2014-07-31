

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

select * from galaxy;
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

select * from user;

-- -----------------------------------------------------
-- Table `mydb`.`slog`
-- -----------------------------------------------------
DROP TABLE IF exists slog;
CREATE TABLE IF NOT EXISTS slog (
  LOGID INT(11) auto_increment not NULL,
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
  SID INT(11) auto_increment not NULL,
  UID INT(11) NULL,
  GID TINYINT NULL,
  ATK INT(11) NULL,
  TYPE CHAR(1) NULL,
  PRIMARY KEY (SID),
  FOREIGN KEY (UID , GID)
  REFERENCES user (UID , GID)
);

select * from ship;

show tables;

insert INTO galaxy values(1, 'El Facil','100000');
insert INTO galaxy values(2, 'Odin','100000');
insert INTO galaxy values(3, 'Iserlohn','100000');
insert INTO galaxy values(4, 'Heinessen','100000');


DROP PROCEDURE IF EXISTS sp_addship;
DELIMITER $$
CREATE PROCEDURE sp_addship(in ouid int, in ogid tinyint) 
begin
	DECLARE idx int;
	DECLARE max int;
	DECLARE oatk int;

	START transaction;

	INSERT INTO user values(ouid, ogid);	
	
	SET idx = 0;
	SET max = 10;

	WHILE idx < max DO
		set oatk = FLOOR(5 + RAND()*(101-5));
		INSERT INTO ship (UID, GID, ATK, TYPE) VALUES(ouid,ogid,oatk,'A');
		SET idx = idx + 1;
	END WHILE;

	COMMIT;
end $$	
DELIMITER ;

CALL sp_addship(58, 1);

DROP PROCEDURE IF EXISTS sp_attackGalaxy;
DELIMITER $$
CREATE PROCEDURE sp_attackGalaxy(in oattack int, in ogid tinyint) 
begin
	START transaction;
	
	UPDATE galaxy SET hp = hp - oattack WHERE GID = ogid;

	COMMIT;
end $$	
DELIMITER ;