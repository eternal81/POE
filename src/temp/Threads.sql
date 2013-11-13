CREATE TABLE `Threads` (
  `ThreadID` varchar(12) NOT NULL,
  `poeUser` varchar(45) DEFAULT NULL,
  `datePosted` datetime DEFAULT NULL,
  `lastScraped` datetime DEFAULT CURRENT_TIMESTAMP,
  `lastActivity` datetime DEFAULT NULL,
  `HTML` mediumtext,
  PRIMARY KEY (`ThreadID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
