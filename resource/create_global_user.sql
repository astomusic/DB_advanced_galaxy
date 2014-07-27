use popidb;

show tables;

DROP TABLE IF exists db;
CREATE TABLE IF NOT EXISTS db (
  DBID TINYINT NOT NULL,
  DBNAME CHAR(3) NULL,
  IP CHAR(15) NULL,
  PRIMARY KEY (DBID)
);
select * from db;

DROP TABLE IF exists user2db;
CREATE TABLE IF NOT EXISTS user2db (
	UID INT(11) auto_increment not NULL,
	GID TINYINT NULL,
	DBID TINYINT NULL,
	PRIMARY KEY (UID),
	FOREIGN KEY (DBID) REFERENCES db(DBID)
);
select * from user2db;

DROP PROCEDURE IF EXISTS sp_adduser;
DELIMITER $$
CREATE PROCEDURE sp_adduser(out ouid int, out ogid tinyint, out odbid tinyint) 
begin
START transaction;

	INSERT INTO user2db values();
	set ouid = LAST_INSERT_ID();
	set ogid = ouid % 4 + 1;
	set odbid = ogid % 2 + 1;
	update user2db set gid = ogid, dbid = odbid WHERE uid = ouid;

COMMIT;
end $$	
DELIMITER ;

CALL sp_adduser(@uid, @gid, @dbid);
insert INTO db values(2, 'db2','10.73.45.75');
insert INTO db values(1, 'db1','10.73.45.53');
