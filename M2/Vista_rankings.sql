create view rankings as 
select p.player_name, sum(b.battle_points) as global_points, 
	(select count(*) from battle where injuries_caused > injuries_suffered and
	player_id = p.player_id) as defeated_enemies, sum(b.injuries_caused) as injuries_caused,
    sum(b.injuries_suffered) as injuries_suffered from players p
join battle b on p.player_id = b.player_id
group by p.player_id order by global_points desc limit 10;
