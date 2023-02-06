-- liquibase formated sql
-- changeset sgorban:1
CREATE TABLE client
(
    id              SERIAL primary key not null,
    chat_id         BIGINT,
    phone            TEXT,
    name            TEXT,
    surname         TEXT,
    time_registered TIMESTAMP
);

-- changeset sgorban:2
CREATE TABLE cat
(
    id    int8 primary key not null,
    name  TEXT,
    owner INTEGER references client (id)
);

CREATE TABLE cat_photo
(
    id         int8 primary key not null,
    data       bytea,
    cat_id     BIGSERIAL references cat (id)
);

CREATE TABLE cat_report
(
    id        int8 primary key not null,
    condition TEXT,
    ration    TEXT,
    changes   TEXT,
    cat_photo BIGSERIAL references cat_photo (id),
    client    INTEGER references client (id)
);

CREATE TABLE dog
(
    id    SERIAL primary key not null,
    name  TEXT,
    owner INTEGER references client (id)
);


CREATE TABLE dog_photo
(
    id     SERIAL primary key NOT NULL,
    data   bytea,
    dog_id INTEGER references dog (id)
);

CREATE TABLE dog_report
(
    id        BIGSERIAL primary key not null,
    condition TEXT,
    ration    TEXT,
    changes   TEXT,
    dog_photo INTEGER references dog_photo (id),
    client    INTEGER references client (id)
);
CREATE TABLE volunteer
(
    id      int8 primary key not null,
    name    TEXT,
    surname TEXT,
    mail    TEXT
);
CREATE TABLE condition
(
    id SERIAL primary key not null,
    chat_id int8,
    condition_for_report int4
);