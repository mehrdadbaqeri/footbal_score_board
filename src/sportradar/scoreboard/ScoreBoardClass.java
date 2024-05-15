package sportradar.scoreboard;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

public class ScoreBoardClass {
    private final ArrayList<MatchClass> matchList;
    private long matchIdIncIdx;

    /**
     * The score board constructor
     */
    public ScoreBoardClass(){
        this.matchList = new ArrayList<>();
        this.matchIdIncIdx = 0;
    }

    /**
     * Start a new match
     * @param homeTeamName The name of the home team
     * @param awayTeamName The name of the away team
     * @return The match ID
     * @throws InvalidParameterException In case any of home away team exists in existing matches
     */
    public long startNewMatch(String homeTeamName, String awayTeamName) throws InvalidParameterException{
        MatchClass matchScore = new MatchClass(this.matchIdIncIdx, homeTeamName, awayTeamName);
        if(null != this.findMatchByTeamName(homeTeamName) || null != this.findMatchByTeamName(awayTeamName)) {
            throw new InvalidParameterException("The given teams already exist on the board");
        }
        else {
            this.matchList.add(matchScore);
        }
        return matchIdIncIdx++;
    }

    /**
     * Update the score of both teams
     * @param matchId The match ID
     * @param homeTeamScore The score for the home team
     * @param awayTeamScore The score for the away team
     * @throws InvalidParameterException In case the home team does not exist
     */
    public void updateMatchScore(long matchId, int homeTeamScore, int awayTeamScore) throws InvalidParameterException{
        MatchClass match = this.findMatchByMatchId(matchId);
        if( match != null){
            this.matchList.get(this.matchList.indexOf(match)).setHomeTeamScore(homeTeamScore);
            this.matchList.get(this.matchList.indexOf(match)).setAwayTeamScore(awayTeamScore);
        }
        else {
            throw new InvalidParameterException("Could not find the match in score board");
        }
    }

    /**
     * Update the score of the home team
     * @param matchId The match ID
     * @param homeTeamScore The score for the home team
     * @throws InvalidParameterException In case the home team does not exist
     */
    public void updateMatchHomeScore(long matchId, int homeTeamScore) throws InvalidParameterException{
        MatchClass match = this.findMatchByMatchId(matchId);
        if( match != null){
            this.matchList.get(this.matchList.indexOf(match)).setHomeTeamScore(homeTeamScore);
        }
        else {
            throw new InvalidParameterException("Could not find the match in score board");
        }
    }

    /**
     * Update the score of the home team
     * @param matchId The match ID
     * @param awayTeamScore The score for the home team
     * @throws InvalidParameterException In case the home team does not exist
     */
    public void updateMatchAwayScore(long matchId, int awayTeamScore) throws InvalidParameterException{
        MatchClass match = this.findMatchByMatchId(matchId);
        if( match != null){
            this.matchList.get(this.matchList.indexOf(match)).setAwayTeamScore(awayTeamScore);
        }
        else {
            throw new InvalidParameterException("Could not find the match in score board");
        }
    }

    /**
     * Get the score of the home team in the given match
     * @param matchId The match ID
     * @return The score of the home team in the given match
     * @throws InvalidParameterException In case the home team does not exist
     */
    public int getHomeScore(long matchId) throws InvalidParameterException{
        MatchClass match = this.findMatchByMatchId(matchId);
        int homeScore = 0;
        if( match != null){
            homeScore = this.matchList.get(this.matchList.indexOf(match)).getHomeTeamScore();
        }
        else {
            throw new InvalidParameterException("Could not find the match in score board");
        }
        return homeScore;
    }

    /**
     * Get the score of the away team in the given match
     * @param matchId The match ID
     * @return The score of the away team in the given match
     * @throws InvalidParameterException In case the home team does not exist
     */
    public int getAwayScore(long matchId) throws InvalidParameterException{
        MatchClass match = this.findMatchByMatchId(matchId);
        int homeScore = 0;
        if( match != null){
            homeScore = this.matchList.get(this.matchList.indexOf(match)).getAwayTeamScore();
        }
        else {
            throw new InvalidParameterException("Could not find the match in score board");
        }
        return homeScore;
    }

    /**
     * Find a match by the given team name
     * @param teamName The team name to search the match base on it
     * @return The match score object containing the given team. It returns null if such match does not exist on
     * the board.
     */
    private MatchClass findMatchByTeamName(String teamName){
        return matchList.stream()
                .filter(matchScoreItem -> teamName.equals(matchScoreItem.getHomeTeam()) ||
                        teamName.equals(matchScoreItem.getAwayTeam()))
                .findAny()
                .orElse(null);
    }

    /**
     * Find a match by the given match ID
     * @param matchId The match id of the match to find
     * @return The match score object containing the given match ID. It returns null if such match does not exist on
     * the board.
     */
    private MatchClass findMatchByMatchId(long matchId){
        return matchList.stream()
                .filter(matchScoreItem -> matchId == matchScoreItem.getMatchId())
                .findAny()
                .orElse(null);
    }

    /**
     * Finish the match with given match ID
     * @param matchID The match ID
     * @throws InvalidParameterException In case the match does not exist
     */
    public void finishMatch(long matchID) throws InvalidParameterException{
        MatchClass match = this.findMatchByMatchId(matchID);
        if(null == match) {
            throw new InvalidParameterException("The given match does not exist on the board");
        }
        else {
            this.matchList.remove(this.matchList.get(this.matchList.indexOf(match)));
        }
    }

    /**
     * Get the ordered match list based on first the sum of the scores, then the match start order.
     * @return The ordered match list @ref {@link List <MatchClass>}
     */
    public List<MatchClass> getOrderedMatchList(){
        Comparator<MatchClass> matchClassComparator = Comparator.comparing(matchClass -> matchClass.getAwayTeamScore() +
                matchClass.getHomeTeamScore());
        matchClassComparator = matchClassComparator.thenComparing(Comparator.comparing(this.matchList::indexOf));
        Stream<MatchClass> matchListStream = this.matchList.stream().sorted(matchClassComparator.reversed());

        return matchListStream.toList();
    }

    /**
     * Get the list of existing matches on the board
     * @return The list of existing matches @ref {@link ArrayList< MatchClass >}
     */
    public ArrayList<MatchClass> getMatchList(){
        return this.matchList;
    }

    /**
     * Get the index of added matches. It indicates the number of running plus finished matches.
     * @return The index of added matches
     */
    public long getMatchIdIncIdx() {
        return matchIdIncIdx;
    }
}
