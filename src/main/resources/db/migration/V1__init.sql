CREATE TABLE actor
(
    id INTEGER NOT NULL,
    CONSTRAINT pk_actor PRIMARY KEY (id)
);

CREATE TABLE actor_movie_meta_info
(
    actor_id           INTEGER NOT NULL,
    movie_meta_info_id INTEGER NOT NULL,
    CONSTRAINT pk_actor_movie_meta_info PRIMARY KEY (actor_id, movie_meta_info_id)
);

CREATE TABLE customer
(
    id       INTEGER NOT NULL,
    email    VARCHAR(255),
    login    VARCHAR(255),
    password VARCHAR(255),
    role     VARCHAR(255),
    status   VARCHAR(255),
    CONSTRAINT pk_customer PRIMARY KEY (id)
);

CREATE TABLE director
(
    id INTEGER NOT NULL,
    CONSTRAINT pk_director PRIMARY KEY (id)
);

CREATE TABLE director_movie_meta_info
(
    director_id        INTEGER NOT NULL,
    movie_meta_info_id INTEGER NOT NULL
);

CREATE TABLE highlight
(
    id        INTEGER GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    author_id INTEGER                                  NOT NULL,
    name      VARCHAR(255),
    CONSTRAINT pk_highlight PRIMARY KEY (id)
);

CREATE TABLE highlight_author
(
    id       INTEGER NOT NULL,
    nickname VARCHAR(255),
    CONSTRAINT pk_highlightauthor PRIMARY KEY (id)
);

CREATE TABLE highlight_metainfo
(
    id           INTEGER GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    highlight_id INTEGER                                  NOT NULL,
    movie_id     INTEGER                                  NOT NULL,
    data         OID,
    CONSTRAINT pk_highlightmetainfo PRIMARY KEY (id)
);

CREATE TABLE liked
(
    customer_id           INTEGER NOT NULL,
    highlight_metainfo_id INTEGER NOT NULL,
    CONSTRAINT pk_liked PRIMARY KEY (customer_id, highlight_metainfo_id)
);

CREATE TABLE movie
(
    id         INTEGER GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    name       VARCHAR(255),
    genre      VARCHAR(255),
    rating     FLOAT,
    age_rating INTEGER,
    CONSTRAINT pk_movie PRIMARY KEY (id)
);

CREATE TABLE movie_meta_info
(
    id          INTEGER NOT NULL,
    film_id     INTEGER NOT NULL,
    country     VARCHAR(255),
    description VARCHAR(255),
    CONSTRAINT pk_moviemetainfo PRIMARY KEY (id)
);

CREATE TABLE person
(
    id         INTEGER GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    name       VARCHAR(255),
    surname    VARCHAR(255),
    patronymic VARCHAR(255),
    birthdate  TIMESTAMP WITHOUT TIME ZONE,
    gender     VARCHAR(255),
    CONSTRAINT pk_person PRIMARY KEY (id)
);

CREATE TABLE preview
(
    id                 INTEGER NOT NULL,
    height             INTEGER,
    width              INTEGER,
    tag                VARCHAR(255),
    link               VARCHAR(255),
    movie_meta_info_id INTEGER,
    CONSTRAINT pk_preview PRIMARY KEY (id)
);

CREATE TABLE subscribers
(
    customer_id         INTEGER NOT NULL,
    highlight_author_id INTEGER NOT NULL,
    CONSTRAINT pk_subscribers PRIMARY KEY (customer_id, highlight_author_id)
);

ALTER TABLE highlight
    ADD CONSTRAINT uc_highlight_author UNIQUE (author_id);

ALTER TABLE highlight_metainfo
    ADD CONSTRAINT uc_highlightmetainfo_highlight UNIQUE (highlight_id);

ALTER TABLE highlight_metainfo
    ADD CONSTRAINT uc_highlightmetainfo_movie UNIQUE (movie_id);

ALTER TABLE movie_meta_info
    ADD CONSTRAINT uc_moviemetainfo_film UNIQUE (film_id);

ALTER TABLE actor
    ADD CONSTRAINT FK_ACTOR_ON_ID FOREIGN KEY (id) REFERENCES person (id);

ALTER TABLE customer
    ADD CONSTRAINT FK_CUSTOMER_ON_ID FOREIGN KEY (id) REFERENCES person (id);

ALTER TABLE director
    ADD CONSTRAINT FK_DIRECTOR_ON_ID FOREIGN KEY (id) REFERENCES person (id);

ALTER TABLE highlight_author
    ADD CONSTRAINT FK_HIGHLIGHTAUTHOR_ON_ID FOREIGN KEY (id) REFERENCES customer (id);

ALTER TABLE highlight_metainfo
    ADD CONSTRAINT FK_HIGHLIGHTMETAINFO_ON_HIGHLIGHT FOREIGN KEY (highlight_id) REFERENCES highlight (id);

ALTER TABLE highlight_metainfo
    ADD CONSTRAINT FK_HIGHLIGHTMETAINFO_ON_MOVIE FOREIGN KEY (movie_id) REFERENCES movie (id);

ALTER TABLE highlight
    ADD CONSTRAINT FK_HIGHLIGHT_ON_AUTHOR FOREIGN KEY (author_id) REFERENCES highlight_author (id);

ALTER TABLE movie_meta_info
    ADD CONSTRAINT FK_MOVIEMETAINFO_ON_FILM FOREIGN KEY (film_id) REFERENCES movie (id);

ALTER TABLE actor_movie_meta_info
    ADD CONSTRAINT fk_actmovmetinf_on_actor FOREIGN KEY (actor_id) REFERENCES actor (id);

ALTER TABLE actor_movie_meta_info
    ADD CONSTRAINT fk_actmovmetinf_on_movie_meta_info FOREIGN KEY (movie_meta_info_id) REFERENCES movie_meta_info (id);

ALTER TABLE director_movie_meta_info
    ADD CONSTRAINT fk_dirmovmetinf_on_director FOREIGN KEY (director_id) REFERENCES director (id);

ALTER TABLE director_movie_meta_info
    ADD CONSTRAINT fk_dirmovmetinf_on_movie_meta_info FOREIGN KEY (movie_meta_info_id) REFERENCES movie_meta_info (id);

ALTER TABLE liked
    ADD CONSTRAINT fk_liked_on_customer FOREIGN KEY (customer_id) REFERENCES customer (id);

ALTER TABLE liked
    ADD CONSTRAINT fk_liked_on_highlight_metainfo FOREIGN KEY (highlight_metainfo_id) REFERENCES highlight_metainfo (id);

ALTER TABLE subscribers
    ADD CONSTRAINT fk_sub_on_customer FOREIGN KEY (customer_id) REFERENCES customer (id);

ALTER TABLE subscribers
    ADD CONSTRAINT fk_sub_on_highlight_author FOREIGN KEY (highlight_author_id) REFERENCES highlight_author (id);