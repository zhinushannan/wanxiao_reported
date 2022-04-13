create table report.bot_request
(
    request_id  int auto_increment
        primary key,
    flag        varchar(255) not null,
    bot_id      varchar(255) not null,
    type        int          not null comment '1-friend
0-group',
    target_id   varchar(255) not null,
    comment     varchar(255) null,
    create_time datetime     not null
);

