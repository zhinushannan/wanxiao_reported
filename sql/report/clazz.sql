create table report.clazz
(
    clazz_name   varchar(255) not null
        primary key,
    teacher_name varchar(255) not null,
    date         varchar(255) not null,
    dept_id      varchar(255) not null,
    group_id     varchar(255) not null,
    bot_id       varchar(255) not null,
    `delete`     int          not null
);

