drop table if exists t_user;
drop table if exists t_login_log;

create table t_user (
    user_id   identity,
    user_name varchar(30),
    password  varchar(32),
    last_visit datetime,
    last_ip  varchar(23)
);

create table t_login_log (
     login_log_id  INT AUTO_INCREMENT PRIMARY KEY,
     user_id   INT,
     ip  varchar(23),
     login_datetime datetime
);