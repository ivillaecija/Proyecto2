use batalla;

delete from Arma;

-- DATOS NO INVENTADOS
-- ARMAS 
insert into Arma values('Daga', '+3 en velocitat', 'Huma i elf');
insert into Arma values('Espasa', '+1 velocitat +1 força', 'Huma, elf i nan');
insert into Arma values('Destral', '+3 en força', 'Huma i nan');
insert into Arma values('Espases dobles', '+2 velocitat + 2 força', 'Huma i elf');
insert into Arma values('Simitarra', '+2 velocitat +1 força', 'Huma i elf');
insert into Arma values('Arc', '+5 velocitat +1 força', 'Elf');
insert into Arma values('Katana', '+3 velocitat +2 força', 'Huma');
insert into Arma values('Punyal', '+4 velocitat', 'Huma, elf i nan');
insert into Arma values('Destral de dues mans', '+5 força', 'Nan');

-- DATOS INVENTADOS
-- PERSONAJES
insert into Personatge values(1, 'Huma', 'Humano1', 50, 5, 3, 6, 5);
insert into Personatge values(2, 'Elf', 'Elfo1', 40, 4, 2, 7, 7);
insert into Personatge values(3, 'Nan', 'Nan1', 60, 6, 4, 5, 3);

-- JUGADOR
insert into Jugador values (1, 'Jugador1');
insert into Jugador values (2, 'Jugador2');

-- Batalla
insert into Batalla values(1,1,1,2,'Katana',40, 30);
insert into Batalla values(2,2,3,1,'Destral de dues mans',50,40);

-- Ronda
insert into Ronda values(1,1,1,10,10);
insert into Ronda values(1,1,2,10,10);
insert into Ronda values(1,1,3,20,10);
insert into Ronda values(2,2,1,30,20);
insert into Ronda values(2,2,2,20,20);