-- ユーザー情報を格納するuserテーブルを作成するSQL文

DROP TABLE IF EXISTS user;

CREATE TABLE user
(
   id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
   userid VARCHAR(100) NOT NULL UNIQUE,
   password VARCHAR(100) NOT NULL,
   authority VARCHAR(100),
   username VARCHAR(100) NOT NULL
);