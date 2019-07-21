package me.minidigger.globalmute.model;

import java.util.UUID;

/**
 * A player connects to a server. Can be administrated by a user or a team
 */
public class Server {

    private UUID id;
    private Level level;
    private User owner;
    private Team team; // optional
    private Network network; // optional
}
