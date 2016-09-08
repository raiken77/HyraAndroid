package comp5216.sydney.edu.au.hyra.Resources;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import comp5216.sydney.edu.au.hyra.DatabaseResource.Database;

/**
 * Created by apple on 10/19/15.
 */
public class NotificationService extends Service {

    Database data;

    IBinder mBinder;
    boolean mAllowRebind;
    int startMode;

    public NotificationService() {
        super();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        data = new Database(getApplicationContext());


    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return startMode;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return mAllowRebind;
    }


}
