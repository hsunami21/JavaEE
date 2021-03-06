package team23_data;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name="GAME", schema="PROJECT")
public class Game implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private int game_ID;
	private String p1_name;
	private String p2_name;
	private int p1_score;
	private int p2_score;

	public Game() {
		// TODO Auto-generated constructor stub
		
	}
	
	public Game(String p1_name, String p2_name, int p1_score, int p2_score) {
		this.p1_name = p1_name;
		this.p2_name = p2_name;
		this.p1_score = p1_score;
		this.p2_score = p2_score;

	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column (name="GAME_ID", nullable=false)
	public int getGame_ID() {
		return game_ID;
	}

	public void setGame_ID(int game_ID) {
		this.game_ID = game_ID;
	}

	@Column (name="PLAYER1_NAME", nullable=false)
	public String getP1_name() {
		return p1_name;
	}

	public void setP1_name(String p1_name) {
		this.p1_name = p1_name;
	}

	@Column (name="PLAYER2_NAME", nullable=false)
	public String getP2_name() {
		return p2_name;
	}

	public void setP2_name(String p2_name) {
		this.p2_name = p2_name;
	}

	@Column (name="PLAYER1_SCORE", nullable=false)
	public int getP1_score() {
		return p1_score;
	}

	public void setP1_score(int p1_score) {
		this.p1_score = p1_score;
	}

	@Column (name="PLAYER2_SCORE", nullable=false)
	public int getP2_score() {
		return p2_score;
	}

	public void setP2_score(int p2_score) {
		this.p2_score = p2_score;
	}

	
}
