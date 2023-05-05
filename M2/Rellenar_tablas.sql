use battle;

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



