package sportradar.scoreboard;

/**
 * The match score information object
 */
public class MatchClass {
    private final long matchId;
    private int homeTeamScore;
    private int awayTeamScore;
    private String homeTeam;
    private String awayTeam;

    /**
     * Create a new match score instance and assign zero to the scores
     * @param matchId The match ID
     * @param homeTeam The name of the home team
     * @param awayTeam The name of the away team
     */
    public MatchClass(long matchId, String homeTeam, String awayTeam)
    {
        this.homeTeamScore = 0;
        this.awayTeamScore = 0;
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.matchId = matchId;
    }

    /**
     * Set the home team score
     * @param homeTeamScore The score to set for the home team
     */
    public void setHomeTeamScore(int homeTeamScore) {
        this.homeTeamScore = homeTeamScore;
    }

    /**
     * Get the match ID
     * @return match ID
     */
    public long getMatchId() {
        return matchId;
    }

    /**
     * Set the away team score
     * @param awayTeamScore The score to set for the away team
     */
    public void setAwayTeamScore(int awayTeamScore) {
        this.awayTeamScore = awayTeamScore;
    }

    /**
     * Get the score of the away team
     * @return The score of the away team
     */
    public int getAwayTeamScore() {
        return awayTeamScore;
    }

    /**
     * Get the score of the home team
     * @return The score of the home team
     */
    public int getHomeTeamScore() {
        return homeTeamScore;
    }

    /**
     * Set the name of the away team
     * @param awayTeam The name of the away team
     */
    public void setAwayTeam(String awayTeam) {
        this.awayTeam = awayTeam;
    }

    /**
     * Set the name of the home team
     * @param homeTeam The name of the home team
     */
    public void setHomeTeam(String homeTeam) {
        this.homeTeam = homeTeam;
    }

    /**
     * Get the name of the away team
     * @return The name of the away team
     */
    public String getAwayTeam() {
        return awayTeam;
    }

    /**
     * Get the name of the home team
     * @return The name of the home team
     */
    public String getHomeTeam() {
        return homeTeam;
    }
}
