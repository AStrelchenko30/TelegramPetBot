-- liquibase formated sql
-- changeset sgorban:1
CREATE TABLE client (
                                   id SERIAL primary key not null ,
                                   chat_id BIGINT,
                                   mail TEXT,
                                   name TEXT,
                                   passport_number BIGINT,
                                   surname TEXT
                                  )