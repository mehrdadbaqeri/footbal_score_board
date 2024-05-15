import org.junit.Assert;
import org.junit.Test;
import sportradar.scoreboard.MatchClass;
import sportradar.scoreboard.ScoreBoardClass;

import java.security.InvalidParameterException;
import java.util.List;

import static org.junit.Assert.fail;

/**
 * The test suite for ScoreBoardClass
 */
public class ScoreBoardClassTests {

    /**
     * Test checking the expected score board instance can be created
     */
    @Test
    public void test_scoreboard_instantiate(){
        ScoreBoardClass scoreBoardClass = new ScoreBoardClass();
        Assert.assertEquals(0, scoreBoardClass.getMatchIdIncIdx());
        Assert.assertEquals(0, scoreBoardClass.getMatchList().size());
    }

    /**
     * Test checking if multiple matches can be started
     */
    @Test
    public void test_scoreboard_start_match(){
        ScoreBoardClass scoreBoard = new ScoreBoardClass();
        long match1 = scoreBoard.startNewMatch("Germany", "Spain");
        long match2 = scoreBoard.startNewMatch("Iran", "Brazil");
        long match3 = scoreBoard.startNewMatch("Japan", "England");
        long match4 = scoreBoard.startNewMatch("UAE", "USA");
        long match5 = scoreBoard.startNewMatch("Sweden", "Austria");
        Assert.assertEquals(5, scoreBoard.getMatchList().size());
    }

    /**
     * Test checking if started matches can be finished
     */
    @Test
    public void test_scoreboard_finish_match(){
        ScoreBoardClass scoreBoard = new ScoreBoardClass();
        long match1 = scoreBoard.startNewMatch("Germany", "Spain");
        long match2 = scoreBoard.startNewMatch("Iran", "Brazil");
        long match3 = scoreBoard.startNewMatch("Japan", "England");
        long match4 = scoreBoard.startNewMatch("UAE", "USA");
        long match5 = scoreBoard.startNewMatch("Sweden", "Austria");
        Assert.assertEquals(5, scoreBoard.getMatchList().size());
        scoreBoard.finishMatch(match2);
        Assert.assertEquals(5, scoreBoard.getMatchIdIncIdx());
        Assert.assertEquals(4, scoreBoard.getMatchList().size());
        scoreBoard.finishMatch(match1);
        Assert.assertEquals(5, scoreBoard.getMatchIdIncIdx());
        Assert.assertEquals(3, scoreBoard.getMatchList().size());
        try{
            scoreBoard.finishMatch(match2);
            fail("It is expected to throw InvalidParameterException");
        }
        catch (Exception actualException){
            Assert.assertEquals(InvalidParameterException.class, actualException.getClass());
        }
    }

    /**
     * Test checking if match scores can be updated
     */
    @Test
    public void test_scoreboard_update_match_score(){
        ScoreBoardClass scoreBoard = new ScoreBoardClass();
        long match1 = scoreBoard.startNewMatch("Germany", "Spain");
        long match2 = scoreBoard.startNewMatch("Iran", "Brazil");
        long match3 = scoreBoard.startNewMatch("Japan", "England");

        scoreBoard.updateMatchScore(match1, 3, 10);
        Assert.assertEquals(3, scoreBoard.getHomeScore(match1));
        Assert.assertEquals(10, scoreBoard.getAwayScore(match1));

        scoreBoard.updateMatchHomeScore(match3, 7);
        Assert.assertEquals(7, scoreBoard.getHomeScore(match3));
        Assert.assertEquals(0, scoreBoard.getAwayScore(match3));

        scoreBoard.updateMatchAwayScore(match2, 12);
        Assert.assertEquals(0, scoreBoard.getHomeScore(match2));
        Assert.assertEquals(12, scoreBoard.getAwayScore(match2));

        scoreBoard.updateMatchAwayScore(match1, 13);
        Assert.assertEquals(3, scoreBoard.getHomeScore(match1));
        Assert.assertEquals(13, scoreBoard.getAwayScore(match1));
    }

    /**
     * Test checking getting a sorted summary
     */
    @Test
    public void test_scoreboard_get_summary(){
        ScoreBoardClass scoreBoard = new ScoreBoardClass();
        long match1 = scoreBoard.startNewMatch("Germany", "Spain");
        long match2 = scoreBoard.startNewMatch("Iran", "Brazil");
        long match3 = scoreBoard.startNewMatch("Japan", "England");
        long match4 = scoreBoard.startNewMatch("UAE", "USA");
        long match5 = scoreBoard.startNewMatch("Sweden", "Austria");
        scoreBoard.updateMatchScore(match1, 3, 1);
        scoreBoard.updateMatchScore(match2, 3, 4);
        scoreBoard.updateMatchScore(match3, 0, 7);
        scoreBoard.updateMatchScore(match4, 7, 0);
        scoreBoard.updateMatchScore(match5, 3, 10);

        List<MatchClass> orderedMatchScoreList = scoreBoard.getOrderedMatchList();
        Assert.assertEquals("Sweden", orderedMatchScoreList.getFirst().getHomeTeam());
        Assert.assertEquals("Austria", orderedMatchScoreList.getFirst().getAwayTeam());
        Assert.assertEquals("UAE", orderedMatchScoreList.get(1).getHomeTeam());
        Assert.assertEquals("USA", orderedMatchScoreList.get(1).getAwayTeam());
        Assert.assertEquals("Japan", orderedMatchScoreList.get(2).getHomeTeam());
        Assert.assertEquals("England", orderedMatchScoreList.get(2).getAwayTeam());
        Assert.assertEquals("Iran", orderedMatchScoreList.get(3).getHomeTeam());
        Assert.assertEquals("Brazil", orderedMatchScoreList.get(3).getAwayTeam());
        Assert.assertEquals("Germany", orderedMatchScoreList.getLast().getHomeTeam());
        Assert.assertEquals("Spain", orderedMatchScoreList.getLast().getAwayTeam());
    }
}
