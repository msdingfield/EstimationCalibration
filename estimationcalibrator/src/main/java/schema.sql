create table users(
    username varchar_ignorecase(50) not null primary key,
    password varchar_ignorecase(50) not null,
    enabled boolean not null
);

create table authorities (
    username varchar_ignorecase(50) not null,
    authority varchar_ignorecase(50) not null,
    constraint fk_authorities_users foreign key(username) references users(username)
);
create unique index ix_auth_username on authorities (username,authority);

create table estimators (
	username varchar_ignorecase(50) not null primary key,
	language varchar_ignorecase(3) not null,
	next_question_id decimal not null
);

create table questions (
    question_id varchar(36) not null primary key,
    prose varchar(2000) not null,
    answer double not null,
    language varchar_ignorecase(3) not null,
    username varchar_ignorecase(50) not null
);

create table estimates (
    username varchar_ignorecase(50) not null,
    question_id decimal not null,
    lower_bound double not null,
    upper_bound double not null,
    primary key (username, question_id)
);