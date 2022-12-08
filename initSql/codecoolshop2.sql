--
-- PostgreSQL database dump
--

-- Dumped from database version 15.1 (Ubuntu 15.1-1.pgdg22.04+1)
-- Dumped by pg_dump version 15.1 (Ubuntu 15.1-1.pgdg22.04+1)

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

--
-- Name: activity_seq; Type: SEQUENCE; Schema: public; Owner: maxim
--

CREATE SEQUENCE public.activity_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.activity_seq OWNER TO maxim;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: category; Type: TABLE; Schema: public; Owner: maxim
--

CREATE TABLE public.category (
    category_id integer DEFAULT nextval('public.activity_seq'::regclass) NOT NULL,
    category_name character varying(1000) NOT NULL,
    category_department character varying(1000),
    category_description character varying(10000)
);


ALTER TABLE public.category OWNER TO maxim;

--
-- Name: products; Type: TABLE; Schema: public; Owner: maxim
--

CREATE TABLE public.products (
    product_id integer DEFAULT nextval('public.activity_seq'::regclass) NOT NULL,
    product_name character varying(100) NOT NULL,
    product_defaultprice integer NOT NULL,
    product_currency character varying(100) NOT NULL,
    product_description character varying(1000000),
    product_category_id integer NOT NULL,
    product_supplier_id integer NOT NULL,
    product_picture character varying(1000)
);


ALTER TABLE public.products OWNER TO maxim;

--
-- Name: supplier; Type: TABLE; Schema: public; Owner: maxim
--

CREATE TABLE public.supplier (
    suplier_id integer DEFAULT nextval('public.activity_seq'::regclass) NOT NULL,
    suplier_name character varying(1000) NOT NULL,
    suplier_description character varying(10000)
);


ALTER TABLE public.supplier OWNER TO maxim;

--
-- Name: users; Type: TABLE; Schema: public; Owner: maxim
--

CREATE TABLE public.users (
    user_id integer DEFAULT nextval('public.activity_seq'::regclass) NOT NULL,
    username character varying(100) NOT NULL,
    password character varying(100) NOT NULL,
    email character varying(100) NOT NULL
);


ALTER TABLE public.users OWNER TO maxim;

--
-- Data for Name: category; Type: TABLE DATA; Schema: public; Owner: maxim
--

COPY public.category (category_id, category_name, category_department, category_description) FROM stdin;
8	Laptops	\N	\N
9	Speakers	\N	\N
\.


--
-- Data for Name: products; Type: TABLE DATA; Schema: public; Owner: maxim
--

COPY public.products (product_id, product_name, product_defaultprice, product_currency, product_description, product_category_id, product_supplier_id, product_picture) FROM stdin;
23	Lenovo ThinkPad X1 Carbon Gen 9	2111	USD	Intel Core i5-1135G7, 16GB RAM, 256GB SSD, Intel Iris Xe Graphics, Windows 10 Pro	8	10	lenovo
24	Dell XPS 13 Plus 9320	2360	USD	Superb 4K screen and powerful 12th-generation Intel Core i7 processor, plus 16GB (gigabytes) of RAM (memory) and 512GB of very fast solid-state drive (SSD) storage.	8	11	dell
25	ASUS ROG Flow X13	1200	USD	Powerful gaming has never been so flexible with up to the latest Ryzen 9 5980HS CPU and GeForce RTX 3080	8	12	asus
26	Lenovo Legion 5 Pro	1503	USD	Inside the Legion 5 Pro is an Intel Core i7–12700H with 14 cores and 20 threads, an Nvidia RTX 3070 Ti with 8GB of GDDR6 memory, a 512GB NVMe SSD for storage and 16GB of DDR5 4,800Mhz memory. 	8	10	lenovo2
27	Dell XPS 17	1849	USD	Dell XPS 17 laptop featuring 12th Gen Intel Core processors, up to NVIDIA GeForce RTX 3060 graphics. 	8	11	dell2
28	Wilson Benesch Precision P2.0	14000	USD	The Precision P2.0 are available in a wide range of finish options for the side panels, varying from real wood veneers to painted (including a rather fetching green), so there`s likely to be something that suits most living environments.	9	13	wilsonbenesch
29	Fyne F501	1499	USD	The F500 range consists of the F500 standmounting design (plus matching stands), two pairs of floorstanders (these F501s and the bigger, more expensive F502s), a centre speaker (F500C) and the F500FX dipole intended for use as rear speakers in a surround-sound set-up. And Fyne has a range of three subwoofers too.	9	14	fyne
30	Fyne Audio F302i	745	USD	These are accommodating speakers in more than just size, too. They’re pretty open about room positioning so long as you don’t stuff them into a corner or right up against a wall. A little bit of toe-in towards the main listening position helps to add a degree of focus to the presentation, but the precise amount isn’t particularly critical to get a good, stable stereo image.	9	14	fyne2
\.


--
-- Data for Name: supplier; Type: TABLE DATA; Schema: public; Owner: maxim
--

COPY public.supplier (suplier_id, suplier_name, suplier_description) FROM stdin;
10	Lenovo	\N
11	Dell	\N
12	Asus	\N
13	Wilson Benesch	\N
14	Fyne	\N
\.


--
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: maxim
--

COPY public.users (user_id, username, password, email) FROM stdin;
1	ghffh	fghfgh	fghfgh
2	ghffh	fghfgh	fghfgh
3	ghffh	dd	fghfgh
4	salut	salut	salut
5	buna	buna	buna
6	salut	salut	salut
\.


--
-- Name: activity_seq; Type: SEQUENCE SET; Schema: public; Owner: maxim
--

SELECT pg_catalog.setval('public.activity_seq', 30, true);


--
-- Name: category category_pkey; Type: CONSTRAINT; Schema: public; Owner: maxim
--

ALTER TABLE ONLY public.category
    ADD CONSTRAINT category_pkey PRIMARY KEY (category_id);


--
-- Name: products products_pkey; Type: CONSTRAINT; Schema: public; Owner: maxim
--

ALTER TABLE ONLY public.products
    ADD CONSTRAINT products_pkey PRIMARY KEY (product_id);


--
-- Name: supplier supplier_pkey; Type: CONSTRAINT; Schema: public; Owner: maxim
--

ALTER TABLE ONLY public.supplier
    ADD CONSTRAINT supplier_pkey PRIMARY KEY (suplier_id);


--
-- Name: users users_pkey; Type: CONSTRAINT; Schema: public; Owner: maxim
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (user_id);


--
-- PostgreSQL database dump complete
--

