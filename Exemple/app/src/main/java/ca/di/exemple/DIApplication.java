package ca.di.exemple;

import android.app.Application;
import android.content.Context;

import com.dianalytics.DIAnalytics;

/**
 * Created by kevin on 2016-10-31.
 */

public class DIApplication extends Application {

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }

    @Override
    public void onCreate() {
        //Application class super
        super.onCreate();

        //Set the base url for all calls to the server
        DIAnalytics.setBaseUrl("https://dev.ofsys.com/");

        //Enable logs to be displayed
        DIAnalytics.setLogEnabled(true);

        //Register your application to the service
        DIAnalytics.startWithApplicationId(this, "44:Gm2KBfOoDhXyf5cFJ58hG0CkOChq1OqV");
    }
}
