CREATE TABLE `Online_curs`.`Curs` (
  `idCurs` INT NOT NULL AUTO_INCREMENT,
  `Price` DECIMAL(20,2) NULL,
  `Title` VARCHAR(100) NULL,
  `fk_user` INT NULL,
  PRIMARY KEY (`idCurs`),
  UNIQUE INDEX `idCurs_UNIQUE` (`idCurs` ASC) VISIBLE,
  INDEX `fk_user_fk_uderID` (`fk_user` ASC) VISIBLE,
  CONSTRAINT `fk_user_fk_userID`
    FOREIGN KEY (`fk_user`)
    REFERENCES `Online_curs`.`User` (`idUser`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
