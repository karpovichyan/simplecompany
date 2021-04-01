PGDMP         /                y            simplecompanydb #   12.6 (Ubuntu 12.6-0ubuntu0.20.04.1) #   12.6 (Ubuntu 12.6-0ubuntu0.20.04.1) &    �           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            �           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            �           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            �           1262    16387    simplecompanydb    DATABASE     �   CREATE DATABASE simplecompanydb WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'en_US.UTF-8' LC_CTYPE = 'en_US.UTF-8';
    DROP DATABASE simplecompanydb;
                postgres    false            �           0    0    DATABASE simplecompanydb    ACL     .   GRANT ALL ON DATABASE simplecompanydb TO yan;
                   postgres    false    3014            �            1259    16444    activity    TABLE     �   CREATE TABLE public.activity (
    id integer NOT NULL,
    hours integer NOT NULL,
    type_info text NOT NULL,
    timesheet_id integer NOT NULL
);
    DROP TABLE public.activity;
       public         heap    yan    false            �            1259    16442    activity_id_seq    SEQUENCE     �   CREATE SEQUENCE public.activity_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 &   DROP SEQUENCE public.activity_id_seq;
       public          yan    false    204            �           0    0    activity_id_seq    SEQUENCE OWNED BY     C   ALTER SEQUENCE public.activity_id_seq OWNED BY public.activity.id;
          public          yan    false    203            �            1259    16451    activity_type_info    TABLE     _   CREATE TABLE public.activity_type_info (
    type text NOT NULL,
    ratio numeric NOT NULL
);
 &   DROP TABLE public.activity_type_info;
       public         heap    yan    false            �            1259    16519    employee    TABLE     �   CREATE TABLE public.employee (
    id integer NOT NULL,
    first_name text NOT NULL,
    last_name text NOT NULL,
    position_info text
);
    DROP TABLE public.employee;
       public         heap    yan    false            �            1259    16517    employees_id_seq    SEQUENCE     �   CREATE SEQUENCE public.employees_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 '   DROP SEQUENCE public.employees_id_seq;
       public          yan    false    207            �           0    0    employees_id_seq    SEQUENCE OWNED BY     D   ALTER SEQUENCE public.employees_id_seq OWNED BY public.employee.id;
          public          yan    false    206            �            1259    16425    job_info    TABLE     \   CREATE TABLE public.job_info (
    "position" text NOT NULL,
    salary integer NOT NULL
);
    DROP TABLE public.job_info;
       public         heap    yan    false            �            1259    16541 	   timesheet    TABLE     �   CREATE TABLE public.timesheet (
    id integer NOT NULL,
    month integer NOT NULL,
    year integer NOT NULL,
    total_hours integer NOT NULL,
    employee_id integer NOT NULL
);
    DROP TABLE public.timesheet;
       public         heap    yan    false            �            1259    16539    timesheet_id_seq    SEQUENCE     �   CREATE SEQUENCE public.timesheet_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 '   DROP SEQUENCE public.timesheet_id_seq;
       public          yan    false    209            �           0    0    timesheet_id_seq    SEQUENCE OWNED BY     E   ALTER SEQUENCE public.timesheet_id_seq OWNED BY public.timesheet.id;
          public          yan    false    208            (           2604    16447    activity id    DEFAULT     j   ALTER TABLE ONLY public.activity ALTER COLUMN id SET DEFAULT nextval('public.activity_id_seq'::regclass);
 :   ALTER TABLE public.activity ALTER COLUMN id DROP DEFAULT;
       public          yan    false    204    203    204            )           2604    16522    employee id    DEFAULT     k   ALTER TABLE ONLY public.employee ALTER COLUMN id SET DEFAULT nextval('public.employees_id_seq'::regclass);
 :   ALTER TABLE public.employee ALTER COLUMN id DROP DEFAULT;
       public          yan    false    207    206    207            *           2604    16544    timesheet id    DEFAULT     l   ALTER TABLE ONLY public.timesheet ALTER COLUMN id SET DEFAULT nextval('public.timesheet_id_seq'::regclass);
 ;   ALTER TABLE public.timesheet ALTER COLUMN id DROP DEFAULT;
       public          yan    false    209    208    209            �          0    16444    activity 
   TABLE DATA           F   COPY public.activity (id, hours, type_info, timesheet_id) FROM stdin;
    public          yan    false    204   (       �          0    16451    activity_type_info 
   TABLE DATA           9   COPY public.activity_type_info (type, ratio) FROM stdin;
    public          yan    false    205   �(       �          0    16519    employee 
   TABLE DATA           L   COPY public.employee (id, first_name, last_name, position_info) FROM stdin;
    public          yan    false    207   �(       �          0    16425    job_info 
   TABLE DATA           6   COPY public.job_info ("position", salary) FROM stdin;
    public          yan    false    202   �)       �          0    16541 	   timesheet 
   TABLE DATA           N   COPY public.timesheet (id, month, year, total_hours, employee_id) FROM stdin;
    public          yan    false    209   �)       �           0    0    activity_id_seq    SEQUENCE SET     =   SELECT pg_catalog.setval('public.activity_id_seq', 9, true);
          public          yan    false    203            �           0    0    employees_id_seq    SEQUENCE SET     ?   SELECT pg_catalog.setval('public.employees_id_seq', 22, true);
          public          yan    false    206            �           0    0    timesheet_id_seq    SEQUENCE SET     ?   SELECT pg_catalog.setval('public.timesheet_id_seq', 35, true);
          public          yan    false    208            /           2606    16450    activity activity_pk 
   CONSTRAINT     R   ALTER TABLE ONLY public.activity
    ADD CONSTRAINT activity_pk PRIMARY KEY (id);
 >   ALTER TABLE ONLY public.activity DROP CONSTRAINT activity_pk;
       public            yan    false    204            1           2606    16458 (   activity_type_info activity_type_info_pk 
   CONSTRAINT     h   ALTER TABLE ONLY public.activity_type_info
    ADD CONSTRAINT activity_type_info_pk PRIMARY KEY (type);
 R   ALTER TABLE ONLY public.activity_type_info DROP CONSTRAINT activity_type_info_pk;
       public            yan    false    205            4           2606    16528    employee employees_pk 
   CONSTRAINT     S   ALTER TABLE ONLY public.employee
    ADD CONSTRAINT employees_pk PRIMARY KEY (id);
 ?   ALTER TABLE ONLY public.employee DROP CONSTRAINT employees_pk;
       public            yan    false    207            ,           2606    16432    job_info job_info_pk 
   CONSTRAINT     Z   ALTER TABLE ONLY public.job_info
    ADD CONSTRAINT job_info_pk PRIMARY KEY ("position");
 >   ALTER TABLE ONLY public.job_info DROP CONSTRAINT job_info_pk;
       public            yan    false    202            7           2606    16547    timesheet timesheet_pk 
   CONSTRAINT     T   ALTER TABLE ONLY public.timesheet
    ADD CONSTRAINT timesheet_pk PRIMARY KEY (id);
 @   ALTER TABLE ONLY public.timesheet DROP CONSTRAINT timesheet_pk;
       public            yan    false    209            -           1259    16448    activity_id_uindex    INDEX     L   CREATE UNIQUE INDEX activity_id_uindex ON public.activity USING btree (id);
 &   DROP INDEX public.activity_id_uindex;
       public            yan    false    204            2           1259    16526    employees_id_uindex    INDEX     M   CREATE UNIQUE INDEX employees_id_uindex ON public.employee USING btree (id);
 '   DROP INDEX public.employees_id_uindex;
       public            yan    false    207            5           1259    16545    timesheet_id_uindex    INDEX     N   CREATE UNIQUE INDEX timesheet_id_uindex ON public.timesheet USING btree (id);
 '   DROP INDEX public.timesheet_id_uindex;
       public            yan    false    209            8           2606    16492 "   activity activity_activity_info_fk    FK CONSTRAINT     �   ALTER TABLE ONLY public.activity
    ADD CONSTRAINT activity_activity_info_fk FOREIGN KEY (type_info) REFERENCES public.activity_type_info(type);
 L   ALTER TABLE ONLY public.activity DROP CONSTRAINT activity_activity_info_fk;
       public          yan    false    205    2865    204            9           2606    16529    employee employee_job_info_fk    FK CONSTRAINT     �   ALTER TABLE ONLY public.employee
    ADD CONSTRAINT employee_job_info_fk FOREIGN KEY (position_info) REFERENCES public.job_info("position");
 G   ALTER TABLE ONLY public.employee DROP CONSTRAINT employee_job_info_fk;
       public          yan    false    207    2860    202            :           2606    16548    timesheet timesheet_employee_fk    FK CONSTRAINT     �   ALTER TABLE ONLY public.timesheet
    ADD CONSTRAINT timesheet_employee_fk FOREIGN KEY (employee_id) REFERENCES public.employee(id);
 I   ALTER TABLE ONLY public.timesheet DROP CONSTRAINT timesheet_employee_fk;
       public          yan    false    207    209    2868            �   d   x�]�;� ��Sx���]l����K�p~����ά�Q�λ���B��o�..KE���V};ˡJW�;��(#�����Si��(g	�(;gt?���$�W(5      �   B   x�KL*N�+IM�,��4�*KLN,����4г�*�L�V�IM,Kr͹��R�J2sS9�L�b���� a�      �   �   x�uϱ
1��9y�>���z����x��R$j�lK��^�A����$98�'_rZ���w���,2�,������Z�0���0���㋘1�C)��v�l-�f{e]���y���rf�Iٺ��I����̪�M��x� �Rvi_      �   Q   x��*���/RpI-K��/H-�450�
�O+)O,JUp�K��KM-R0�4�.a�id�U�,�
6�Fc��F�F c���� Ta3q      �   {   x�Uб1C��&0)ےw��s�\a6�Q�M]l��8�0X�0Hp�6�7��e@p:���O�Y7,H	�AA�`C�ӡe@(NG�xa���m�� 4(�6"n81������=�     