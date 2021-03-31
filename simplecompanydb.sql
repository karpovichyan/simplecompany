--
-- PostgreSQL database dump
--

-- Dumped from database version 12.6 (Ubuntu 12.6-0ubuntu0.20.04.1)
-- Dumped by pg_dump version 12.6 (Ubuntu 12.6-0ubuntu0.20.04.1)

-- Started on 2021-03-31 17:25:56 +03

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

SET default_table_access_method = heap;

--
-- TOC entry 206 (class 1259 OID 16444)
-- Name: activity; Type: TABLE; Schema: public; Owner: yan
--

CREATE TABLE public.activity (
    id integer NOT NULL,
    hours integer NOT NULL,
    type_info text NOT NULL,
    timesheet_id integer NOT NULL
);


ALTER TABLE public.activity OWNER TO yan;

--
-- TOC entry 205 (class 1259 OID 16442)
-- Name: activity_id_seq; Type: SEQUENCE; Schema: public; Owner: yan
--

CREATE SEQUENCE public.activity_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.activity_id_seq OWNER TO yan;

--
-- TOC entry 3018 (class 0 OID 0)
-- Dependencies: 205
-- Name: activity_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: yan
--

ALTER SEQUENCE public.activity_id_seq OWNED BY public.activity.id;


--
-- TOC entry 207 (class 1259 OID 16451)
-- Name: activity_type_info; Type: TABLE; Schema: public; Owner: yan
--

CREATE TABLE public.activity_type_info (
    type text NOT NULL,
    ratio numeric NOT NULL
);


ALTER TABLE public.activity_type_info OWNER TO yan;

--
-- TOC entry 210 (class 1259 OID 16519)
-- Name: employees; Type: TABLE; Schema: public; Owner: yan
--

CREATE TABLE public.employees (
    id integer NOT NULL,
    first_name text NOT NULL,
    last_name text NOT NULL,
    position_info text
);


ALTER TABLE public.employees OWNER TO yan;

--
-- TOC entry 209 (class 1259 OID 16517)
-- Name: employees_id_seq; Type: SEQUENCE; Schema: public; Owner: yan
--

CREATE SEQUENCE public.employees_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.employees_id_seq OWNER TO yan;

--
-- TOC entry 3019 (class 0 OID 0)
-- Dependencies: 209
-- Name: employees_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: yan
--

ALTER SEQUENCE public.employees_id_seq OWNED BY public.employees.id;


--
-- TOC entry 202 (class 1259 OID 16425)
-- Name: job_info; Type: TABLE; Schema: public; Owner: yan
--

CREATE TABLE public.job_info (
    "position" text NOT NULL,
    salary_rate integer NOT NULL
);


ALTER TABLE public.job_info OWNER TO yan;

--
-- TOC entry 204 (class 1259 OID 16435)
-- Name: timesheet; Type: TABLE; Schema: public; Owner: yan
--

CREATE TABLE public.timesheet (
    id integer NOT NULL,
    month integer NOT NULL,
    year integer NOT NULL,
    total_hours integer NOT NULL,
    employees_id integer NOT NULL
);


ALTER TABLE public.timesheet OWNER TO yan;

--
-- TOC entry 208 (class 1259 OID 16497)
-- Name: timesheet_employees_id_seq; Type: SEQUENCE; Schema: public; Owner: yan
--

CREATE SEQUENCE public.timesheet_employees_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.timesheet_employees_id_seq OWNER TO yan;

--
-- TOC entry 3020 (class 0 OID 0)
-- Dependencies: 208
-- Name: timesheet_employees_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: yan
--

ALTER SEQUENCE public.timesheet_employees_id_seq OWNED BY public.timesheet.employees_id;


--
-- TOC entry 203 (class 1259 OID 16433)
-- Name: timesheet_id_seq; Type: SEQUENCE; Schema: public; Owner: yan
--

CREATE SEQUENCE public.timesheet_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.timesheet_id_seq OWNER TO yan;

--
-- TOC entry 3021 (class 0 OID 0)
-- Dependencies: 203
-- Name: timesheet_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: yan
--

ALTER SEQUENCE public.timesheet_id_seq OWNED BY public.timesheet.id;


--
-- TOC entry 2860 (class 2604 OID 16447)
-- Name: activity id; Type: DEFAULT; Schema: public; Owner: yan
--

ALTER TABLE ONLY public.activity ALTER COLUMN id SET DEFAULT nextval('public.activity_id_seq'::regclass);


--
-- TOC entry 2861 (class 2604 OID 16522)
-- Name: employees id; Type: DEFAULT; Schema: public; Owner: yan
--

ALTER TABLE ONLY public.employees ALTER COLUMN id SET DEFAULT nextval('public.employees_id_seq'::regclass);


--
-- TOC entry 2858 (class 2604 OID 16438)
-- Name: timesheet id; Type: DEFAULT; Schema: public; Owner: yan
--

ALTER TABLE ONLY public.timesheet ALTER COLUMN id SET DEFAULT nextval('public.timesheet_id_seq'::regclass);


--
-- TOC entry 2859 (class 2604 OID 16499)
-- Name: timesheet employees_id; Type: DEFAULT; Schema: public; Owner: yan
--

ALTER TABLE ONLY public.timesheet ALTER COLUMN employees_id SET DEFAULT nextval('public.timesheet_employees_id_seq'::regclass);


--
-- TOC entry 3008 (class 0 OID 16444)
-- Dependencies: 206
-- Data for Name: activity; Type: TABLE DATA; Schema: public; Owner: yan
--

COPY public.activity (id, hours, type_info, timesheet_id) FROM stdin;
\.


--
-- TOC entry 3009 (class 0 OID 16451)
-- Dependencies: 207
-- Data for Name: activity_type_info; Type: TABLE DATA; Schema: public; Owner: yan
--

COPY public.activity_type_info (type, ratio) FROM stdin;
absenteeism	0
vacation	0.9
sick leave	0.7
overtime	1.5
\.


--
-- TOC entry 3012 (class 0 OID 16519)
-- Dependencies: 210
-- Data for Name: employees; Type: TABLE DATA; Schema: public; Owner: yan
--

COPY public.employees (id, first_name, last_name, position_info) FROM stdin;
1	Yan	Karpovich	\N
3	Yan	Karpovich	\N
4	Yan	Karpovich	\N
5	Yan	Karpovich	\N
\.


--
-- TOC entry 3004 (class 0 OID 16425)
-- Dependencies: 202
-- Data for Name: job_info; Type: TABLE DATA; Schema: public; Owner: yan
--

COPY public.job_info ("position", salary_rate) FROM stdin;
Junior Developer	500
Software Engineer 2	1500
Software Engineer 1	2000
Software Engineer 3	1000
Senior Software Engineer 1	3000
Senior Software Engineer 2	2500
\.


--
-- TOC entry 3006 (class 0 OID 16435)
-- Dependencies: 204
-- Data for Name: timesheet; Type: TABLE DATA; Schema: public; Owner: yan
--

COPY public.timesheet (id, month, year, total_hours, employees_id) FROM stdin;
3	1	2021	168	2
4	1	2021	168	3
5	1	2021	168	4
7	1	2021	168	5
\.


--
-- TOC entry 3022 (class 0 OID 0)
-- Dependencies: 205
-- Name: activity_id_seq; Type: SEQUENCE SET; Schema: public; Owner: yan
--

SELECT pg_catalog.setval('public.activity_id_seq', 1, false);


--
-- TOC entry 3023 (class 0 OID 0)
-- Dependencies: 209
-- Name: employees_id_seq; Type: SEQUENCE SET; Schema: public; Owner: yan
--

SELECT pg_catalog.setval('public.employees_id_seq', 5, true);


--
-- TOC entry 3024 (class 0 OID 0)
-- Dependencies: 208
-- Name: timesheet_employees_id_seq; Type: SEQUENCE SET; Schema: public; Owner: yan
--

SELECT pg_catalog.setval('public.timesheet_employees_id_seq', 5, true);


--
-- TOC entry 3025 (class 0 OID 0)
-- Dependencies: 203
-- Name: timesheet_id_seq; Type: SEQUENCE SET; Schema: public; Owner: yan
--

SELECT pg_catalog.setval('public.timesheet_id_seq', 7, true);


--
-- TOC entry 2869 (class 2606 OID 16450)
-- Name: activity activity_pk; Type: CONSTRAINT; Schema: public; Owner: yan
--

ALTER TABLE ONLY public.activity
    ADD CONSTRAINT activity_pk PRIMARY KEY (id);


--
-- TOC entry 2871 (class 2606 OID 16458)
-- Name: activity_type_info activity_type_info_pk; Type: CONSTRAINT; Schema: public; Owner: yan
--

ALTER TABLE ONLY public.activity_type_info
    ADD CONSTRAINT activity_type_info_pk PRIMARY KEY (type);


--
-- TOC entry 2874 (class 2606 OID 16528)
-- Name: employees employees_pk; Type: CONSTRAINT; Schema: public; Owner: yan
--

ALTER TABLE ONLY public.employees
    ADD CONSTRAINT employees_pk PRIMARY KEY (id);


--
-- TOC entry 2863 (class 2606 OID 16432)
-- Name: job_info job_info_pk; Type: CONSTRAINT; Schema: public; Owner: yan
--

ALTER TABLE ONLY public.job_info
    ADD CONSTRAINT job_info_pk PRIMARY KEY ("position");


--
-- TOC entry 2866 (class 2606 OID 16441)
-- Name: timesheet timesheet_pk; Type: CONSTRAINT; Schema: public; Owner: yan
--

ALTER TABLE ONLY public.timesheet
    ADD CONSTRAINT timesheet_pk PRIMARY KEY (id);


--
-- TOC entry 2867 (class 1259 OID 16448)
-- Name: activity_id_uindex; Type: INDEX; Schema: public; Owner: yan
--

CREATE UNIQUE INDEX activity_id_uindex ON public.activity USING btree (id);


--
-- TOC entry 2872 (class 1259 OID 16526)
-- Name: employees_id_uindex; Type: INDEX; Schema: public; Owner: yan
--

CREATE UNIQUE INDEX employees_id_uindex ON public.employees USING btree (id);


--
-- TOC entry 2864 (class 1259 OID 16439)
-- Name: timesheet_id_uindex; Type: INDEX; Schema: public; Owner: yan
--

CREATE UNIQUE INDEX timesheet_id_uindex ON public.timesheet USING btree (id);


--
-- TOC entry 2877 (class 2606 OID 16529)
-- Name: employees position_info; Type: FK CONSTRAINT; Schema: public; Owner: yan
--

ALTER TABLE ONLY public.employees
    ADD CONSTRAINT position_info FOREIGN KEY (position_info) REFERENCES public.job_info("position");


--
-- TOC entry 2875 (class 2606 OID 16487)
-- Name: activity timesheet_id; Type: FK CONSTRAINT; Schema: public; Owner: yan
--

ALTER TABLE ONLY public.activity
    ADD CONSTRAINT timesheet_id FOREIGN KEY (timesheet_id) REFERENCES public.timesheet(id);


--
-- TOC entry 2876 (class 2606 OID 16492)
-- Name: activity type_info; Type: FK CONSTRAINT; Schema: public; Owner: yan
--

ALTER TABLE ONLY public.activity
    ADD CONSTRAINT type_info FOREIGN KEY (type_info) REFERENCES public.activity_type_info(type);


-- Completed on 2021-03-31 17:25:57 +03

--
-- PostgreSQL database dump complete
--

