create database if not exists battle;
use battle;

-- TAULES DE CONFIGURACIÓ
create table weapons (
    weapon_id int unsigned primary key,
    weapon_name varchar(30) not null,
    weapon_image_path varchar(30)
);

create table warriors (
    warrior_id int unsigned primary key,
    warrior_name varchar(30) not null,
    warrior_image_path varchar(30)
);

create table players (
    player_id int UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    player_name varchar(30) not null
);

create table weapons_available (
    warrior_id int unsigned,
    weapon_id int unsigned,
    constraint warrior_id foreign key (warrior_id) references warriors(warrior_id),
    constraint weapon_id foreign key (weapon_id) references weapons(weapon_id)
);


-- TAULES DE BATALLA
create table battle (
    battle_id int UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    player_id int unsigned not null,
    warrior_id int unsigned not null,
    warrior_weapon_id int unsigned not null,
    opponent_id int unsigned not null,
    opponent_weapon_id int unsigned not null,
    injuries_caused int not null,
    injuries_suffered int not null,
    battle_points int not null,
    foreign key (player_id) references players(player_id),
    foreign key (warrior_id) references warriors(warrior_id),
    foreign key (warrior_weapon_id) references weapons(weapon_id),
    foreign key (opponent_id) references warriors(warrior_id),
    foreign key (opponent_weapon_id) references weapons(weapon_id)
);

create table BATTLE_ROUNDS(
	battle_rounds_id int UNSIGNED not null,
	battle_id int unsigned not null,
	player_id int unsigned not null,
	warrior_id int unsigned not null,
	warrior_weapon_id int unsigned not null,
	opponent_id int unsigned not null,
	opponent_weapon_id int unsigned not null,
	injuries_caused int not null,
	injuries_suffered int not null,

	foreign key (battle_id) references battle(battle_id),
	foreign key (player_id) references players(player_id),
	foreign key (warrior_id) references warriors(warrior_id),
	foreign key (warrior_weapon_id) references weapons(weapon_id),
	foreign key (opponent_id) references warriors(warrior_id),
	foreign key (opponent_weapon_id) references weapons(weapon_id)
);

-- WARRIORS
insert into warriors values(1, 'Elf', 'elf.png');
insert into warriors values(2, 'Human', 'huma.png');
insert into warriors values(3, 'Nan', 'nan.png');

-- WEAPONS
insert into weapons values(1, 'Daga', 'daga.png');
insert into weapons values(2, 'Espasa', 'espasa.png');
insert into weapons values(3, 'Destral', 'destral.png');
insert into weapons values(4, 'Espasas Dobles', 'espasas_dobles.png');
insert into weapons values(5, 'Simitarra', 'simitarra.png');
insert into weapons values(6, 'Arc', 'arc.png');
insert into weapons values(7, 'Katana', 'katana.png');
insert into weapons values(8, 'Punyal', 'punyal.png');
insert into weapons values(9, 'Destral de dues mans', 'destral_de_dues_mans.png');

-- WEAPONS-AVAILABLE
insert into weapons_available values(2,1);
insert into weapons_available values(1,1);
insert into weapons_available values(1,2);
insert into weapons_available values(2,2);
insert into weapons_available values(3,2);
insert into weapons_available values(2,3);
insert into weapons_available values(3,3);
insert into weapons_available values(1,4);
insert into weapons_available values(2,4);
insert into weapons_available values(1,5);
insert into weapons_available values(2,5);
insert into weapons_available values(1,6);
insert into weapons_available values(2,7);
insert into weapons_available values(1,8);
insert into weapons_available values(2,8);
insert into weapons_available values(3,8);
insert into weapons_available values(3,9);


-- ESTO SÓN DATOS INVENTADOS DE PRUEBA

insert into players values(1, 'Izan');
insert into players values(2, 'Joel');
insert into players values(3, 'Bryan');

insert into battle values(1, 1, 1, 1, 2, 1, 40, 30, 100);
insert into battle values(2, 2, 1, 2, 1, 1, 30, 20, 103);
insert into battle values(3, 3, 1, 4, 2, 7, 30, 40, 230);
insert into battle values(4, 1, 2, 2, 3, 9, 30, 10, 86);
insert into battle values(5, 2, 2, 7, 1, 1, 40, 15, 71);
insert into battle values(6, 3, 2, 8, 3, 8, 10, 20, 45);
insert into battle values(7, 1, 3, 9, 1, 1, 12, 30, 130);
insert into battle values(8, 2, 3, 8, 2, 7, 39, 35, 240);
insert into battle values(9, 3, 3, 2, 1, 1, 30, 10, 12);

create view rankings as 
select p.player_name, sum(b.battle_points) as global_points, 
	(select count(*) from battle where injuries_caused > injuries_suffered and
	player_id = p.player_id) as defeated_enemies, sum(b.injuries_caused) as injuries_caused,
    sum(b.injuries_suffered) as injuries_suffered from players p
join battle b on p.player_id = b.player_id
group by p.player_id order by global_points desc limit 10;