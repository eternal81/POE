DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `addThread`(
	IN ithreadID VARCHAR(10), 
	IN ipoeUser VARCHAR(45), 
	IN dt VARCHAR(45), 
	in dt2 VARCHAR(45),
	IN iHTML MEDIUMTEXT)
BEGIN
	IF EXISTS(SELECT * FROM Threads WHERE ThreadID LIKE ithreadID) THEN
		UPDATE Threads SET lastScraped = now(), datePosted = dt, lastActivity=dt2, HTML=iHTML WHERE threadID=ithreadID;
ELSE
		INSERT INTO Threads (threadID, poeUser, datePosted, lastActivity, HTML) VALUES (ithreadID, ipoeUser, dt, dt2, iHTML);
END IF;
END$$
DELIMITER ;
