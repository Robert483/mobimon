package com.alarmnotification.mobimon;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.SystemClock;
import android.support.v7.app.NotificationCompat;
import android.support.v4.content.WakefulBroadcastReceiver;
import Object.GlobalContants;

/**
 * Created by Ryan L. Vu on 6/23/2016.
 */
public class HungerReceiver extends WakefulBroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        SharedPreferences data = context.getSharedPreferences(GlobalContants.USER_PREF, Context.MODE_PRIVATE);

        switch (data.getInt(GlobalContants.HEALTH_STATE, GlobalContants.FULL_HEALTH)) {
            case GlobalContants.FULL_HEALTH:
                sendNotification(context, "Thú cưng đang đói bụng!", "Sức khỏe của thú cưng đã dưới 50%, bạn vui lòng cho thú ăn!");
                setAlarm(context, 30 * GlobalContants.HP_DROP_INTERVAL);
                data.edit().putInt(GlobalContants.HEALTH_STATE, GlobalContants.HALF_HEALTH).commit();
                break;
            case GlobalContants.HALF_HEALTH:
                sendNotification(context, "Thú cưng đang RẤT đói!", "Sức khỏe của thú cưng đã dưới 20%, hãy cứu thú cưng của bạn bằng cách cho nó ăn, NGAY BÂY GIỜ!!");
                setAlarm(context, 20 * GlobalContants.HP_DROP_INTERVAL);
                data.edit().putInt(GlobalContants.HEALTH_STATE, GlobalContants.NO_HEALTH).commit();
                break;
            case GlobalContants.NO_HEALTH:
                sendNotification(context, "Thú cưng đã qua đời!", "Bạn đã để thú cưng của mình chết đói!");
                break;
        }
    }

    private  void setAlarm(Context context, long time) {
        AlarmManager alarmMgr = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, HungerReceiver.class);
        PendingIntent alarmIntent = PendingIntent.getBroadcast(context, GlobalContants.HUNGER_ALARM, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        alarmMgr.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, SystemClock.elapsedRealtime() + time, alarmIntent);
    }

    private void sendNotification(Context context, String title, String message) {
        Intent resultIntent = new Intent(context, SplashArtActivity.class);
        resultIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent resultPendingIntent = PendingIntent.getActivity(context, GlobalContants.SPLASH_ART_REQ_CODE, resultIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
        builder.setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(title)
                .setContentText(message)
                .setAutoCancel(true)
                .setContentIntent(resultPendingIntent);

        NotificationManager notiManager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
        notiManager.notify(GlobalContants.HUNGER_NOTI, builder.build());
    }
}
