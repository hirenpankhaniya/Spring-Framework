create table authors (id bigint not null auto_increment, name varchar(255) not null, primary key (id));
create table books (id bigint not null auto_increment, format tinyint not null check (format between 0 and 4), language tinyint not null check (language between 0 and 2), subtitle varchar(255) not null, title varchar(255) not null, primary key (id));
create table books_authors (bookid bigint not null, authorid bigint not null, primary key (bookid, authorid));
create table books_borrowers (borrowerid bigint, bookid bigint not null, primary key (bookid));
create table books_genres (bookid bigint not null, genreid bigint not null, primary key (bookid, genreid));
create table books_library_branches (branchid bigint, bookid bigint not null, primary key (bookid));
create table borrowers (id bigint not null auto_increment, name varchar(255) not null, primary key (id));
create table genres (id bigint not null auto_increment, name varchar(255) not null, primary key (id));
create table librarybranches (id bigint not null auto_increment, address varchar(255) not null, name varchar(255) not null, primary key (id));

alter table books_authors add constraint FKn4kicg12yp811m2epjbmnkvm1 foreign key (authorid) references authors (id);
alter table books_authors add constraint FK7on3cs0219bi8u5ps9ob013p1 foreign key (bookid) references books (id);
alter table books_borrowers add constraint FKkcjpxr8bqi4fooxwn4eve155i foreign key (borrowerid) references borrowers (id);
alter table books_borrowers add constraint FKf7d9jc727lx0u7no5hwkrk09f foreign key (bookid) references books (id);
alter table books_genres add constraint FKoap2cbluqialetl6a58mfd87w foreign key (genreid) references genres (id);
alter table books_genres add constraint FK6ir4i7akyty1ftv9c0yxq5rlt foreign key (bookid) references books (id);
alter table books_library_branches add constraint FKb2nuwqrht28qbuyya9xkismfd foreign key (branchid) references librarybranches (id);
alter table books_library_branches add constraint FK6vr5iyxtmmu8byknroltvsg6s foreign key (bookid) references books (id);
