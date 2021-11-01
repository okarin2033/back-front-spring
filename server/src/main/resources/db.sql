create table employee (
    id bigint not null,
    name varchar(255),
    phone varchar(255),
    priv_id bigint,
    primary key (id))
    engine=InnoDB;

create table hibernate_sequence (
    next_val bigint)
    engine=InnoDB;
insert into hibernate_sequence values ( 1 );

create table item (
    item_id bigint not null,
    name varchar(255),
    type_type_id bigint,
    primary key (item_id))
    engine=InnoDB;

create table orders (
    id bigint not null,
    date varchar(255),
    employee_id bigint, payment_id bigint,
    user_user_id bigint,
    primary key (id))
    engine=InnoDB;

create table orders_item_list (
    orders_id bigint not null,
    item_list_item_id bigint not null)
    engine=InnoDB;

create table payment (
    id bigint not null,
    payment_type varchar(255),
    primary key (id))
    engine=InnoDB;

create table priv (
    id bigint not null,
    access integer not null,
    descr varchar(255),
    primary key (id))
    engine=InnoDB;

create table type (
    type_id bigint not null,
    descr varchar(255),
    name varchar(255),
    primary key (type_id))
    engine=InnoDB;

create table users (
    user_id bigint not null,
    email varchar(255),
    name varchar(255),
    phone varchar(255),
    primary key (user_id))
    engine=InnoDB;

alter table orders_item_list add constraint UK_jlbxcq8udm1wmy0fdver7c1jy unique (item_list_item_id);
alter table employee add constraint FK500lmb6mo966k3r6cy1yhxmhx foreign key (priv_id) references priv (id);
alter table item add constraint FKcpfrv7vplnym8mkeytmecdnhc foreign key (type_type_id) references type (type_id);
alter table orders add constraint FKog5v9ga2g2ukytypn4ocip6b4 foreign key (employee_id) references employee (id);
alter table orders add constraint FKag8ppnkjvx255gj7lm3m18wkj foreign key (payment_id) references payment (id);
alter table orders add constraint FK38709695otpk064vi3y92u08s foreign key (user_user_id) references users (user_id);
alter table orders_item_list add constraint FKiq7spj3xlhc4rq2q1lv6kbiny foreign key (item_list_item_id) references item (item_id);
alter table orders_item_list add constraint FK3vdx40qe5cdwpcjqjw2oyfr6b foreign key (orders_id) references orders (id)