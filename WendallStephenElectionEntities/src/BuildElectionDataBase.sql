CREATE TABLE ELECTION.CANDIDATE (
   CID INTEGER PRIMARY KEY AUTO_INCREMENT,
   CNAME VARCHAR(30),
   CVOTES INTEGER DEFAULT 0
);

INSERT INTO ELECTION.CANDIDATE (CNAME) VALUES ('Ju Wen Chen');
INSERT INTO ELECTION.CANDIDATE (CNAME) VALUES ('Grace Semanio');
INSERT INTO ELECTION.CANDIDATE (CNAME) VALUES ('Asif Khan');

CREATE TABLE ELECTION.VOTER (
VPK INTEGER PRIMARY KEY AUTO_INCREMENT,
VNAME VARCHAR(30) NOT NULL,
VID VARCHAR(30) NOT NULL,
VOTED CHAR(1) DEFAULT NULL
);

INSERT INTO ELECTION.VOTER( VNAME, VID ) VALUES ('A', 'Alpha');
INSERT INTO ELECTION.VOTER( VNAME, VID ) VALUES ('B', 'Bravo');
INSERT INTO ELECTION.VOTER( VNAME, VID ) VALUES ('C', 'Charlie');
INSERT INTO ELECTION.VOTER( VNAME, VID ) VALUES ('D', 'Delta');
INSERT INTO ELECTION.VOTER( VNAME, VID ) VALUES ('E', 'Echo');
INSERT INTO ELECTION.VOTER( VNAME, VID ) VALUES ('F', 'Foxtrot');
INSERT INTO ELECTION.VOTER( VNAME, VID ) VALUES ('G', 'Golf');
INSERT INTO ELECTION.VOTER( VNAME, VID ) VALUES ('H', 'Hotel');
INSERT INTO ELECTION.VOTER( VNAME, VID ) VALUES ('I', 'India');
INSERT INTO ELECTION.VOTER( VNAME, VID ) VALUES ('J', 'Juliet');
INSERT INTO ELECTION.VOTER( VNAME, VID ) VALUES ('K', 'Kilo');
INSERT INTO ELECTION.VOTER( VNAME, VID ) VALUES ('L', 'Lima');
INSERT INTO ELECTION.VOTER( VNAME, VID ) VALUES ('M', 'Mike');
INSERT INTO ELECTION.VOTER( VNAME, VID ) VALUES ('N', 'November');
INSERT INTO ELECTION.VOTER( VNAME, VID ) VALUES ('O', 'Oscar');
INSERT INTO ELECTION.VOTER( VNAME, VID ) VALUES ('P', 'Papa');
INSERT INTO ELECTION.VOTER( VNAME, VID ) VALUES ('Q', 'Quebec');
INSERT INTO ELECTION.VOTER( VNAME, VID ) VALUES ('R', 'Romeo');
INSERT INTO ELECTION.VOTER( VNAME, VID ) VALUES ('S', 'Sierra');
INSERT INTO ELECTION.VOTER( VNAME, VID ) VALUES ('T', 'Tango');
INSERT INTO ELECTION.VOTER( VNAME, VID ) VALUES ('U', 'Uniform');
INSERT INTO ELECTION.VOTER( VNAME, VID ) VALUES ('V', 'Victor');
INSERT INTO ELECTION.VOTER( VNAME, VID ) VALUES ('W', 'Whiskey');
INSERT INTO ELECTION.VOTER( VNAME, VID ) VALUES ('X', 'X-ray');
INSERT INTO ELECTION.VOTER( VNAME, VID ) VALUES ('Y', 'Yankee');
INSERT INTO ELECTION.VOTER( VNAME, VID ) VALUES ('Z', 'Zulu');