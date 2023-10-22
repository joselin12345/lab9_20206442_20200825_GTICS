-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema partidosSDCI
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema partidosSDCI
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `partidosSDCI` DEFAULT CHARACTER SET utf8 ;
USE `partidosSDCI` ;

-- -----------------------------------------------------
-- Table `partidosSDCI`.`equipo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `partidosSDCI`.`equipo` (
  `idequipo` INT NOT NULL AUTO_INCREMENT,
  `nombreEquipo` VARCHAR(45) NOT NULL,
  `colorEquipo` VARCHAR(45) NOT NULL,
  `mascota` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idequipo`),
  UNIQUE INDEX `nombre_UNIQUE` (`nombreEquipo` ASC),
  UNIQUE INDEX `color_UNIQUE` (`colorEquipo` ASC),
  UNIQUE INDEX `mascota_UNIQUE` (`mascota` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `partidosSDCI`.`participante`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `partidosSDCI`.`participante` (
  `idparticipante` INT NOT NULL AUTO_INCREMENT,
  `equipo` INT NOT NULL,
  `carrera` VARCHAR(45) NOT NULL,
  `codigo` DECIMAL NOT NULL,
  `tipoParticipante` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idparticipante`),
  INDEX `fk_participante_equipo_idx` (`equipo` ASC),
  CONSTRAINT `fk_participante_equipo`
    FOREIGN KEY (`equipo`)
    REFERENCES `partidosSDCI`.`equipo` (`idequipo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `partidosSDCI`.`deporte`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `partidosSDCI`.`deporte` (
  `iddeporte` INT NOT NULL AUTO_INCREMENT,
  `nombreDeporte` VARCHAR(45) NOT NULL,
  `pesoDeporte` INT NOT NULL,
  PRIMARY KEY (`iddeporte`),
  UNIQUE INDEX `nombreActividad_UNIQUE` (`nombreDeporte` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `partidosSDCI`.`partido`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `partidosSDCI`.`partido` (
  `idpartido` INT NOT NULL AUTO_INCREMENT,
  `equipoA` INT NOT NULL,
  `equipoB` INT NOT NULL,
  `scoreA` INT NOT NULL,
  `scoreB` INT NOT NULL,
  PRIMARY KEY (`idpartido`),
  INDEX `fk_partido_equipo1_idx` (`equipoA` ASC),
  INDEX `fk_partido_equipo2_idx` (`equipoB` ASC),
  CONSTRAINT `fk_partido_equipo1`
    FOREIGN KEY (`equipoA`)
    REFERENCES `partidosSDCI`.`equipo` (`idequipo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_partido_equipo2`
    FOREIGN KEY (`equipoB`)
    REFERENCES `partidosSDCI`.`equipo` (`idequipo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `partidosSDCI`.`historialPartidos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `partidosSDCI`.`historialPartidos` (
  `idhistorialPartidos` INT NOT NULL AUTO_INCREMENT,
  `partido_idpartido` INT NOT NULL,
  `deporte_iddeporte` INT NOT NULL,
  `horaFecha` DATETIME NOT NULL,
  PRIMARY KEY (`idhistorialPartidos`),
  INDEX `fk_historialPartidos_partido1_idx` (`partido_idpartido` ASC),
  INDEX `fk_historialPartidos_deporte1_idx` (`deporte_iddeporte` ASC),
  CONSTRAINT `fk_historialPartidos_partido1`
    FOREIGN KEY (`partido_idpartido`)
    REFERENCES `partidosSDCI`.`partido` (`idpartido`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_historialPartidos_deporte1`
    FOREIGN KEY (`deporte_iddeporte`)
    REFERENCES `partidosSDCI`.`deporte` (`iddeporte`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `partidosSDCI`.`participantesPartido`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `partidosSDCI`.`participantesPartido` (
  `idparticipantesPartido` INT NOT NULL,
  `partido_idpartido` INT NOT NULL,
  `participante_idparticipante` INT NOT NULL,
  `horaFecha` DATETIME NOT NULL,
  PRIMARY KEY (`idparticipantesPartido`),
  INDEX `fk_participantesPartido_partido1_idx` (`partido_idpartido` ASC),
  INDEX `fk_participantesPartido_participante1_idx` (`participante_idparticipante` ASC),
  CONSTRAINT `fk_participantesPartido_partido1`
    FOREIGN KEY (`partido_idpartido`)
    REFERENCES `partidosSDCI`.`partido` (`idpartido`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_participantesPartido_participante1`
    FOREIGN KEY (`participante_idparticipante`)
    REFERENCES `partidosSDCI`.`participante` (`idparticipante`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
