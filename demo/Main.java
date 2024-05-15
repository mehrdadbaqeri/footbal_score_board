import java.util.List;
import sportradar.scoreboard.*;

public class Main {
    public static void main(String[] args) {
        ScoreBoardClass scoreBoard = new ScoreBoardClass();
        long match1 = scoreBoard.startNewMatch("Germany", "Spain");
        long match2 = scoreBoard.startNewMatch("Iran", "Brazil");
        long match3 = scoreBoard.startNewMatch("Japan", "England");
        long match4 = scoreBoard.startNewMatch("UAE", "USA");
        long match5 = scoreBoard.startNewMatch("Sweden", "Austria");
        scoreBoard.updateMatchHomeScore(match2, 4);
        scoreBoard.updateMatchAwayScore(match3, 6);
        scoreBoard.updateMatchHomeScore(match1, 4);
        scoreBoard.updateMatchAwayScore(match5, 8);
        scoreBoard.updateMatchHomeScore(match3, 4);

        List<MatchClass> orderedMatchScoreList = scoreBoard.getOrderedMatchList();
        for(MatchClass match: orderedMatchScoreList)
        {
            System.out.println(match.getAwayTeam() + ": " + match.getAwayTeamScore() + "--" +
                    match.getHomeTeam() + ": " + match.getHomeTeamScore());
        }
        System.out.println("----------------Finish all, but match1------------------");
        scoreBoard.finishMatch(match4);
        scoreBoard.finishMatch(match3);
        scoreBoard.finishMatch(match2);
        scoreBoard.finishMatch(match5);
        orderedMatchScoreList = scoreBoard.getOrderedMatchList();
        for(MatchClass match: orderedMatchScoreList)
        {
            System.out.println(match.getAwayTeam() + ": " + match.getAwayTeamScore() + "--" +
                    match.getHomeTeam() + ": " + match.getHomeTeamScore());
        }
    }
}