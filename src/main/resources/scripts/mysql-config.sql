-- create database
create database if not exists recipe_dev;
create database if not exists recipe_prod;

-- create database service accounts for localhost
create user 'sig_dev_user'@'localhost' identified by 'signature#2715';
create user 'sig_prod_user'@'localhost' identified by 'signature#2715';

-- create database service accounts for remote
create user 'sig_dev_user'@'%' identified by 'signature$2715';
create user 'sig_prod_user'@'%' identified by 'signature$2715';

-- database grants for user in localhost
GRANT SELECT ON recipe_dev.* to 'sig_dev_user'@'localhost';
GRANT INSERT ON recipe_dev.* to 'sig_dev_user'@'localhost';
GRANT DELETE ON recipe_dev.* to 'sig_dev_user'@'localhost';
GRANT UPDATE ON recipe_dev.* to 'sig_dev_user'@'localhost';

GRANT SELECT ON recipe_prod.* to 'sig_prod_user'@'localhost';
GRANT INSERT ON recipe_prod.* to 'sig_prod_user'@'localhost';
GRANT DELETE ON recipe_prod.* to 'sig_prod_user'@'localhost';
GRANT UPDATE ON recipe_prod.* to 'sig_prod_user'@'localhost';

-- database grants for user in remote
GRANT SELECT ON recipe_dev.* to 'sig_dev_user'@'%';
GRANT INSERT ON recipe_dev.* to 'sig_dev_user'@'%';
GRANT DELETE ON recipe_dev.* to 'sig_dev_user'@'%';
GRANT UPDATE ON recipe_dev.* to 'sig_dev_user'@'%';

GRANT SELECT ON recipe_prod.* to 'sig_prod_user'@'%';
GRANT INSERT ON recipe_prod.* to 'sig_prod_user'@'%';
GRANT DELETE ON recipe_prod.* to 'sig_prod_user'@'%';
GRANT UPDATE ON recipe_prod.* to 'sig_prod_user'@'%';