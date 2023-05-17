package rankings;

public class Ranking {
	private String player_name;
	private int global_points, defeated_enemies, injuries_caused, injuries_suffered;
	
	public Ranking(String p, int g, int d, int ic, int is) {
		this.player_name = p;
		this.global_points = g;
		this.defeated_enemies = d;
		this.injuries_caused = ic;
		this.injuries_suffered = is;
	}

	public String getPlayer_name() {
		return player_name;
	}

	public void setPlayer_name(String player_name) {
		this.player_name = player_name;
	}

	public int getGlobal_points() {
		return global_points;
	}

	public void setGlobal_points(int global_points) {
		this.global_points = global_points;
	}

	public int getDefeated_enemies() {
		return defeated_enemies;
	}

	public void setDefeated_enemies(int defeated_enemies) {
		this.defeated_enemies = defeated_enemies;
	}

	public int getInjuries_caused() {
		return injuries_caused;
	}

	public void setInjuries_caused(int injuries_caused) {
		this.injuries_caused = injuries_caused;
	}

	public int getInjuries_suffered() {
		return injuries_suffered;
	}

	public void setInjuries_suffered(int injuries_suffered) {
		this.injuries_suffered = injuries_suffered;
	}
}
