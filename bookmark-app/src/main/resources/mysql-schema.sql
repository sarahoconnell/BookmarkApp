
DROP TABLE IF EXISTS `users`; 
DROP TABLE IF EXISTS `link`; 
DROP TABLE IF EXISTS `board`; 
DROP TABLE IF EXISTS `user_roles`; 

CREATE TABLE `board` (
  `ID` varchar(255) NOT NULL,
  `NAME` varchar(255) DEFAULT NULL,
  `DESCRIPTION` varchar(255) DEFAULT NULL,
  `USERID` varchar(255) DEFAULT NULL,
  `IMG` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
);
CREATE TABLE `link` (
  `ID` varchar(255) NOT NULL,
  `URL` varchar(255) NOT NULL,
  `DESCRIPTION` varchar(255) DEFAULT NULL,
  `BOARDID` varchar(255) NOT NULL,
  `NAME` varchar(255) NOT NULL,
  `IMAGE` blob,
  PRIMARY KEY (`ID`)
);
CREATE TABLE `user_roles` (
  `ID` varchar(255) NOT NULL,
  `USERID` varchar(255) NOT NULL,
  `AUTHORITY` varchar(255) NOT NULL,
  PRIMARY KEY (`ID`)
);
CREATE TABLE `users` (
  `ID` varchar(255) NOT NULL,
  `NAME` varchar(255) NOT NULL,
  `PASSWORD` varchar(255) NOT NULL,
  `TWITTERID` varchar(255) DEFAULT NULL,
  `ENABLED` tinyint(1) NOT NULL,
  PRIMARY KEY (`ID`)
);