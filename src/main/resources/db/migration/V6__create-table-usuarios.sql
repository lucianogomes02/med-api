create table usuarios(

    id BINARY(16) not null,
    login varchar(100) not null unique,
    senha varchar(255) not null,

    primary key(id)
);