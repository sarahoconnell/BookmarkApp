CREATE TABLE users (ID VARCHAR, NAME VARCHAR, PASSWORD VARCHAR, TWITTERID VARCHAR, ENABLED TINYINT, PRIMARY KEY (ID))
CREATE TABLE link (ID VARCHAR, URL VARCHAR, DESCRIPTION VARCHAR, BOARDID VARCHAR, NAME VARCHAR, IMAGE BLOB, PRIMARY KEY (ID))
CREATE TABLE board (ID VARCHAR, NAME VARCHAR, DESCRIPTION VARCHAR, USERID VARCHAR, PRIMARY KEY (ID))
CREATE TABLE user_roles (ID VARCHAR, USERID VARCHAR, AUTHORITY VARCHAR)
