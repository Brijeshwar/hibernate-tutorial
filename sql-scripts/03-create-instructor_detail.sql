create table instructor (
	id int(11) not null auto_increment,
    youtube_channel varchar(128) default null,
    hobby varchar(45) default null,
	primary key (id)
);