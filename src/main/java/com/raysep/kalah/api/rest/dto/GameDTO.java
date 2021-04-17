package com.raysep.kalah.api.rest.dto;

import java.io.Serializable;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

/**
 * DTO that represents the Game.
 */
@ApiModel(description = "DTO that represents the Game.")
@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
public class GameDTO implements Serializable {

    private static final long serialVersionUID = -140598950020070176L;
    @ApiModelProperty(value = "Unique identifier of this game.", required = true, notes = "* It's always a number.")
    private Long id;
    @ApiModelProperty("URL to the game.")
    private String url;
    @ApiModelProperty("Key-value object where the key is the pit ID and value is the number of stones in the pit.")
    private Map<Integer, Integer> status;
    @ApiModelProperty("Message telling who won the game.")
    private String endGameMessage;

    /**
     * Sets the game ID.
     *
     * @param id ID
     * @return GameDTO
     */
    public GameDTO id(final Long id) {
        this.id = id;
        return this;
    }

    /**
     * Sets the URL.
     *
     * @param url URL.
     * @return GameDTO
     */
    public GameDTO url(final String url) {
        this.url = url;
        return this;
    }

    /**
     * Sets the status.
     *
     * @param status Map<Integer, Integer>
     * @return GameDTO
     */
    public GameDTO status(final Map<Integer, Integer> status) {
        this.status = status;
        return this;
    }

    /**
     * Sets the end game message.
     *
     * @param endGameMessage End game message.
     * @return GameDTO
     */
    public GameDTO endGameMessage(final String endGameMessage) {
        this.endGameMessage = endGameMessage;
        return this;
    }
}
