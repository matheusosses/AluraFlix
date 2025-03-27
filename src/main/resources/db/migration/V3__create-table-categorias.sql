create table categorias(
    id bigint not null auto_increment,
    titulo varchar(50) unique,
    cor varchar(50),
    primary key (id)
)