alter table videos add column categoria_id bigint;
update videos set categoria_id = 1;
alter table videos add constraint fk_video_categoria foreign key (categoria_id) references categorias(id);

alter table videos modify categoria_id bigint not null;