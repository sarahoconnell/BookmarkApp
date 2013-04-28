INSERT INTO USERS(ID, NAME, PASSWORD, TWITTERID, ENABLED) VALUES('007', 'admin', 'password', '@admin', 1);
INSERT INTO USERS(ID, NAME, PASSWORD, TWITTERID, ENABLED) VALUES('001', 'user', 'password', '@user', 1);

INSERT INTO USER_ROLES(ID, USERID, AUTHORITY) VALUES('100007','007','ROLE_ADMIN');
INSERT INTO USER_ROLES(ID, USERID, AUTHORITY) VALUES('100001','001','ROLE_USER');

INSERT INTO BOARD(ID, NAME, DESCRIPTION, USERID, IMG, ISPUBLIC) VALUES('b01', 'Public Sites', 'My Favourite Websites', '001', 'icon-star', 1);
INSERT INTO BOARD(ID, NAME, DESCRIPTION, USERID, IMG, ISPUBLIC) VALUES('b02', 'Private Sites', 'My Favourite Websites', '001', 'icon-star', 0);

INSERT INTO LINK(ID, URL, DESCRIPTION, BOARDID, NAME) VALUES('l01', 'http://www.google.com', 'Search with confidence', 'b01', 'Google');
INSERT INTO LINK(ID, URL, DESCRIPTION, BOARDID, NAME) VALUES('l02', 'http://www.google.com', 'Search with confidence', 'b02', 'Google');
