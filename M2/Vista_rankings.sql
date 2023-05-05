create view rankings as 
select j.nom, sum(b.puntuacio) as puntuacio_global, 
	(select count(*) from Batalla where dany_realitzat > dany_rebut 
    and id_jugador = j.id_jugador) as enemics_derrotats,  sum(b.dany_realitzat) as dany_realitzat,
    sum(b.dany_rebut) as dany_rebut from Jugador j 
join Batalla b on b.id_jugador = j.id_jugador
group by j.id_jugador;

