ALTER TABLE `Online_curs`.`Curs`
DROP FOREIGN KEY `fk_user_fk_userID`;
ALTER TABLE `Online_curs`.`Curs`
CHANGE COLUMN `fk_user` `Owner` INT NULL DEFAULT NULL ,
DROP INDEX `fk_user_fk_uderID` ,
ADD INDEX `Owner_id` (`Owner` ASC) VISIBLE;
;
ALTER TABLE `Online_curs`.`Curs`
ADD CONSTRAINT `Owner_id`
  FOREIGN KEY (`Owner`)
  REFERENCES `Online_curs`.`User` (`idUser`);
