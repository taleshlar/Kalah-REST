package com.raysep.kalah.api.handler.impl;

import com.raysep.kalah.api.KalahApplication;
import com.raysep.kalah.api.domain.Game;
import com.raysep.kalah.api.domain.GameTest;
import com.raysep.kalah.api.domain.Pit;
import com.raysep.kalah.api.util.Constants;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Test class for the EndTurnRuleHandlerImpl.
 *
 * @see EndTurnRuleHandlerImpl
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = KalahApplication.class)
public class EndTurnRuleHandlerImplTest {

    @Autowired
    private EndTurnRuleHandlerImpl handler;

    @Test
    public void canHandleRequestNoMoveDoneTest() {
        final Game game = GameTest.getBasicGame();
        Assert.assertFalse("Shouldn't handler request without previous move", handler.canHandleRequest(game, 1));
    }

    @Test
    public void canHandleRequestWithWinnerTest() {
        final Game game = GameTest.getBasicGame();
        game.setWinner(GameTest.NORTHERN_PLAYER);
        Assert.assertFalse("Shouldn't handler request with a winner", handler.canHandleRequest(game, 1));
    }

    @Test
    public void canHandleRequestWithDrawTest() {
        final Game game = GameTest.getBasicGame();
        game.setDraw(true);
        Assert.assertFalse("Shouldn't handler request with a draw", handler.canHandleRequest(game, 1));
    }

    @Test
    public void handleRequestLastStonePitTest() {
        final Game game = GameTest.getBasicGame();
        Assert.assertSame("The current player should be the " + GameTest.NORTHERN_PLAYER, GameTest.NORTHERN_PLAYER, game.getCurrentPlayer());
        // Simulates the movement
        game.setLastSownPit(game.getPitAt(1));
        handler.handleRequest(game, 1);
        // Changes the current player
        Assert.assertSame("The current player should be the " + GameTest.SOUTHERN_PLAYER, GameTest.SOUTHERN_PLAYER, game.getCurrentPlayer());
    }

    @Test
    public void handleRequestLastStoneKalahTest() {
        final Game game = GameTest.getBasicGame();
        Assert.assertSame("The current player should be the " + GameTest.NORTHERN_PLAYER, GameTest.NORTHERN_PLAYER, game.getCurrentPlayer());
        // Simulates the movement
        game.setLastSownPit(game.getKalahFrom(GameTest.NORTHERN_PLAYER));
        handler.handleRequest(game, Constants.NORTHERN_KALAH_ID);
        // Don't change the current player
        Assert.assertSame("The current player should be the " + GameTest.NORTHERN_PLAYER, GameTest.NORTHERN_PLAYER, game.getCurrentPlayer());
    }

    @Test
    public void handleRequestCurrentPlayerWinsTest() {
        final Game game = GameTest.getBasicGame();
        game.clearPits();
        // Simulates the movement
        // Northern/current player run out of stones
        final Pit northernKalah = game.getKalahFrom(GameTest.NORTHERN_PLAYER);
        northernKalah.stones(10);
        game.getPitAt(1).stones(2);

        final Pit southernKalah = game.getKalahFrom(GameTest.SOUTHERN_PLAYER);
        southernKalah.stones(5);

        game.setLastSownPit(northernKalah);

        handler.handleRequest(game, Constants.NORTHERN_KALAH_ID);

        Assert.assertSame("The southern kalah should have 5 stones", 5, southernKalah.getStones());
        Assert.assertSame("The northern kalah should have 12 stones", 12, northernKalah.getStones());
        Assert.assertSame("The southern player should have 0 stones in his/her pits", 0, game
                .getAmountOfStonesFrom(GameTest.SOUTHERN_PLAYER));
        Assert.assertSame("The northern kalah should have 0 stones in his/her pits", 0, game
                .getAmountOfStonesFrom(GameTest.NORTHERN_PLAYER));

        Assert.assertSame("The winning player should be the " + GameTest.NORTHERN_PLAYER, GameTest.NORTHERN_PLAYER, game.getWinner());
    }

    @Test
    public void handleRequestOtherPlayerWinsTest() {
        final Game game = GameTest.getBasicGame();
        game.clearPits();
        game.changePlayers();
        // Simulates the movement
        // Southern/current player run out of stones
        final Pit northernKalah = game.getKalahFrom(GameTest.NORTHERN_PLAYER);
        northernKalah.stones(10);

        final Pit southernKalah = game.getKalahFrom(GameTest.SOUTHERN_PLAYER);
        southernKalah.stones(5);
        game.getPitAt(Constants.FINAL_PIT_ID - 1).stones(2);

        game.setLastSownPit(southernKalah);

        handler.handleRequest(game, Constants.FINAL_PIT_ID);

        Assert.assertSame("The southern kalah should have 7 stones", 7, southernKalah.getStones());
        Assert.assertSame("The northern kalah should have 10 stones", 10, northernKalah.getStones());
        Assert.assertSame("The southern player should have 0 stones in his/her pits", 0, game
                .getAmountOfStonesFrom(GameTest.SOUTHERN_PLAYER));
        Assert.assertSame("The northern kalah should have 0 stones in his/her pits", 0, game
                .getAmountOfStonesFrom(GameTest.NORTHERN_PLAYER));

        Assert.assertSame("The winning player should be the " + GameTest.NORTHERN_PLAYER, GameTest.NORTHERN_PLAYER, game.getWinner());
    }

    @Test
    public void handleRequestDrawTest() {
        final Game game = GameTest.getBasicGame();
        game.clearPits();

        // Simulates the movement
        game.setLastSownPit(game.getPitAt(1));
        handler.handleRequest(game, 1);

        Assert.assertTrue("It should be a draw", game.isDraw());
    }
}
