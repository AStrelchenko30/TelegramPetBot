-- liquibase formated sql
-- changeset sgorban:1
CREATE TABLE client
(
    id              SERIAL primary key NOT NULL,
    chat_id         BIGINT,
    mail            TEXT,
    name            TEXT,
    passport_number BIGINT,
    surname         TEXT
);

-- changeset sgorban:2
CREATE TABLE cat
(
    id    int8 primary key not null,
    name  TEXT,
    owner int4 references client (id)
);

CREATE TABLE cat_photo
(
    id         int8 primary key not null,
    file_path  TEXT,
    file_size  BIGINT,
    media_type TEXT,
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
    client    BIGSERIAL references client (id)
);

CREATE TABLE dog
(
    id    int8 primary key not null,
    name  TEXT,
    owner int4 references client (id)
);


CREATE TABLE dog_photo
(
    id         int8 primary key not null,
    file_path  TEXT,
    file_size  BIGINT,
    media_type TEXT,
    data       bytea,
    dog_id     BIGSERIAL references dog (id)
);

CREATE TABLE dog_report
(
    id        int8 primary key not null,
    condition TEXT,
    ration    TEXT,
    changes   TEXT,
    dog_photo BIGSERIAL references dog_photo (id),
    client    BIGSERIAL references client (id)
);
CREATE TABLE volunteer
(
    id      int8 primary key not null,
    name    TEXT,
    surname TEXT,
    mail    TEXT
);