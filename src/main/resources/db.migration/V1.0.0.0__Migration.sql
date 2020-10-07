create table balance(
    id bigint primary key AUTO_INCREMENT,
    bonus_amount decimal(10,2) default 0
);

create table account_status(
    id smallint primary key,
    description varchar(10)
);

create table users(
    id bigint primary key AUTO_INCREMENT,
    `login` varchar(70),
    password varchar(40),
    first_name varchar(15),
    last_name varchar(15),
    email varchar(40),
    phone_number varchar(32),
    balance_id bigint,
    account_status_id smallint default 1,
    isAdmin smallint default 0,
    constraint balance_id_fk foreign key(balance_id) REFERENCES balance(id)
);


create table card_status(
    id smallint primary key,
    description varchar(10)
);

create table tarrifs(
    id smallint primary key,
    tarrif_name varchar(10),
    commission double(100, 0)
);

create table cards(
    id int primary key AUTO_INCREMENT,
    balance_id bigint,
    amount double default 0,
    tarrif_id smallint,
    card_number varchar(60),
    CVV varchar(10),
    MM_YY varchar(10),
    card_status_id smallint default 1,

    constraint  balance_card_id_fk foreign key(balance_id) references balance(id),
    constraint  tarrif_id_fk foreign key(tarrif_id) references tarrifs(id),
    constraint  card_status_id_fk foreign key(card_status_id) references card_status(id)
);

create table payment_status(
    id smallint primary key ,
    code_status int,
    description varchar(30)
);

create table payments(
    id int auto_increment primary key,
    recipient_card_number varchar(60),
    sender_card_id int,
    amount decimal,
    description varchar(100),
    status_id smallint default 1,
    accepting_date DATETIME default NOW(),

    constraint  sender_card_id_fk foreign key(sender_card_id) references cards(id),
    constraint  status_id_fk foreign key(status_id) references payment_status(id)
);

insert into payment_status(id, code_status, description) VALUES(1, 204, "ready");
insert into payment_status(id, code_status, description) VALUES(2, 200, "done");