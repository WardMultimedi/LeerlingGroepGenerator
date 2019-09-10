package be.multimedi.StudGroupGenerator;

class DB {
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


     */
}
