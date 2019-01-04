create sequence hibernate_sequence;
create table if not exists "users" (
  user_id serial primary key,
  active integer not null,
  create_date date not null default current_date,
  email varchar(255) unique not null,
  modify_date date not null default current_date,
  password varchar(255) not null,
  username varchar(255) not null
);

create table if not exists "tag" (
  tag_id  serial primary key not null,
  create_date date not null,
  modify_date date not null,
  tagname varchar(255) not null
);

create table if not exists "article" (
  article_id  serial  primary key not null,
  create_date date not null,
  modify_date date not null,
  title varchar(255) not null,
  user_id integer not null references "users" (user_id),
  content text not null
);

create table if not exists "article_tag" (
  article_id integer not null  references "article" (article_id),
  tag_id integer not null  references "tag" (tag_id)
);

create table if not exists "role" (
  role_id  serial primary key not null,
  create_date date,
  modify_date date,
  role varchar(255) not null
);

create table if not exists "user_role" (
  user_id integer not null  references "users" (user_id),
  role_id integer not null  references "role" (role_id)
);
