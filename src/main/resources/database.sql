drop table if exists public.registercard;
drop table if exists public.documentversion;
drop table if exists public.document;
drop table if exists public.author;



create table public.author
(
    authorid serial not null
        primary key,
    login    varchar(255)
        constraint login_unq
            unique,
    password varchar(255),
    username varchar(255)
);

alter table public.author
    owner to postgres;

create table public.document
(
    documentid   serial
        primary key,
    documentname varchar(255),
    author       integer
        constraint fkhbcfuwq1od7f6gvwrua1dma6p
            references public.author
);

alter table public.document
    owner to postgres;

create table public.documentversion
(
    documentversionid serial
        primary key,
    documentid        integer
        constraint fkg4u0fp70t3isk4442wwr4a2p2
            references public.document,
    versionauthor     integer
        constraint fkfy7jhjdyut34kmuk3tsxlp4k5
            references public.author,
    version           integer default 1 not null,
    content           bytea[]
);

alter table public.documentversion
    owner to postgres;

create table public.registercard
(
    regcardid            serial
        primary key,
    dateextern           timestamp(6) with time zone,
    dateintro            timestamp(6) with time zone,
    documentexternnumber varchar(255),
    documentintronumber  varchar(255),
    documentid           integer
        constraint fkq5122vs0ehnytrrqbx6fxvs1k
            references public.document
);

alter table public.registercard
    owner to postgres;

