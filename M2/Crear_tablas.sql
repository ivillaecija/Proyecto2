create database if not exists batalla;
use batalla;
create table Personatge (
	id_personatge int unsigned auto_increment primary key,
    raca varchar(30) not null,
    nom varchar(30) not null,
    vida int not null,
    forca int not null,
    agilitat int not null,
    velocitat int not null,
    check (raca in ('Huma', 'Elf', 'Nan'))
);

create table Arma (
	id_arma int unsigned auto_increment primary key,
    tipus varchar(30) not null,
    descripcio varchar(30),
    utilitat varchar(30),
    check(tipus in ('Daga', 'Espasa', 'Destral', 'Espases dobles', 'Simitarra', 
    'Arc', 'Katana', 'Punyal', 'Destral de dues mans')),
    check(utilitat in ('Huma i elf', 'Huma, elf i nan', 'Huma i nan', 'Elf', 'Huma', 'Nan'))
);

create table Jugador(
	id_jugador int unsigned primary key,
    nom varchar(30) not null
);

create table Batalla(
	id_batalla int unsigned primary key,
    id_jugador int unsigned,
    guerrer int unsigned,
    enemic int unsigned,
	arma int unsigned,
    dany_realitzat int not null,
    dany_rebut int not null,
    constraint id_jugador foreign key (id_jugador) references Jugador(id_jugador),
    constraint guerrer foreign key (guerrer) references Personatge(id_personatge),
    constraint enemic foreign key (enemic) references Personatge(id_personatge),
    constraint arma foreign key (arma) references Arma(id_arma)  
);

create table Ronda(
	id_batalla int unsigned,
    id_jugador_ronda int unsigned,
    num_ronda int unsigned not null,
    dany_realitzat int not null,
    dany_rebut int not null,
    constraint id_batalla foreign key (id_batalla) references Batalla(id_batalla),
    constraint id_jugador_ronda foreign key (id_jugador_ronda) references Jugador(id_jugador)
);