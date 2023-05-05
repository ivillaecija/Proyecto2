-- ENEMICS DERROTATS
create view enemics_derrotats as 
	select id_jugador, count(*) as enemics_derrotats from Batalla 
    where dany_realitzat > dany_rebut group by id_jugador;
    
-- RANKINGS
create view rankings as select b.id_jugador, sum(b.puntuacio) as puntuacio_global, e.enemics_derrotats,
sum(b.dany_realitzat) as dany_realitzat, sum(b.dany_rebut) as dany_rebut
from Batalla b join enemics_derrotats e on e.id_jugador = b.id_jugador group by id_jugador;

    
