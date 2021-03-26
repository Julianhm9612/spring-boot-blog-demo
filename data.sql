CREATE TABLE public."user_app"
(
    id bigint,
    name character varying,
    surname character varying,
    PRIMARY KEY (id)
);

ALTER TABLE public."user_app"
    OWNER to investigacion;

CREATE SEQUENCE public.seq_user;

ALTER SEQUENCE public.seq_user
    OWNER TO investigacion;

INSERT INTO public."user_app"(
	id, name, surname)
	VALUES (nextval('seq_user'), 'Ana', 'Martinez');

insert into post (id, title, body, user_id)
	values(nextval('seq_post'), 'Prueba', 'Esta es una prueba', 1)

CREATE OR REPLACE FUNCTION find_posts_by_user(idUser numeric)
	RETURNS TABLE(id bigint, title character varying, userId bigint)AS $$
BEGIN
    RETURN QUERY SELECT p.id, p.title, p.user_id as userId FROM post as p WHERE p.user_id = idUser ORDER BY p.id;
END; $$

LANGUAGE plpgsql;

select * FROM find_posts_by_user(3);
