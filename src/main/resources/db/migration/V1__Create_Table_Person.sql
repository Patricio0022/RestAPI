
CREATE TABLE IF NOT EXISTS `person` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(255) NOT NULL,
  `last_name` varchar(255) NOT NULL,
  `adress` varchar(255) NOT NULL,
  `gender` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
);



