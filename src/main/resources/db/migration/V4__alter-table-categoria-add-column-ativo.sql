alter table categorias add column ativo tinyint;
update categorias set ativo = 1;