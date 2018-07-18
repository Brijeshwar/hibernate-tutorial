create table review (
	id int(11) not null auto_increment,
    comment varchar(256) default null,
    course_id int(11) default null,
    primary key (id),
    key fk_course_id_idx (course_id),
    constraint fk_course
    foreign key (course_id)
    references course(id)
)