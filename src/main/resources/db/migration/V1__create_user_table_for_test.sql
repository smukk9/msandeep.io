
CREATE SEQUENCE hibernate_sequence;
CREATE SEQUENCE user_tb_seq;
CREATE TABLE IF NOT EXISTS "USERS" (
  USER_ID INTEGER PRIMARY KEY DEFAULT NEXTVAL('user_tb_seq'),
  ACTIVE VARCHAR(255) NOT NULL,
  CREATE_DATE DATE NOT NULL DEFAULT CURRENT_DATE,
  EMAIL VARCHAR(255) UNIQUE NOT NULL,
  MODIFY_DATE DATE NOT NULL DEFAULT CURRENT_DATE,
  PASSWORD VARCHAR(255) NOT NULL,
  USERNAME VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS "TAG" (
  TAG_ID  SERIAL PRIMARY KEY NOT NULL,
  CREATE_DATE VARCHAR(255) NOT NULL,
  MODIFY_DATE VARCHAR(255) NOT NULL,
  TAGNAME VARCHAR(255) NOT NULL


);

CREATE TABLE IF NOT EXISTS "ARTICLE" (
  ARTICLE_ID  SERIAL  PRIMARY KEY NOT NULL,
  CREATE_DATE VARCHAR(255) NOT NULL,
  MODIFY_DATE VARCHAR(255) NOT NULL,
  TITLE VARCHAR(255) NOT NULL,
  USER_ID INTEGER NOT NULL references "USERS" (USER_ID),
  CONTENT TEXT NOT NULL


);

CREATE TABLE IF NOT EXISTS "ARTICLE_TAG" (
  ARTICLE_ID INTEGER NOT NULL  references "ARTICLE" (ARTICLE_ID),
  TAG_ID INTEGER NOT NULL  references "TAG" (TAG_ID)

);

CREATE TABLE IF NOT EXISTS "ROLE" (
  ROLE_ID  SERIAL PRIMARY KEY NOT NULL,
  CREATE_DATE VARCHAR(255),
  MODIFY_DATE VARCHAR(255),
  ROLE VARCHAR(255) NOT NULL


);

CREATE TABLE IF NOT EXISTS "USER_ROLE" (
  USER_ID INTEGER NOT NULL  references "USERS" (USER_ID),
  ROLE_ID INTEGER NOT NULL  references "ROLE" (ROLE_ID)

);
GRANT USAGE, SELECT ON ALL SEQUENCES IN SCHEMA public TO dbuser;