create schema if not exists sayings;

DROP TABLE IF EXISTS sayings.cat_line;
CREATE TABLE IF NOT EXISTS sayings.cat_line
(
    id     serial primary key,
    saying varchar(255)
);

create schema if not exists story;

DROP TABLE IF EXISTS story.paragraph;
CREATE TABLE IF NOT EXISTS story.paragraph
(
    id   serial primary key,
    text varchar
);
