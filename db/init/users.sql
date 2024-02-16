

create table if not exists Users(

    id serial primary key,

    first_name varchar(100),

    last_name varchar(100),

    middle_name varchar(100),

    email varchar(100) unique,

    password_hash varchar(100),

    phone varchar(100) unique not null

);

    


CREATE TYPE status_type AS ENUM('approved', 'requested', 'declined');

create table if not exists Friend_request(

    initiator_user_id int not null,

    target_user_id int not null,

    status status_type,

    requested_at TIMESTAMP default now(),

    updated_at TIMESTAMP,

    

    primary key (initiator_user_id,target_user_id),

    foreign key (initiator_user_id) references Users(id),

    foreign key (target_user_id) references Users(id)

    

);