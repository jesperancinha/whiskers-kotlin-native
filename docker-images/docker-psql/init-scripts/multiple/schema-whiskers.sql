create schema if not exists sayings;

CREATE TABLE IF NOT EXISTS sayings.cat_lines
(
    id     serial primary key,
    saying varchar(255)
);

create schema if not exists story;

CREATE TABLE IF NOT EXISTS story.paragraphs
(
    id   serial primary key,
    text varchar(255)
);
