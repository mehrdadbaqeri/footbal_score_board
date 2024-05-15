import org.junit.Assert;
import org.junit.Test;
import sportradar.scoreboard.MatchClass;

import java.security.InvalidParameterException;

import static org.junit.Assert.fail;

/**
 * The test suite for ScoreBoardClass
 */
public class MatchClassTests {
    /**
     * Generate random integer test data
     * @param min The minimum of the random value range
     * @param max The maximum of the random value range
     * @return The random value
     */
    public int getRandomInteger(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    /**
     * Generate random long long test data
     * @param min The minimum of the random value range
     * @param max The maximum of the random value range
     * @return The random value
     */
    public long getRandomLong(long min, long max) {
        return (long) ((Math.random() * (max - min)) + min);
    }

    /**
     * Test checking the expected match instance can be created
     */
    @Test
    public void test_match_instantiate(){
        long matchId1 = getRandomLong(0, Long.MAX_VALUE);
        String match1HomeTeam = "Match1Home";
        String match1AwayTeam = "Match1Home";
        MatchClass match1 = new MatchClass(matchId1, match1HomeTeam, match1AwayTeam);
        Assert.assertEquals(matchId1, match1.getMatchId());
        Assert.assertEquals(match1HomeTeam, match1.getHomeTeam());
        Assert.assertEquals(match1AwayTeam, match1.getAwayTeam());
        Assert.assertEquals(0, match1.getAwayTeamScore());
        Assert.assertEquals(0, match1.getHomeTeamScore());
    }

    /**
     * Test checking multiple match instance can be created
     */
    @Test
    public void test_match_instantiate_multiple(){
        long matchId1 = getRandomLong(0, Long.MAX_VALUE);
        String match1HomeTeam = "Match1Home";
        String match1AwayTeam = "Match1Home";
        MatchClass match1 = new MatchClass(matchId1, match1HomeTeam, match1AwayTeam);
        Assert.assertEquals(matchId1, match1.getMatchId());
        Assert.assertEquals(match1HomeTeam, match1.getHomeTeam());
        Assert.assertEquals(match1AwayTeam, match1.getAwayTeam());
        Assert.assertEquals(0, match1.getAwayTeamScore());
        Assert.assertEquals(0, match1.getHomeTeamScore());

        long matchId2 = getRandomLong(0, Long.MAX_VALUE);
        String match2HomeTeam = "Match2Home";
        String match2AwayTeam = "Match2Home";
        MatchClass match2 = new MatchClass(matchId2, match2HomeTeam, match2AwayTeam);
        Assert.assertEquals(matchId2, match2.getMatchId());
        Assert.assertEquals(match2HomeTeam, match2.getHomeTeam());
        Assert.assertEquals(match2AwayTeam, match2.getAwayTeam());
        Assert.assertEquals(0, match2.getAwayTeamScore());
        Assert.assertEquals(0, match2.getHomeTeamScore());

        long matchId3 = getRandomLong(0, Long.MAX_VALUE);
        String match3HomeTeam = "Match3Home";
        String match3AwayTeam = "Match3Home";
        MatchClass match3 = new MatchClass(matchId3, match3HomeTeam, match3AwayTeam);
        Assert.assertEquals(matchId3, match3.getMatchId());
        Assert.assertEquals(match3HomeTeam, match3.getHomeTeam());
        Assert.assertEquals(match3AwayTeam, match3.getAwayTeam());
        Assert.assertEquals(0, match3.getAwayTeamScore());
        Assert.assertEquals(0, match3.getHomeTeamScore());
    }

    /**
     * Test checking the update of the match scores
     */
    @Test
    public void test_match_update_scores(){
        long matchId1 = getRandomLong(0, Long.MAX_VALUE);
        String match1HomeTeam = "Match1Home";
        String match1AwayTeam = "Match1Home";
        int match1HomeScore = getRandomInteger(0, Integer.MAX_VALUE);
        int match1AwayScore = getRandomInteger(0, Integer.MAX_VALUE);
        long matchId2 = getRandomLong(0, Long.MAX_VALUE);
        String match2HomeTeam = "Match2Home";
        String match2AwayTeam = "Match3Home";
        int match2HomeScore = getRandomInteger(0, Integer.MAX_VALUE);
        int match2AwayScore = getRandomInteger(0, Integer.MAX_VALUE);
        long matchId3 = getRandomLong(0, Long.MAX_VALUE);
        String match3HomeTeam = "Match3Home";
        String match3AwayTeam = "Match3Home";
        int match3HomeScore = getRandomInteger(0, Integer.MAX_VALUE);
        int match3AwayScore = getRandomInteger(0, Integer.MAX_VALUE);

        MatchClass match1 = new MatchClass(matchId1, match1HomeTeam, match1AwayTeam);
        MatchClass match2 = new MatchClass(matchId2, match2HomeTeam, match2AwayTeam);
        MatchClass match3 = new MatchClass(matchId3, match3HomeTeam, match3AwayTeam);

        match1.setAwayTeamScore(match1AwayScore);
        match1.setHomeTeamScore(match1HomeScore);
        match2.setAwayTeamScore(match2AwayScore);
        match2.setHomeTeamScore(match2HomeScore);
        match3.setAwayTeamScore(match3AwayScore);
        match3.setHomeTeamScore(match3HomeScore);

        Assert.assertEquals(match1HomeScore, match1.getHomeTeamScore());
        Assert.assertEquals(match1AwayScore, match1.getAwayTeamScore());
        Assert.assertEquals(match2HomeScore, match2.getHomeTeamScore());
        Assert.assertEquals(match2AwayScore, match2.getAwayTeamScore());
        Assert.assertEquals(match3HomeScore, match3.getHomeTeamScore());
        Assert.assertEquals(match3AwayScore, match3.getAwayTeamScore());
    }

    /**
     * Test checking the update of the match scores
     */
    @Test
    public void test_match_update_scores_invalid_score(){
        long matchId1 = getRandomLong(0, Long.MAX_VALUE);
        String match1HomeTeam = "Match1Home";
        String match1AwayTeam = "Match1Home";
        int match1HomeScore = getRandomInteger(0, Integer.MAX_VALUE);
        int match1AwayScore = getRandomInteger(Integer.MIN_VALUE, 0);
        long matchId2 = getRandomLong(0, Long.MAX_VALUE);
        String match2HomeTeam = "Match2Home";
        String match2AwayTeam = "Match3Home";
        int match2HomeScore = getRandomInteger(Integer.MIN_VALUE, 0);
        int match2AwayScore = getRandomInteger(0, Integer.MAX_VALUE);
        MatchClass match1 = new MatchClass(matchId1, match1HomeTeam, match1AwayTeam);
        MatchClass match2 = new MatchClass(matchId2, match2HomeTeam, match2AwayTeam);

        match1.setHomeTeamScore(match1HomeScore);
        Assert.assertEquals(match1HomeScore, match1.getHomeTeamScore());
        try{
            match1.setAwayTeamScore(match1AwayScore);
            fail("Expect it to throw exception");
        }
        catch (Exception actualException){
            Assert.assertEquals(InvalidParameterException.class, actualException.getClass());
        }

        match2.setAwayTeamScore(match2AwayScore);
        Assert.assertEquals(match2AwayScore, match2.getAwayTeamScore());
        try{
            match2.setHomeTeamScore(match2HomeScore);
            fail("Expect it to throw exception");
        }
        catch (Exception actualException){
            Assert.assertEquals(InvalidParameterException.class, actualException.getClass());
        }
    }

}
