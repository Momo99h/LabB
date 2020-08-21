PGDMP     ,                    x           LabB    12.2    12.2     ;           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            <           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            =           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            >           1262    24682    LabB    DATABASE     �   CREATE DATABASE "LabB" WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'Italian_Italy.1252' LC_CTYPE = 'Italian_Italy.1252';
    DROP DATABASE "LabB";
                postgres    false            �            1259    24683    admins    TABLE     q   CREATE TABLE public.admins (
    username character varying NOT NULL,
    password character varying NOT NULL
);
    DROP TABLE public.admins;
       public         heap    postgres    false            �            1259    24689    games    TABLE     4  CREATE TABLE public.games (
    roomid numeric NOT NULL,
    gameid numeric NOT NULL,
    players numeric NOT NULL,
    playersnicknames character varying NOT NULL,
    playersnicknamesend character varying NOT NULL,
    gamefinalscore numeric NOT NULL,
    date timestamp without time zone DEFAULT now()
);
    DROP TABLE public.games;
       public         heap    postgres    false            �            1259    24695    rooms    TABLE     �   CREATE TABLE public.rooms (
    roomid numeric NOT NULL,
    name character varying NOT NULL,
    players numeric NOT NULL,
    playersnicknames character varying NOT NULL,
    date timestamp without time zone DEFAULT now()
);
    DROP TABLE public.rooms;
       public         heap    postgres    false            �            1259    24701    settings    TABLE     L   CREATE TABLE public.settings (
    dictionarypath character varying(500)
);
    DROP TABLE public.settings;
       public         heap    postgres    false            �            1259    24704    users    TABLE     �   CREATE TABLE public.users (
    nome character varying NOT NULL,
    cognome character varying NOT NULL,
    nickname character varying NOT NULL,
    email character varying NOT NULL,
    password character varying NOT NULL
);
    DROP TABLE public.users;
       public         heap    postgres    false            �            1259    24710 
   usersscore    TABLE     n   CREATE TABLE public.usersscore (
    nickname character varying NOT NULL,
    totalpoints numeric NOT NULL
);
    DROP TABLE public.usersscore;
       public         heap    postgres    false            �            1259    24716 
   usersstate    TABLE     �   CREATE TABLE public.usersstate (
    nickname character varying NOT NULL,
    onlinestatus numeric,
    idroom numeric NOT NULL
);
    DROP TABLE public.usersstate;
       public         heap    postgres    false            �            1259    24722 
   userswords    TABLE     P  CREATE TABLE public.userswords (
    roomid numeric NOT NULL,
    gameid numeric NOT NULL,
    nickname character varying NOT NULL,
    word character varying NOT NULL,
    indictionary character varying NOT NULL,
    score numeric NOT NULL,
    date timestamp without time zone DEFAULT now(),
    explanation character varying(500)
);
    DROP TABLE public.userswords;
       public         heap    postgres    false            1          0    24683    admins 
   TABLE DATA           4   COPY public.admins (username, password) FROM stdin;
    public          postgres    false    202   x        2          0    24689    games 
   TABLE DATA           u   COPY public.games (roomid, gameid, players, playersnicknames, playersnicknamesend, gamefinalscore, date) FROM stdin;
    public          postgres    false    203   �        3          0    24695    rooms 
   TABLE DATA           N   COPY public.rooms (roomid, name, players, playersnicknames, date) FROM stdin;
    public          postgres    false    204   �        4          0    24701    settings 
   TABLE DATA           2   COPY public.settings (dictionarypath) FROM stdin;
    public          postgres    false    205   �!       5          0    24704    users 
   TABLE DATA           I   COPY public.users (nome, cognome, nickname, email, password) FROM stdin;
    public          postgres    false    206   �!       6          0    24710 
   usersscore 
   TABLE DATA           ;   COPY public.usersscore (nickname, totalpoints) FROM stdin;
    public          postgres    false    207   ^"       7          0    24716 
   usersstate 
   TABLE DATA           D   COPY public.usersstate (nickname, onlinestatus, idroom) FROM stdin;
    public          postgres    false    208   �"       8          0    24722 
   userswords 
   TABLE DATA           l   COPY public.userswords (roomid, gameid, nickname, word, indictionary, score, date, explanation) FROM stdin;
    public          postgres    false    209   �"       �
           2606    24729    admins admins_pkey 
   CONSTRAINT     V   ALTER TABLE ONLY public.admins
    ADD CONSTRAINT admins_pkey PRIMARY KEY (username);
 <   ALTER TABLE ONLY public.admins DROP CONSTRAINT admins_pkey;
       public            postgres    false    202            �
           2606    24784    games gameid_pk 
   CONSTRAINT     Y   ALTER TABLE ONLY public.games
    ADD CONSTRAINT gameid_pk PRIMARY KEY (gameid, roomid);
 9   ALTER TABLE ONLY public.games DROP CONSTRAINT gameid_pk;
       public            postgres    false    203    203            �
           2606    24735    rooms rooms_pkey 
   CONSTRAINT     R   ALTER TABLE ONLY public.rooms
    ADD CONSTRAINT rooms_pkey PRIMARY KEY (roomid);
 :   ALTER TABLE ONLY public.rooms DROP CONSTRAINT rooms_pkey;
       public            postgres    false    204            �
           2606    24739    users users_nickname_key 
   CONSTRAINT     W   ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_nickname_key UNIQUE (nickname);
 B   ALTER TABLE ONLY public.users DROP CONSTRAINT users_nickname_key;
       public            postgres    false    206            �
           2606    24741    users users_pkey 
   CONSTRAINT     e   ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (nickname, email, password);
 :   ALTER TABLE ONLY public.users DROP CONSTRAINT users_pkey;
       public            postgres    false    206    206    206            �
           2606    24743    usersscore usersscore_pkey 
   CONSTRAINT     ^   ALTER TABLE ONLY public.usersscore
    ADD CONSTRAINT usersscore_pkey PRIMARY KEY (nickname);
 D   ALTER TABLE ONLY public.usersscore DROP CONSTRAINT usersscore_pkey;
       public            postgres    false    207            �
           2606    24745    usersstate usersstate_pkey 
   CONSTRAINT     ^   ALTER TABLE ONLY public.usersstate
    ADD CONSTRAINT usersstate_pkey PRIMARY KEY (nickname);
 D   ALTER TABLE ONLY public.usersstate DROP CONSTRAINT usersstate_pkey;
       public            postgres    false    208            �
           2606    24753    games games_roomid_fkey    FK CONSTRAINT     y   ALTER TABLE ONLY public.games
    ADD CONSTRAINT games_roomid_fkey FOREIGN KEY (roomid) REFERENCES public.rooms(roomid);
 A   ALTER TABLE ONLY public.games DROP CONSTRAINT games_roomid_fkey;
       public          postgres    false    2728    204    203            �
           2606    24763 #   usersscore usersscore_nickname_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.usersscore
    ADD CONSTRAINT usersscore_nickname_fkey FOREIGN KEY (nickname) REFERENCES public.users(nickname);
 M   ALTER TABLE ONLY public.usersscore DROP CONSTRAINT usersscore_nickname_fkey;
       public          postgres    false    207    2730    206            1   2   x�KL����42426J3�4O4�DscK�D�T�Dôdc�=... 4
�      2      x������ � �      3   �   x���AjA��+��F�h�0y������~�!��$��]��f �\.g����=
���o��v;�7�Ԛ��5�+\���:��wk�%��VB����o�X�?���~��Ɵlj��*��0��{r}��M��g�a�������f�I��T�����t[N�)�ܒ�T����If      4   3   x�s�����Ԣb�7?7D��g����)��F%��%z�%\1z\\\ ���      5   k   x���;�  �ZCDX���f�c6*����lf�7RΩ?Ņ����R���x��:��J�����@{t�T]o��,�L~�����þr��f⌱}�1      6      x��,(�7�4�
 18M�b���� J�w      7      x��,(�7�4�4�
 1��=... ^&�      8      x������ � �     