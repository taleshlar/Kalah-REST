package com.raysep.kalah.api.domain;

import java.io.Serializable;
import java.util.Objects;

import lombok.Getter;
import com.raysep.kalah.api.util.Constants;


/**
 * Class that represents the Pit.
 */
@Getter
public class Pit implements Serializable {

    private static final long serialVersionUID = -305363298175037639L;
    /**
     * Pit's ID (start with {@link Constants#INITIAL_PIT_ID} and end with {@link Constants#FINAL_PIT_ID)
     */
    private Integer id;
    /**
     * Current amount of stones in the pit.
     */
    private Integer stones;
    /**
     * Next pit to move.
     */
    private Pit next;
    /**
     * Pit's player.
     */
    private Player player;
    /**
     * Is kalah?
     */
    private boolean kalah;

    /**
     * Adds {@param amountStones} to the pit.
     *
     * @param amountStones Amount of stones.
     * @return Pit
     */
    public Pit addStones(final Integer amountStones) {
        stones += amountStones;
        return this;
    }

    /**
     * Sets the ID.
     *
     * @param id ID
     * @return PIT
     */
    public Pit id(final Integer id) {
        this.id = id;
        return this;
    }

    /**
     * Sets the amount of stones.
     *
     * @param stones Amount of stones.
     * @return Pit.
     */
    public Pit stones(final Integer stones) {
        this.stones = stones;
        return this;
    }

    /**
     * Sets the next Pit.
     *
     * @param next Next Pit.
     * @return Pit.
     */
    public Pit next(final Pit next) {
        this.next = next;
        return this;
    }

    /**
     * Sets the player who owns the Pit.
     *
     * @param player Player.
     * @return Pit.
     */
    public Pit player(final Player player) {
        this.player = player;
        return this;
    }

    /**
     * Sets if the pit is kalah or not.
     *
     * @param kalah Is kalah?
     * @return Pit
     */
    public Pit kalah(final boolean kalah) {
        this.kalah = kalah;
        return this;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final Pit pit = (Pit) obj;
        return Objects.equals(id, pit.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        final Integer nextId = Objects.nonNull(next) ? next.getId() : null;
        return "Pit{" +
                "id=" + id +
                ", stones=" + stones +
                ", next=" + nextId +
                ", player=" + player +
                ", kalah=" + kalah +
                '}';
    }
}
