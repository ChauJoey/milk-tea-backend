-- milk-tea user table
drop table if exists mt_user;
create table mt_user
(
    id          bigint primary key auto_increment, -- PK
    username    varchar(50)  not null,
    phone       varchar(20)  not null,
    password    varchar(100) not null,
    status      tinyint   default 1,               -- 1=normal, 0=disabled
    create_time timestamp default current_timestamp,
    update_time timestamp default current_timestamp on update current_timestamp
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='milk-tea user table';


-- milk-tea drink table
drop table if exists mt_drink;
create table mt_drink
(
    id          bigint primary key auto_increment,
    name        varchar(100)   not null,
    price       decimal(10, 2) not null,

    -- custom fields
    size        varchar(20)    not null default 'medium', -- small, medium, large
    sugar_level varchar(20)             default 'normal', -- normal, less, no
    ice_level   varchar(20)             default 'normal', -- normal, less, no

    status      tinyint                 default 1,        -- 1=normal, 0=disabled

    create_time timestamp               default current_timestamp,
    update_time timestamp               default current_timestamp on update current_timestamp
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='milk-tea drink table';


-- milk-tea order table
drop table if exists mt_order;
create table mt_order
(
    id          bigint primary key auto_increment,
    user_id     bigint         not null,
    total_price decimal(10, 2) not null,
    status      tinyint   default 0, -- 0=pending, 1=completed, 2=cancelled
    remark      varchar(255),        -- remark message

    create_time timestamp default current_timestamp,
    update_time timestamp default current_timestamp on update current_timestamp,
    constraint fk_mt_order_user foreign key (user_id) references mt_user (id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='milk-tea order table';


-- milk-tea order item table
drop table if exists mt_order_item;
create table mt_order_item
(
    id          bigint primary key auto_increment,
    order_id    bigint         not null,
    drink_id    bigint         not null,
    quantity    int            not null,
    unit_price  decimal(10, 2) not null,

    -- custom fields
    size        varchar(20)    not null,
    sugar_level varchar(20),
    ice_level   varchar(20),

    create_time timestamp default current_timestamp,
    update_time timestamp default current_timestamp on update current_timestamp,
    constraint fk_mt_order_item_order foreign key (order_id) references mt_order (id),
    constraint fk_mt_order_item_drink foreign key (drink_id) references mt_drink (id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='milk-tea order item table';
