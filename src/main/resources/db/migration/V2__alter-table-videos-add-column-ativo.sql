alter table videos add column ativo tinyint;
update videos set ativo = 1;