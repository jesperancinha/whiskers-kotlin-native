create schema if not exists sayings;

CREATE TABLE sayings.cat_lines
(
    id     serial primary key,
    saying varchar(255)
);