PGDMP         2                x           LabB    12.0    12.2 "    }           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            ~           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false                       0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            �           1262    28175    LabB    DATABASE     d   CREATE DATABASE "LabB" WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'C' LC_CTYPE = 'C';
    DROP DATABASE "LabB";
                postgres    false            �            1259    29156    admins    TABLE     q   CREATE TABLE public.admins (
    username character varying NOT NULL,
    password character varying NOT NULL
);
    DROP TABLE public.admins;
       public         heap    postgres    false            �            1259    29027    games    TABLE     /  CREATE TABLE public.games (
    roomid numeric NOT NULL,
    gameid numeric NOT NULL,
    players numeric NOT NULL,
    playersnicknames character varying NOT NULL,
    playersnicknamesend character varying NOT NULL,
    gamefinalscore numeric NOT NULL,
    date timestamp without time zone NOT NULL
);
    DROP TABLE public.games;
       public         heap    postgres    false            �            1259    29012    rooms    TABLE     �   CREATE TABLE public.rooms (
    roomid numeric NOT NULL,
    name character varying NOT NULL,
    players numeric NOT NULL,
    playersnicknames character varying NOT NULL,
    date timestamp without time zone
);
    DROP TABLE public.rooms;
       public         heap    postgres    false            �            1259    29164    settings    TABLE     L   CREATE TABLE public.settings (
    dictionarypath character varying(500)
);
    DROP TABLE public.settings;
       public         heap    postgres    false            �            1259    28941    users    TABLE     �   CREATE TABLE public.users (
    nome character varying NOT NULL,
    cognome character varying NOT NULL,
    nickname character varying NOT NULL,
    email character varying NOT NULL,
    password character varying NOT NULL
);
    DROP TABLE public.users;
       public         heap    postgres    false            �            1259    29047 
   usersscore    TABLE     n   CREATE TABLE public.usersscore (
    nickname character varying NOT NULL,
    totalpoints numeric NOT NULL
);
    DROP TABLE public.usersscore;
       public         heap    postgres    false            �            1259    29175 
   usersstate    TABLE     �   CREATE TABLE public.usersstate (
    nickname character varying NOT NULL,
    onlinestatus numeric,
    idroom numeric NOT NULL
);
    DROP TABLE public.usersstate;
       public         heap    postgres    false            �            1259    29132 
   userswords    TABLE     #  CREATE TABLE public.userswords (
    roomid numeric NOT NULL,
    gameid numeric NOT NULL,
    nickname character varying NOT NULL,
    word character varying NOT NULL,
    indictionary character varying NOT NULL,
    score numeric NOT NULL,
    date timestamp without time zone NOT NULL
);
    DROP TABLE public.userswords;
       public         heap    postgres    false            x          0    29156    admins 
   TABLE DATA           4   COPY public.admins (username, password) FROM stdin;
    public          postgres    false    207   ='       u          0    29027    games 
   TABLE DATA           u   COPY public.games (roomid, gameid, players, playersnicknames, playersnicknamesend, gamefinalscore, date) FROM stdin;
    public          postgres    false    204   '       t          0    29012    rooms 
   TABLE DATA           N   COPY public.rooms (roomid, name, players, playersnicknames, date) FROM stdin;
    public          postgres    false    203   �'       y          0    29164    settings 
   TABLE DATA           2   COPY public.settings (dictionarypath) FROM stdin;
    public          postgres    false    208   �'       s          0    28941    users 
   TABLE DATA           I   COPY public.users (nome, cognome, nickname, email, password) FROM stdin;
    public          postgres    false    202   �'       v          0    29047 
   usersscore 
   TABLE DATA           ;   COPY public.usersscore (nickname, totalpoints) FROM stdin;
    public          postgres    false    205   �'       z          0    29175 
   usersstate 
   TABLE DATA           D   COPY public.usersstate (nickname, onlinestatus, idroom) FROM stdin;
    public          postgres    false    209   (       w          0    29132 
   userswords 
   TABLE DATA           _   COPY public.userswords (roomid, gameid, nickname, word, indictionary, score, date) FROM stdin;
    public          postgres    false    206   -(       �           2606    29163    admins admins_pkey 
   CONSTRAINT     V   ALTER TABLE ONLY public.admins
    ADD CONSTRAINT admins_pkey PRIMARY KEY (username);
 <   ALTER TABLE ONLY public.admins DROP CONSTRAINT admins_pkey;
       public            postgres    false    207            �           2606    29034    games games_pkey 
   CONSTRAINT     R   ALTER TABLE ONLY public.games
    ADD CONSTRAINT games_pkey PRIMARY KEY (roomid);
 :   ALTER TABLE ONLY public.games DROP CONSTRAINT games_pkey;
       public            postgres    false    204            �           2606    29036     games games_playersnicknames_key 
   CONSTRAINT     g   ALTER TABLE ONLY public.games
    ADD CONSTRAINT games_playersnicknames_key UNIQUE (playersnicknames);
 J   ALTER TABLE ONLY public.games DROP CONSTRAINT games_playersnicknames_key;
       public            postgres    false    204            �           2606    29019    rooms rooms_pkey 
   CONSTRAINT     R   ALTER TABLE ONLY public.rooms
    ADD CONSTRAINT rooms_pkey PRIMARY KEY (roomid);
 :   ALTER TABLE ONLY public.rooms DROP CONSTRAINT rooms_pkey;
       public            postgres    false    203            �           2606    29021     rooms rooms_playersnicknames_key 
   CONSTRAINT     g   ALTER TABLE ONLY public.rooms
    ADD CONSTRAINT rooms_playersnicknames_key UNIQUE (playersnicknames);
 J   ALTER TABLE ONLY public.rooms DROP CONSTRAINT rooms_playersnicknames_key;
       public            postgres    false    203            �           2606    28950    users users_nickname_key 
   CONSTRAINT     W   ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_nickname_key UNIQUE (nickname);
 B   ALTER TABLE ONLY public.users DROP CONSTRAINT users_nickname_key;
       public            postgres    false    202            �           2606    28948    users users_pkey 
   CONSTRAINT     e   ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (nickname, email, password);
 :   ALTER TABLE ONLY public.users DROP CONSTRAINT users_pkey;
       public            postgres    false    202    202    202            �           2606    29054    usersscore usersscore_pkey 
   CONSTRAINT     ^   ALTER TABLE ONLY public.usersscore
    ADD CONSTRAINT usersscore_pkey PRIMARY KEY (nickname);
 D   ALTER TABLE ONLY public.usersscore DROP CONSTRAINT usersscore_pkey;
       public            postgres    false    205            �           2606    29182    usersstate usersstate_pkey 
   CONSTRAINT     ^   ALTER TABLE ONLY public.usersstate
    ADD CONSTRAINT usersstate_pkey PRIMARY KEY (nickname);
 D   ALTER TABLE ONLY public.usersstate DROP CONSTRAINT usersstate_pkey;
       public            postgres    false    209            �           2606    29139    userswords userswords_pkey 
   CONSTRAINT     \   ALTER TABLE ONLY public.userswords
    ADD CONSTRAINT userswords_pkey PRIMARY KEY (gameid);
 D   ALTER TABLE ONLY public.userswords DROP CONSTRAINT userswords_pkey;
       public            postgres    false    206            �           2606    29042 !   games games_playersnicknames_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.games
    ADD CONSTRAINT games_playersnicknames_fkey FOREIGN KEY (playersnicknames) REFERENCES public.users(nickname) ON UPDATE CASCADE;
 K   ALTER TABLE ONLY public.games DROP CONSTRAINT games_playersnicknames_fkey;
       public          postgres    false    3038    204    202            �           2606    29037    games games_roomid_fkey    FK CONSTRAINT     y   ALTER TABLE ONLY public.games
    ADD CONSTRAINT games_roomid_fkey FOREIGN KEY (roomid) REFERENCES public.rooms(roomid);
 A   ALTER TABLE ONLY public.games DROP CONSTRAINT games_roomid_fkey;
       public          postgres    false    204    3042    203            �           2606    29022 !   rooms rooms_playersnicknames_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.rooms
    ADD CONSTRAINT rooms_playersnicknames_fkey FOREIGN KEY (playersnicknames) REFERENCES public.users(nickname) ON UPDATE CASCADE;
 K   ALTER TABLE ONLY public.rooms DROP CONSTRAINT rooms_playersnicknames_fkey;
       public          postgres    false    3038    202    203            �           2606    29055 #   usersscore usersscore_nickname_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.usersscore
    ADD CONSTRAINT usersscore_nickname_fkey FOREIGN KEY (nickname) REFERENCES public.users(nickname);
 M   ALTER TABLE ONLY public.usersscore DROP CONSTRAINT usersscore_nickname_fkey;
       public          postgres    false    3038    202    205            x   2   x�KL����42426J3�4O4�DscK�D�T�Dôdc�=... 4
�      u      x������ � �      t      x������ � �      y      x������ � �      s      x������ � �      v      x������ � �      z      x������ � �      w      x������ � �     