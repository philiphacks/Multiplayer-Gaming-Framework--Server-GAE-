package be.tbs.mgf.games.session;

import be.tbs.mgf.database.data.ScoreData;

/**
 * Keeps track of the score for one player.
 * @author Gino
 */
public class Score {
	
	private int fPlayer;
	
	private int fTotals;
	
	/**
	 * Constructs a new scoring-object for this player.
	 * Score is set at zero.
	 * @param player - The id of the player.
	 */
	public Score(int player) {
		fPlayer = player;
		fTotals = 0;
	}
	
	/**
	 * Constructs a new scoring-object for this player.
	 * Score is set at the specified value.
	 * @param player - The id of the player.
	 * @param score - The score to start the player with.
	 */
	public Score(int player, int score) {
		fPlayer = player;
		fTotals = score;
	}
	
	/**
	 * Constructs a new score object using an {@link ScoreData} object.
	 * @param data - Data containing everything needed to reconstruct a score object.
	 */
	public Score(ScoreData data) {
		fPlayer = data.getPlayer();
		fTotals = data.getScore();
	}
	
	/**
	 * Increments the score for this player by one (1).
	 */
	public void increment() {
		increment(1);
	}
	
	/**
	 * Increments the score for this player by the specified amount.
	 * @param by - The amount to increment the score with.
	 */
	public void increment(int by) {
		fTotals+=by;
	}
	
	/**
	 * Decrements the score for this player by one (1).
	 */
	public void decrement() {
		decrement(1);
	}
	
	/**
	 * Decrements the score for this player by the specified amount.
	 * @param by - The amount to decrement the score with.
	 */
	public void decrement(int by) {
		fTotals-=by;
	}
	
	/**
	 * Gets the total score.
	 * @return int - The total score.
	 */
	public int getTotal() {
		return fTotals;
	}
	
	/**
	 * Gets the player
	 * @return int - The id of the player
	 */
	public int getPlayer() {
		return fPlayer;
	}
}
