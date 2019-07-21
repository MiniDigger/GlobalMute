package me.minidigger.globalmute.model;

import java.util.Date;

/**
 * A punishment for a player. Can be issued by a user or automatically
 */
public class Punishment {

    private PunishmentType type;
    private Server server;
    private PunishmentReason reason;
    private Date expirationDate;
}
