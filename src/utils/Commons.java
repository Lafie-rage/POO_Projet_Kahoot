package utils;

/**
 * Class containing definition of commons final values shared in the whole project.
 */
public class Commons {
    // region Network configuration
    public static final String HOST = "127.0.0.1";
    public static final int PORT = 60_000;
    // endregion

    // region Communication signals
    public static final String ENDING_CONNECTION_SIGNAL = "ENDING_CONNECTION";
    public static final String CLIENT_READY_SIGNAL = "CLIENT_READY";
    // endregion

    public static final int NB_QUESTIONS = 50;
    public static final int MAX_PLAYER_IN_ROOM = 1;

}
