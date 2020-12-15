insert into BLOG(id, cnt, title) values (1, 1, 'blog');
insert into BLOG(id, cnt, title) values (2, 2, 'blog');
insert into BLOG(id, cnt, title) values (3, 3, 'blog');
insert into BLOG(id, cnt, title) values (4, 4, 'blog');

insert into BLOG_USER values (1, true, 'yechan', '1234', 'ROLE_ADMIN', 'yechan');

DROP TABLE BLOG CASCADE CONSTRAINTS;