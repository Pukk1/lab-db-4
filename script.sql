DROP TABLE movie CASCADE;
DROP TABLE movie_meta_info CASCADE;
DROP TABLE preview CASCADE;
DROP TABLE person CASCADE;
DROP TABLE director CASCADE;
DROP TABLE director_movie_meta_info CASCADE;
DROP TABLE actor CASCADE;
DROP TABLE actor_movie_meta_info CASCADE;
DROP TABLE customer CASCADE;
DROP TABLE highlight_author CASCADE;
DROP TABLE highlight CASCADE;
DROP TABLE highlight_metainfo CASCADE;
DROP TABLE liked CASCADE;
DROP TABLE subscribers CASCADE;
DROP TYPE GENDER CASCADE;

CREATE TYPE GENDER AS ENUM (
  'MALE',
  'FEMALE'
);

CREATE TABLE movie (
                       id serial PRIMARY KEY,
                       name varchar(255) NOT NULL,
                       genre varchar(255) NOT NULL,
                       rating float4 DEFAULT 0.0,
                       age_rating int DEFAULT 0
                           CHECK(rating >= 0.0 AND rating <= 100.0)
);

CREATE TABLE movie_meta_info (
                                 id serial PRIMARY KEY,
                                 film_id int UNIQUE NOT NULL REFERENCES movie ON DELETE CASCADE,
                                 country varchar(255) DEFAULT 'International',
                                 description text
);

CREATE TABLE preview (
                         id serial PRIMARY KEY,
                         height int,
                         width int,
                         tag varchar(20),
                         link text DEFAULT 'https://example-server/',
                         movie_meta_info_id int NOT NULL REFERENCES movie_meta_info ON DELETE CASCADE
);

CREATE TABLE person (
                        id serial PRIMARY KEY,
                        name varchar(255) NOT NULL,
                        surname varchar(255) NOT NULL,
                        patronymic varchar(255),
                        birthdate timestamp(6) NOT NULL,
                        gender varchar(255) NOT NULL
);

CREATE TABLE director (
    id int PRIMARY KEY REFERENCES person ON DELETE CASCADE
);

CREATE TABLE director_movie_meta_info (
                                          director_id int NOT NULL REFERENCES director ON DELETE CASCADE,
                                          movie_meta_info_id int NOT NULL REFERENCES movie_meta_info ON DELETE CASCADE,
                                          PRIMARY KEY (director_id, movie_meta_info_id)
);

CREATE TABLE actor (
    id int PRIMARY KEY REFERENCES person ON DELETE CASCADE
);

CREATE TABLE actor_movie_meta_info (
                                       actor_id int NOT NULL REFERENCES actor ON DELETE CASCADE,
                                       movie_meta_info_id int NOT NULL REFERENCES movie_meta_info ON DELETE CASCADE,
                                       PRIMARY KEY (actor_id, movie_meta_info_id)
);

CREATE TABLE customer (
                          id int PRIMARY KEY REFERENCES person ON DELETE CASCADE,
                          email varchar(255) NOT NULL,
                          login varchar(255) UNIQUE NOT NULL,
                          password varchar(255) NOT NULL,
                          role varchar(255) NOT NULL,
                          status varchar(255) NOT NULL
);

CREATE TABLE highlight_author (
                                  id int PRIMARY KEY REFERENCES customer ON DELETE CASCADE,
                                  nickname varchar(255)
);

CREATE TABLE highlight (
                           id serial PRIMARY KEY,
                           name varchar(50) NOT NULL,
                           author_id int UNIQUE NOT NULL REFERENCES highlight_author ON DELETE CASCADE
);

CREATE TABLE highlight_metainfo (
                                    id serial PRIMARY KEY,
                                    data oid NOT NULL,
                                    highlight_id int UNIQUE NOT NULL REFERENCES highlight ON DELETE CASCADE,
                                    movie_id int NOT NULL REFERENCES movie ON DELETE CASCADE
);

CREATE TABLE liked (
                       highlight_metainfo_id int NOT NULL REFERENCES highlight_metainfo ON DELETE CASCADE,
                       customer_id int NOT NULL REFERENCES customer ON DELETE CASCADE,
                       PRIMARY KEY (highlight_metainfo_id, customer_id)
);

CREATE TABLE subscribers (
                             customer_id int NOT NULL REFERENCES customer ON DELETE CASCADE,
                             highlight_author_id int NOT NULL REFERENCES highlight_author ON DELETE CASCADE,
                             PRIMARY KEY (customer_id, highlight_author_id)
);

CREATE INDEX film_id_index_movie_meta_info
    ON movie_meta_info(film_id);

CREATE INDEX ganre_index_movie
    ON movie(genre);

CREATE INDEX login_index_customer
    ON customer(login);

CREATE INDEX email_index_customer
    ON customer(email);

CREATE OR REPLACE FUNCTION set_hightlight_author_nickname_as_login()
RETURNS TRIGGER AS $$
BEGIN
UPDATE highlight_author
SET nickname = c.login
    FROM customer c
WHERE highlight_author.id = c.id
  and NEW.id = highlight_author.id
  and NEW.nickname is NULL OR NEW.nickname = '';
RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER hightlight_author_nickname
    AFTER INSERT ON highlight_author
    FOR EACH ROW EXECUTE PROCEDURE set_hightlight_author_nickname_as_login();

CREATE OR REPLACE FUNCTION set_movie_meta_info_description_default()
RETURNS TRIGGER AS $$
BEGIN
UPDATE movie_meta_info
SET description = 'Discription for this film not exist'
WHERE movie_meta_info.id = NEW.id
  and (NEW.description is NULL OR NEW.description = '');
RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER movie_meta_info_description_default
    AFTER INSERT ON movie_meta_info
    FOR EACH ROW EXECUTE PROCEDURE set_movie_meta_info_description_default();
