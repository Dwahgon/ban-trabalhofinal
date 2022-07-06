--
-- PostgreSQL database dump
--

-- Dumped from database version 10.21 (Ubuntu 10.21-1.pgdg18.04+1)
-- Dumped by pg_dump version 14.3 (Ubuntu 14.3-1.pgdg18.04+1)

-- Started on 2022-07-06 15:18:15 -03

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

--
-- TOC entry 203 (class 1259 OID 16494)
-- Name: albuns; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.albuns (
    id integer NOT NULL,
    nome character varying(50) NOT NULL,
    datalancamento date,
    fotocapa bytea,
    idpublicador integer NOT NULL,
    precodisco real NOT NULL
);


ALTER TABLE public.albuns OWNER TO postgres;

--
-- TOC entry 202 (class 1259 OID 16492)
-- Name: albuns_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.albuns_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.albuns_id_seq OWNER TO postgres;

--
-- TOC entry 3050 (class 0 OID 0)
-- Dependencies: 202
-- Name: albuns_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.albuns_id_seq OWNED BY public.albuns.id;


--
-- TOC entry 199 (class 1259 OID 16462)
-- Name: artistas; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.artistas (
    id integer NOT NULL,
    nome character varying(50) NOT NULL,
    biografia character varying(512),
    foto bytea,
    idpublicador integer NOT NULL
);


ALTER TABLE public.artistas OWNER TO postgres;

--
-- TOC entry 198 (class 1259 OID 16460)
-- Name: artistas_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.artistas_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.artistas_id_seq OWNER TO postgres;

--
-- TOC entry 3051 (class 0 OID 0)
-- Dependencies: 198
-- Name: artistas_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.artistas_id_seq OWNED BY public.artistas.id;


--
-- TOC entry 201 (class 1259 OID 16478)
-- Name: bandas; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.bandas (
    id integer NOT NULL,
    nome character varying(50) NOT NULL,
    dataformacao date,
    foto bytea,
    idpublicador integer NOT NULL
);


ALTER TABLE public.bandas OWNER TO postgres;

--
-- TOC entry 200 (class 1259 OID 16476)
-- Name: bandas_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.bandas_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.bandas_id_seq OWNER TO postgres;

--
-- TOC entry 3052 (class 0 OID 0)
-- Dependencies: 200
-- Name: bandas_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.bandas_id_seq OWNED BY public.bandas.id;


--
-- TOC entry 213 (class 1259 OID 16621)
-- Name: composicoesalbum; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.composicoesalbum (
    idalbum integer NOT NULL,
    idmusica integer NOT NULL,
    ordem integer NOT NULL
);


ALTER TABLE public.composicoesalbum OWNER TO postgres;

--
-- TOC entry 211 (class 1259 OID 16591)
-- Name: composicoesbanda; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.composicoesbanda (
    idartista integer NOT NULL,
    idbanda integer NOT NULL
);


ALTER TABLE public.composicoesbanda OWNER TO postgres;

--
-- TOC entry 215 (class 1259 OID 16651)
-- Name: composicoesplaylist; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.composicoesplaylist (
    idplaylist integer NOT NULL,
    idmusica integer NOT NULL,
    ordem integer NOT NULL
);


ALTER TABLE public.composicoesplaylist OWNER TO postgres;

--
-- TOC entry 197 (class 1259 OID 16440)
-- Name: contas; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.contas (
    id integer NOT NULL,
    datanascimento date NOT NULL,
    nome character varying(50) NOT NULL,
    email character varying(40) NOT NULL,
    senha character varying(512) NOT NULL,
    fotodepefil bytea[],
    tipo integer NOT NULL
);


ALTER TABLE public.contas OWNER TO postgres;

--
-- TOC entry 196 (class 1259 OID 16438)
-- Name: contas_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.contas_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.contas_id_seq OWNER TO postgres;

--
-- TOC entry 3053 (class 0 OID 0)
-- Dependencies: 196
-- Name: contas_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.contas_id_seq OWNED BY public.contas.id;


--
-- TOC entry 214 (class 1259 OID 16636)
-- Name: curtidas; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.curtidas (
    idusuario integer NOT NULL,
    idmusica integer NOT NULL
);


ALTER TABLE public.curtidas OWNER TO postgres;

--
-- TOC entry 210 (class 1259 OID 16571)
-- Name: encomendas; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.encomendas (
    idusuario integer NOT NULL,
    idalbum integer NOT NULL,
    dthoracompra timestamp without time zone NOT NULL,
    qnt integer NOT NULL,
    precofrete real NOT NULL,
    cep character varying(8) NOT NULL,
    numero integer NOT NULL,
    complemento character varying(20) NOT NULL,
    idtransportadora integer NOT NULL
);


ALTER TABLE public.encomendas OWNER TO postgres;

--
-- TOC entry 205 (class 1259 OID 16510)
-- Name: musicas; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.musicas (
    id integer NOT NULL,
    titulo character varying(50) NOT NULL,
    qntvistos integer NOT NULL,
    letra character varying(512) NOT NULL,
    idpublicador integer
);


ALTER TABLE public.musicas OWNER TO postgres;

--
-- TOC entry 204 (class 1259 OID 16508)
-- Name: musicas_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.musicas_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.musicas_id_seq OWNER TO postgres;

--
-- TOC entry 3054 (class 0 OID 0)
-- Dependencies: 204
-- Name: musicas_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.musicas_id_seq OWNED BY public.musicas.id;


--
-- TOC entry 207 (class 1259 OID 16529)
-- Name: playlists; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.playlists (
    id integer NOT NULL,
    nome character varying(50) NOT NULL,
    usuariodono integer NOT NULL
);


ALTER TABLE public.playlists OWNER TO postgres;

--
-- TOC entry 206 (class 1259 OID 16527)
-- Name: playlists_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.playlists_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.playlists_id_seq OWNER TO postgres;

--
-- TOC entry 3055 (class 0 OID 0)
-- Dependencies: 206
-- Name: playlists_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.playlists_id_seq OWNED BY public.playlists.id;


--
-- TOC entry 212 (class 1259 OID 16606)
-- Name: producoes; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.producoes (
    idalbum integer NOT NULL,
    idbanda integer NOT NULL
);


ALTER TABLE public.producoes OWNER TO postgres;

--
-- TOC entry 209 (class 1259 OID 16565)
-- Name: transportadoras; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.transportadoras (
    id integer NOT NULL,
    nome character varying(50) NOT NULL,
    cep character varying(8),
    numero integer,
    complemento character varying(20)
);


ALTER TABLE public.transportadoras OWNER TO postgres;

--
-- TOC entry 208 (class 1259 OID 16563)
-- Name: transportadoras_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.transportadoras_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.transportadoras_id_seq OWNER TO postgres;

--
-- TOC entry 3056 (class 0 OID 0)
-- Dependencies: 208
-- Name: transportadoras_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.transportadoras_id_seq OWNED BY public.transportadoras.id;


--
-- TOC entry 2856 (class 2604 OID 16497)
-- Name: albuns id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.albuns ALTER COLUMN id SET DEFAULT nextval('public.albuns_id_seq'::regclass);


--
-- TOC entry 2854 (class 2604 OID 16465)
-- Name: artistas id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.artistas ALTER COLUMN id SET DEFAULT nextval('public.artistas_id_seq'::regclass);


--
-- TOC entry 2855 (class 2604 OID 16481)
-- Name: bandas id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.bandas ALTER COLUMN id SET DEFAULT nextval('public.bandas_id_seq'::regclass);


--
-- TOC entry 2853 (class 2604 OID 16443)
-- Name: contas id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.contas ALTER COLUMN id SET DEFAULT nextval('public.contas_id_seq'::regclass);


--
-- TOC entry 2857 (class 2604 OID 16513)
-- Name: musicas id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.musicas ALTER COLUMN id SET DEFAULT nextval('public.musicas_id_seq'::regclass);


--
-- TOC entry 2858 (class 2604 OID 16532)
-- Name: playlists id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.playlists ALTER COLUMN id SET DEFAULT nextval('public.playlists_id_seq'::regclass);


--
-- TOC entry 2859 (class 2604 OID 16568)
-- Name: transportadoras id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.transportadoras ALTER COLUMN id SET DEFAULT nextval('public.transportadoras_id_seq'::regclass);


--
-- TOC entry 3032 (class 0 OID 16494)
-- Dependencies: 203
-- Data for Name: albuns; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3028 (class 0 OID 16462)
-- Dependencies: 199
-- Data for Name: artistas; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3030 (class 0 OID 16478)
-- Dependencies: 201
-- Data for Name: bandas; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3042 (class 0 OID 16621)
-- Dependencies: 213
-- Data for Name: composicoesalbum; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3040 (class 0 OID 16591)
-- Dependencies: 211
-- Data for Name: composicoesbanda; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3044 (class 0 OID 16651)
-- Dependencies: 215
-- Data for Name: composicoesplaylist; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3026 (class 0 OID 16440)
-- Dependencies: 197
-- Data for Name: contas; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.contas VALUES (1, '2001-03-15', 'kawan', 'email@dominio.com', 'senha', NULL, 2);


--
-- TOC entry 3043 (class 0 OID 16636)
-- Dependencies: 214
-- Data for Name: curtidas; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3039 (class 0 OID 16571)
-- Dependencies: 210
-- Data for Name: encomendas; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3034 (class 0 OID 16510)
-- Dependencies: 205
-- Data for Name: musicas; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3036 (class 0 OID 16529)
-- Dependencies: 207
-- Data for Name: playlists; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3041 (class 0 OID 16606)
-- Dependencies: 212
-- Data for Name: producoes; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3038 (class 0 OID 16565)
-- Dependencies: 209
-- Data for Name: transportadoras; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3057 (class 0 OID 0)
-- Dependencies: 202
-- Name: albuns_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.albuns_id_seq', 1, false);


--
-- TOC entry 3058 (class 0 OID 0)
-- Dependencies: 198
-- Name: artistas_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.artistas_id_seq', 1, false);


--
-- TOC entry 3059 (class 0 OID 0)
-- Dependencies: 200
-- Name: bandas_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.bandas_id_seq', 1, false);


--
-- TOC entry 3060 (class 0 OID 0)
-- Dependencies: 196
-- Name: contas_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.contas_id_seq', 1, true);


--
-- TOC entry 3061 (class 0 OID 0)
-- Dependencies: 204
-- Name: musicas_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.musicas_id_seq', 1, true);


--
-- TOC entry 3062 (class 0 OID 0)
-- Dependencies: 206
-- Name: playlists_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.playlists_id_seq', 1, false);


--
-- TOC entry 3063 (class 0 OID 0)
-- Dependencies: 208
-- Name: transportadoras_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.transportadoras_id_seq', 1, false);


--
-- TOC entry 2867 (class 2606 OID 16502)
-- Name: albuns albuns_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.albuns
    ADD CONSTRAINT albuns_pkey PRIMARY KEY (id);


--
-- TOC entry 2863 (class 2606 OID 16470)
-- Name: artistas artistas_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.artistas
    ADD CONSTRAINT artistas_pkey PRIMARY KEY (id);


--
-- TOC entry 2865 (class 2606 OID 16486)
-- Name: bandas bandas_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.bandas
    ADD CONSTRAINT bandas_pkey PRIMARY KEY (id);


--
-- TOC entry 2881 (class 2606 OID 16625)
-- Name: composicoesalbum composicoesalbum_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.composicoesalbum
    ADD CONSTRAINT composicoesalbum_pkey PRIMARY KEY (idalbum, idmusica);


--
-- TOC entry 2877 (class 2606 OID 16595)
-- Name: composicoesbanda composicoesbanda_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.composicoesbanda
    ADD CONSTRAINT composicoesbanda_pkey PRIMARY KEY (idartista, idbanda);


--
-- TOC entry 2885 (class 2606 OID 16655)
-- Name: composicoesplaylist composicoesplaylist_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.composicoesplaylist
    ADD CONSTRAINT composicoesplaylist_pkey PRIMARY KEY (idplaylist, idmusica);


--
-- TOC entry 2861 (class 2606 OID 16448)
-- Name: contas contas_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.contas
    ADD CONSTRAINT contas_pkey PRIMARY KEY (id);


--
-- TOC entry 2883 (class 2606 OID 16640)
-- Name: curtidas curtidas_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.curtidas
    ADD CONSTRAINT curtidas_pkey PRIMARY KEY (idusuario, idmusica);


--
-- TOC entry 2875 (class 2606 OID 16575)
-- Name: encomendas encomendas_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.encomendas
    ADD CONSTRAINT encomendas_pkey PRIMARY KEY (idusuario, idalbum, dthoracompra);


--
-- TOC entry 2869 (class 2606 OID 16518)
-- Name: musicas musicas_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.musicas
    ADD CONSTRAINT musicas_pkey PRIMARY KEY (id);


--
-- TOC entry 2871 (class 2606 OID 16534)
-- Name: playlists playlists_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.playlists
    ADD CONSTRAINT playlists_pkey PRIMARY KEY (id);


--
-- TOC entry 2879 (class 2606 OID 16610)
-- Name: producoes producoes_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.producoes
    ADD CONSTRAINT producoes_pkey PRIMARY KEY (idalbum, idbanda);


--
-- TOC entry 2873 (class 2606 OID 16570)
-- Name: transportadoras transportadoras_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.transportadoras
    ADD CONSTRAINT transportadoras_pkey PRIMARY KEY (id);


--
-- TOC entry 2888 (class 2606 OID 16503)
-- Name: albuns albuns_idpublicador_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.albuns
    ADD CONSTRAINT albuns_idpublicador_fkey FOREIGN KEY (idpublicador) REFERENCES public.contas(id);


--
-- TOC entry 2886 (class 2606 OID 16471)
-- Name: artistas artistas_idpublicador_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.artistas
    ADD CONSTRAINT artistas_idpublicador_fkey FOREIGN KEY (idpublicador) REFERENCES public.contas(id);


--
-- TOC entry 2887 (class 2606 OID 16487)
-- Name: bandas bandas_idpublicador_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.bandas
    ADD CONSTRAINT bandas_idpublicador_fkey FOREIGN KEY (idpublicador) REFERENCES public.contas(id);


--
-- TOC entry 2898 (class 2606 OID 16626)
-- Name: composicoesalbum composicoesalbum_idalbum_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.composicoesalbum
    ADD CONSTRAINT composicoesalbum_idalbum_fkey FOREIGN KEY (idalbum) REFERENCES public.albuns(id);


--
-- TOC entry 2899 (class 2606 OID 16631)
-- Name: composicoesalbum composicoesalbum_idmusica_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.composicoesalbum
    ADD CONSTRAINT composicoesalbum_idmusica_fkey FOREIGN KEY (idmusica) REFERENCES public.musicas(id);


--
-- TOC entry 2894 (class 2606 OID 16596)
-- Name: composicoesbanda composicoesbanda_idartista_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.composicoesbanda
    ADD CONSTRAINT composicoesbanda_idartista_fkey FOREIGN KEY (idartista) REFERENCES public.artistas(id);


--
-- TOC entry 2895 (class 2606 OID 16601)
-- Name: composicoesbanda composicoesbanda_idbanda_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.composicoesbanda
    ADD CONSTRAINT composicoesbanda_idbanda_fkey FOREIGN KEY (idbanda) REFERENCES public.bandas(id);


--
-- TOC entry 2903 (class 2606 OID 16661)
-- Name: composicoesplaylist composicoesplaylist_idmusica_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.composicoesplaylist
    ADD CONSTRAINT composicoesplaylist_idmusica_fkey FOREIGN KEY (idmusica) REFERENCES public.musicas(id);


--
-- TOC entry 2902 (class 2606 OID 16656)
-- Name: composicoesplaylist composicoesplaylist_idplaylist_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.composicoesplaylist
    ADD CONSTRAINT composicoesplaylist_idplaylist_fkey FOREIGN KEY (idplaylist) REFERENCES public.playlists(id);


--
-- TOC entry 2901 (class 2606 OID 16646)
-- Name: curtidas curtidas_idmusica_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.curtidas
    ADD CONSTRAINT curtidas_idmusica_fkey FOREIGN KEY (idmusica) REFERENCES public.musicas(id);


--
-- TOC entry 2900 (class 2606 OID 16641)
-- Name: curtidas curtidas_idusuario_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.curtidas
    ADD CONSTRAINT curtidas_idusuario_fkey FOREIGN KEY (idusuario) REFERENCES public.contas(id);


--
-- TOC entry 2892 (class 2606 OID 16581)
-- Name: encomendas encomendas_idalbum_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.encomendas
    ADD CONSTRAINT encomendas_idalbum_fkey FOREIGN KEY (idalbum) REFERENCES public.albuns(id);


--
-- TOC entry 2893 (class 2606 OID 16586)
-- Name: encomendas encomendas_idtransportadora_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.encomendas
    ADD CONSTRAINT encomendas_idtransportadora_fkey FOREIGN KEY (idtransportadora) REFERENCES public.transportadoras(id);


--
-- TOC entry 2891 (class 2606 OID 16576)
-- Name: encomendas encomendas_idusuario_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.encomendas
    ADD CONSTRAINT encomendas_idusuario_fkey FOREIGN KEY (idusuario) REFERENCES public.contas(id);


--
-- TOC entry 2889 (class 2606 OID 16666)
-- Name: musicas musicas_idpublicador_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.musicas
    ADD CONSTRAINT musicas_idpublicador_fk FOREIGN KEY (idpublicador) REFERENCES public.contas(id);


--
-- TOC entry 2890 (class 2606 OID 16535)
-- Name: playlists playlists_usuariodono_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.playlists
    ADD CONSTRAINT playlists_usuariodono_fkey FOREIGN KEY (usuariodono) REFERENCES public.contas(id);


--
-- TOC entry 2896 (class 2606 OID 16611)
-- Name: producoes producoes_idalbum_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.producoes
    ADD CONSTRAINT producoes_idalbum_fkey FOREIGN KEY (idalbum) REFERENCES public.albuns(id);


--
-- TOC entry 2897 (class 2606 OID 16616)
-- Name: producoes producoes_idbanda_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.producoes
    ADD CONSTRAINT producoes_idbanda_fkey FOREIGN KEY (idbanda) REFERENCES public.bandas(id);


-- Completed on 2022-07-06 15:18:15 -03

--
-- PostgreSQL database dump complete
--

