USE springboot;
CREATE TABLE `Person` (
    `id` int unsigned NOT NULL auto_increment,
    `Lastname` varchar(50) default NULL,
    `Firstname` varchar(50) default NULL,
    PRIMARY KEY (`id`)
);