
CREATE TABLE IF NOT EXISTS `board` (
  `ID` varchar(255) NOT NULL,
  `NAME` varchar(255) DEFAULT NULL,
  `DESCRIPTION` varchar(255) DEFAULT NULL,
  `USERID` varchar(255) DEFAULT NULL,
  `IMG` varchar(255) DEFAULT NULL,
  `ISPUBLIC` tinyint(1) NOT NULL,
  PRIMARY KEY (`ID`)
);

CREATE TABLE IF NOT EXISTS `link` (
  `ID` varchar(255) NOT NULL,
  `URL` varchar(255) NOT NULL,
  `DESCRIPTION` varchar(255) DEFAULT NULL,
  `BOARDID` varchar(255) NOT NULL,
  `NAME` varchar(255) NOT NULL,
  `IMAGE` blob,
  PRIMARY KEY (`ID`)
);

CREATE TABLE IF NOT EXISTS `user_roles` (
  `ID` varchar(255) NOT NULL,
  `USERID` varchar(255) NOT NULL,
  `AUTHORITY` varchar(255) NOT NULL,
  PRIMARY KEY (`ID`)
);

CREATE TABLE IF NOT EXISTS `users` (
  `ID` varchar(255) NOT NULL,
  `NAME` varchar(255) NOT NULL,
  `PASSWORD` varchar(255) NOT NULL,
  `TWITTERID` varchar(255) DEFAULT NULL,
  `ENABLED` tinyint(1) NOT NULL,
  PRIMARY KEY (`ID`)
);