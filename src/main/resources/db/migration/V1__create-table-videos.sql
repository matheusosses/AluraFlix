create table videos(
    id bigint not null auto_increment,
    titulo varchar(100) not null,
    descricao varchar(250),
    url varchar(300) not null,
    primary key (id)
)