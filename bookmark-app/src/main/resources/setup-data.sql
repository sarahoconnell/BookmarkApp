INSERT IGNORE INTO `users`(ID, NAME, PASSWORD, TWITTERID, ENABLED) VALUES('007', 'admin', 'admin', '@admin', 1);
INSERT IGNORE INTO `users`(ID, NAME, PASSWORD, TWITTERID, ENABLED) VALUES('001', 'sample', 'sample', '@sample', 1);
INSERT IGNORE INTO `user_roles`(ID, USERID, AUTHORITY) VALUES('100007','007','ROLE_ADMIN');
INSERT IGNORE INTO `user_roles`(ID, USERID, AUTHORITY) VALUES('100001','001','ROLE_USER');
INSERT IGNORE INTO `board`(ID, NAME, DESCRIPTION, USERID, IMG, ISPUBLIC) VALUES('b01', 'Favourite Sites', 'My Favourite Websites', '001', 'icon-star', 1);
INSERT IGNORE INTO `board`(ID, NAME, DESCRIPTION, USERID, IMG, ISPUBLIC) VALUES('b02', 'News', 'News', '001', 'icon-star', 1);
INSERT IGNORE INTO `link`(ID, URL, DESCRIPTION, BOARDID, NAME) VALUES('l01', 'http://www.google.com', 'Search with confidence', 'b01', 'Google');
INSERT IGNORE INTO `link`(ID, URL, DESCRIPTION, BOARDID, NAME) VALUES('l02', 'http://www.facebook.com', 'Socialize', 'b01', 'Facebook');
INSERT IGNORE INTO `link`(ID, URL, DESCRIPTION, BOARDID, NAME) VALUES('l03', 'http://www.twitter.com', 'Tweet Happy', 'b01', 'Tweet');
INSERT IGNORE INTO `link`(ID, URL, DESCRIPTION, BOARDID, NAME) VALUES('l04', 'http://www.apple.com', 'Apple', 'b01', 'Apple');
INSERT IGNORE INTO `link`(ID, URL, DESCRIPTION, BOARDID, NAME) VALUES('l11', 'http://www.bbc.co.uk', 'BBC', 'b02', 'BBC');
INSERT IGNORE INTO `link`(ID, URL, DESCRIPTION, BOARDID, NAME) VALUES('l12', 'http://www.rte.ie', 'Irish News', 'b02', 'RTE');
INSERT IGNORE INTO `link`(ID, URL, DESCRIPTION, BOARDID, NAME) VALUES('l13', 'http://www.guardian.co.uk', 'The Guardian', 'b02', 'Guardian');

