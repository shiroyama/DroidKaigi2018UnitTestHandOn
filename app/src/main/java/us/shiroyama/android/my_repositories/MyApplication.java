package us.shiroyama.android.my_repositories;


import android.app.Application;

import com.facebook.stetho.Stetho;
import com.jakewharton.threetenabp.AndroidThreeTen;

import timber.log.Timber;

/**
 * Custom {@link Application}
 *
 * @author Fumihiko Shiroyama
 */

public class MyApplication extends Application {

  @Override
  public void onCreate() {
    super.onCreate();

    AndroidThreeTen.init(this);
    Stetho.initializeWithDefaults(this);
    if (BuildConfig.DEBUG) {
      Timber.plant(new Timber.DebugTree());
    }
  }

}
