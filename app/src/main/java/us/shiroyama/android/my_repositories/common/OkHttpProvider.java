package us.shiroyama.android.my_repositories.common;

import android.support.annotation.NonNull;

import com.facebook.stetho.okhttp3.StethoInterceptor;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Singleton {@link OkHttpClient} Provider
 *
 * @author Fumihiko Shiroyama
 */

public enum OkHttpProvider implements Singleton<OkHttpClient> {
  INSTANCE;

  @NonNull
  private final OkHttpClient okHttpClient;

  {
    HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
    loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

    okHttpClient = new OkHttpClient.Builder()
        .addNetworkInterceptor(new StethoInterceptor())
        .addInterceptor(loggingInterceptor)
        .build();
  }

  @NonNull
  @Override
  public OkHttpClient get() {
    return okHttpClient;
  }
}
