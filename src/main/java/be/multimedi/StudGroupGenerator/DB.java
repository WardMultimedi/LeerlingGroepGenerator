package be.multimedi.StudGroupGenerator;

final class DB {
    static final String url = "jdbc:mariadb://noelvaes.eu/javaeeheverleeDB";
    static final String login = "javaeeheverlee";
    static final String pwd = "j@v@eeheverlee2019";

    /*
    CREATE TABLE `javaeeheverleeDB`.`Students` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `firstName` VARCHAR(45) NOT NULL,
  `lastName` VARCHAR(45) NOT NULL,
  `street` VARCHAR(45) NULL,
  `zipcode` VARCHAR(5) NULL,
  `city` VARCHAR(45) NULL,
  `email` VARCHAR(45) NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC));

CREATE TABLE `javaeeheverleeDB`.`TaskGroups` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `minStudents` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC));

CREATE TABLE `javaeeheverleeDB`.`StudentGroups` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `taskGroup_id` INT NOT NULL,
  PRIMARY KEY (`id`));
  UNIQUE INDEX `id_UNIQUE` (`id` ASC));

CREATE TABLE `javaeeheverleeDB`.`StudentGroupConnections` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `student_id` INT NOT NULL,
  `studGroup_id` INT NOT NULL,
  PRIMARY KEY (`id`));
  UNIQUE INDEX `id_UNIQUE` (`id` ASC));
     */


}
