PGDMP     :                    x           LabB    12.2    12.2 !    F           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            G           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            H           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            I           1262    24682    LabB    DATABASE     �   CREATE DATABASE "LabB" WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'Italian_Italy.1252' LC_CTYPE = 'Italian_Italy.1252';
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
       public         heap    postgres    false            �            1259    24801    lettersoccurencies    TABLE     c   CREATE TABLE public.lettersoccurencies (
    letter character varying(2),
    occurrency bigint
);
 &   DROP TABLE public.lettersoccurencies;
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
       public         heap    postgres    false            �            1259    24792    wordsdefinitions    TABLE     �   CREATE TABLE public.wordsdefinitions (
    word character varying(500),
    definition character varying(500),
    roomid bigint
);
 $   DROP TABLE public.wordsdefinitions;
       public         heap    postgres    false            :          0    24683    admins 
   TABLE DATA           4   COPY public.admins (username, password) FROM stdin;
    public          postgres    false    202   u$       ;          0    24689    games 
   TABLE DATA           u   COPY public.games (roomid, gameid, players, playersnicknames, playersnicknamesend, gamefinalscore, date) FROM stdin;
    public          postgres    false    203   �$       C          0    24801    lettersoccurencies 
   TABLE DATA           @   COPY public.lettersoccurencies (letter, occurrency) FROM stdin;
    public          postgres    false    211   s%       <          0    24695    rooms 
   TABLE DATA           N   COPY public.rooms (roomid, name, players, playersnicknames, date) FROM stdin;
    public          postgres    false    204   �%       =          0    24701    settings 
   TABLE DATA           2   COPY public.settings (dictionarypath) FROM stdin;
    public          postgres    false    205   i'       >          0    24704    users 
   TABLE DATA           I   COPY public.users (nome, cognome, nickname, email, password) FROM stdin;
    public          postgres    false    206   �'       ?          0    24710 
   usersscore 
   TABLE DATA           ;   COPY public.usersscore (nickname, totalpoints) FROM stdin;
    public          postgres    false    207   '(       @          0    24716 
   usersstate 
   TABLE DATA           D   COPY public.usersstate (nickname, onlinestatus, idroom) FROM stdin;
    public          postgres    false    208   S(       A          0    24722 
   userswords 
   TABLE DATA           l   COPY public.userswords (roomid, gameid, nickname, word, indictionary, score, date, explanation) FROM stdin;
    public          postgres    false    209   �(       B          0    24792    wordsdefinitions 
   TABLE DATA           D   COPY public.wordsdefinitions (word, definition, roomid) FROM stdin;
    public          postgres    false    210   G-       �
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
       public          postgres    false    2737    204    203            �
           2606    24763 #   usersscore usersscore_nickname_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.usersscore
    ADD CONSTRAINT usersscore_nickname_fkey FOREIGN KEY (nickname) REFERENCES public.users(nickname);
 M   ALTER TABLE ONLY public.usersscore DROP CONSTRAINT usersscore_nickname_fkey;
       public          postgres    false    207    206    2739            :   2   x�KL����42426J3�4O4�DscK�D�T�Dôdc�=... 4
�      ;   �   x��ѱ!�O��ۀ1�D��r�W�$R�4�Ń�oH@�p��C�׵Oa��|Aj*M-
W�BH�>����n�	��c��?=AA�/�?l��Z�%Zq����5�=>���r���Kc�b���|���坿���ˢ��K%:Kopx]������2�"=�8��      C   J   x����P����|rN��g8<�S� C�j4h�a��/���m�fӁ�ǈ�EZ��I� ��o      <   �  x���Mn1���)z���H�E�]v�H� ɽz�^��(����B���#5|;��<���9��WX����1	=P<a��Ӭu��@������O�\wK���6ZX��h`Λ6>���G���)�V�j�ES61�ˮ>'k靇ญ}p�-������K��uQJǄ�Ϸ秷�ӦKL��i��������:�)̘ٔ�bԺ���MM�3Y���u�A9%�+�����Ԧ����*��q�~�KpI���w�&-�S��lK/<�6���^��5�+Ч��5p� 5���դJ�^	r;��ܮl�(Tm�S��v'Fa�q����i:5���(����3�6����²[�:i��G�����'R?_4b�(�D���q�
�cC�?e�s      =   3   x�s�����Ԣb�7?7D��g����)��F%��%z�%\1z\\\ ���      >   k   x���;�  �ZCDX���f�c6*����lf�7RΩ?Ņ����R���x��:��J�����@{t�T]o��,�L~�����þr��f⌱}�1      ?      x��,(�7�44�
 �8-�b���� O��      @      x��,(�7�4�4�
 1�,#S�=... d�      A   �  x����n�H���>�J�Vw��o1#kd	R���x"KG6��<���	�]�b[M��ͪ!r�����G3:b�����M�kD�(�/�|�4b"fM�bt]4�����6z*��g�Pm������M����1c���oo�|�^���y�@cɉFr}.y��e>$���ʞ��Mٖ�]m�M�T���m�Tu�8ɦy��`��(���1���5Խ}^�X ���a������:�=P.bʉV ��򃫷���mz�ʘb�8�ڜI��_~&g�V�82��i����(��pFF�������p˹UA�� ��O��(T�g#3��((A8���{y��RҘb���g;yu�\��J��Q&	���b�M���3��d���d�w��R&��w��2���b���=�J�Kׅ�H���2�:�S ���r�!��6L"d�ν�W1��ja���>3���0�0�x.ul�������+���Z�0�c�;*L�A���ء�-�a���Ц���:�N��诖� G�K���"z)��bW_DMѶ���*�׺��Dy��3>v���{(7?�b[��eS?�ce���Ϗ��13GǾ��w�^�N˱�$Ql�������`Z�:e l�v�	"�)���
�py7��N���h|#A�0=������(��a��� N(cB��]��M�}���z��J�5����<�48g`cJ���&Z�x�����Y ��8 Ҳ}�8!?՛���/��'�j���p�gJ�#��i�4�@���r�Ln|H3l�Tz�2_%����؆Qx��"�5���`8[����:q�u�qL��s�N��
bPn���6����$��LJ�<��'c��3��6��r��Bz�95����L�ٰ��L�H��l7���<�3��2fEXut����M��܆Bq��Q}�7��T�pe���+vd2�E,���.2�yc�5��5�����G�(��
�4u�I>l�{&�@Y�n�]-g��7���7�w�coJ�{�3�8���ZRu��\���pB����ۚjw��L��s����-�~C�c���F��X/�|<Z6��[��� ���Z��9�O��A�<�B��\y �n
���	�[|=��D�]E��<^VޑR��4�T4w+A�Tm�sk�fSbR�n��z�-�'�*��K)���{2��jD�      B   E   x�����(����TP��I,�,9�@G�(�8�$QG!1''5�(�*�/
����qZr��-F��� �Y$�     