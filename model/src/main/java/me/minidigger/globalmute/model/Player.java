package me.minidigger.globalmute.model;

import java.util.List;
import java.util.UUID;

/**
 * A player. Can send messages. Can be punished
 */
public class Player {

    private UUID uuid;
    private String mojangDisplayName;
    private List<Punishment> punishments;
    private List<Message> messages;
    private List<Server> servers;
    private User user; // optional
}
