package com.raysep.kalah.api.handler;

import com.raysep.kalah.api.domain.Game;
import org.springframework.core.Ordered;

/**
 * Rule's handle interface.
 */
public interface RuleHandler extends Ordered {

    /**
     * Constant that defines the precedence value for the validations before the sows. At this stage, nothing in the game is changed.
     */
    int VALIDATION_BEFORE_SOWS_PRECEDENCE = HIGHEST_PRECEDENCE;

    /**
     * Useful constant for the sows precedence value. This phase is responsible for sowing and defines which was the last sowed pit.
     */
    int SOWS_PRECEDENCE = 1;

    /**
     * Constant that defines the precedence value for the validations after the sows. At this stage, only the amount of stones should be
     * changed in the game.
     */
    int VALIDATION_AFTER_SOWS_PRECEDENCE = 2;

    /**
     * Useful constant for the end of turn precedence value. Final phase that defines who is the next player and if there is a winner.
     */
    int END_TURN_PRECEDENCE = LOWEST_PRECEDENCE;

    /**
     * Handles the game request.
     *
     * @param game  The game.
     * @param pitId The ID of the chosen pit.
     */
    void handle(final Game game, final Integer pitId);
}
