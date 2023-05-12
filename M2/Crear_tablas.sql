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
	battle_rounds_id int UNSIGNED PRIMARY KEY AUTO_INCREMENT,
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
)
