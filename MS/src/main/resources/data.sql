--userテーブルに挿入するデータを記載

ALTER TABLE user CONVERT TO CHARACTER SET utf8;

INSERT INTO user(userid, password, authority,username)
VALUES('sn_hiramitsu','$2a$10$gVfjG34q2VGdHbKpEjwM/O5w47EiqFr1IuaTRi26mvWHCi5FnKw6y','ROLE_USER','平光さなみ');

INSERT INTO user(userid, password, authority,username)
VALUES('ki_shindo','$2a$10$AJ7BcI0AKnMyU2i21iADn.fMlV6Ov/uqmynm7ciQHYgxDX4OrkwNa','ROLE_USER','進藤敬子');

INSERT INTO user(userid, password, authority,username)
VALUES('kz_kudo','$2a$10$5h0u4xuE6xrMcizTx2ZeJuEBl3zgq4cK1CBWDGlcEJLxCSGzRDXmK','ROLE_USER','工藤和雄');

INSERT INTO user(userid, password, authority,username)
VALUES('ak_hasegawa','$2a$10$3ySZKAOWqDAg8/aNOCQP9OQWwIY78n2dPo5TiyWSQnu5ULPiZa7YC','ROLE_USER','長谷川晃');

INSERT INTO user(userid, password, authority,username)
VALUES('ht_watanabe','$2a$10$CORGu7QDpyqNd64qB0VNwumfOWnGN9oqZpjB68mocdg1BfA7iQRn.','ROLE_USER','渡辺初枝');

INSERT INTO user(userid, password, authority,username)
VALUES('ys_sato','$2a$10$bOXuWUDbvu4PKtPSCQHgYOH91Rxc52ueCbFpAjfhHvR/TlCQZkVXu','ROLE_USER','佐藤由明');
