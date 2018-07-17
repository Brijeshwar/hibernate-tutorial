create table instructor (
	id int(11) not null auto_increment,
    first_name varchar(45) default null,
    last_name varchar(45) default null,
    email varchar(45) not null,
    instructor_detail_id int(11) default null,
	primary key (id), foreign key (instructor_detail_id)  references instructor_detail (id)
);