package Object;

/**
 * Created by Ryan L. Vu on 6/18/2016.
 */
public class    GlobalContants {

    private GlobalContants() {}

    public static final long HP_DROP_INTERVAL = 30 * 60 * 1000;
    // User data preferences
    public static final String USER_PREF = "User preferences";
    // User data preferences keys
    public static final String START_TIME = "Start time";
    public static final String CUR_HP = "Current hp";
    public static final String MONEY = "Money";
    public static final String LAST_ACTIVE = "Last active time";
    public static final String HEALTH_STATE = "Health state";

    // Alarm
    public static final int HUNGER_ALARM = 0;

    // Notification
    public static final int HUNGER_NOTI = 0;

    // HEALTH_STATE values
    public static final int FULL_HEALTH = 2;
    public static final int HALF_HEALTH = 1;
    public static final int NO_HEALTH = 0;

    // Activity Request code
    public static final int SPLASH_ART_REQ_CODE = 0;

    // Set alarm state
    public static final boolean SET_ALARM_ENABLED = true;
    public static final boolean SET_ALARM_DISABLED = false;
}
