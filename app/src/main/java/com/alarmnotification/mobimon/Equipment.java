package com.alarmnotification.mobimon;

/**
 * Created by Ryan L. Vu on 6/11/2016.
 */
public class Equipment extends Item {

    private int atk;
    private int def;

    public int getAtk() {
        return atk;
    }

    public void setAtk(int atk) {
        this.atk = atk;
    }

    public int getDef() {
        return def;
    }

    public void setDef(int def) {
        this.def = def;
    }
}
