CREATE TABLE `owner` (
  `id` INT(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL,
  `created_date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`));

CREATE TABLE `pet` (
  `id` INT(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL,
  `type` ENUM('dog', 'cat', 'chicken', 'snake') NOT NULL,
  `created_date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`));

CREATE TABLE `owner_pet` (
  `owner_id` INT(11) UNSIGNED NOT NULL,
  `pet_id` INT(11) UNSIGNED NOT NULL,
  `created_date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`owner_id`, `pet_id`),
  CONSTRAINT `fk_owner`
    FOREIGN KEY (`owner_id`)
    REFERENCES `owner` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_pet`
    FOREIGN KEY (`pet_id`)
    REFERENCES `pet` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

-- Default data
INSERT INTO owner(name) VALUES('John Doe');
INSERT INTO owner(name) VALUES('Jane Doe');

INSERT INTO pet(name, type) VALUES('Rocky', 'dog');
INSERT INTO pet(name, type) VALUES('Penny', 'chicken');
INSERT INTO pet(name, type) VALUES('Silvester', 'cat');
INSERT INTO pet(name, type) VALUES('Slither', 'snake');

INSERT INTO owner_pet(owner_id, pet_id) VALUES(1,1);
INSERT INTO owner_pet(owner_id, pet_id) VALUES(1,2);
INSERT INTO owner_pet(owner_id, pet_id) VALUES(1,3);
INSERT INTO owner_pet(owner_id, pet_id) VALUES(2,1);
INSERT INTO owner_pet(owner_id, pet_id) VALUES(2,4);