create table course (
	id int(11) not null auto_increment,
    title varchar(128) default null,
    instructor_id int(11) default null,
	primary key (id),
    unique key TITLE_UNIQUE (title),
    key FK_INSTRUCTOR_idx (instructor_id),
    constraint FK_INSTRUCTOR FOREIGN KEY (instructor_id) REFERENCES instructor (id)
    
    on delete NO ACTION on update NO ACTION
);