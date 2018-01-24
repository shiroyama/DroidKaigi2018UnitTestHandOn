package us.shiroyama.android.my_repositories.infrastructure.repository.datasource.remote.service;

import android.support.annotation.NonNull;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import us.shiroyama.android.my_repositories.BuildConfig;
import us.shiroyama.android.my_repositories.common.GsonProvider;
import us.shiroyama.android.my_repositories.common.OkHttpProvider;
import us.shiroyama.android.my_repositories.common.Singleton;

/**
 * Singleton {@link Retrofit} Provider
 *
 * @author Fumihiko Shiroyama
 */

public enum RetrofitProvider implements Singleton<Retrofit> {
  INSTANCE;

  @NonNull
  private final Retrofit instance = new Retrofit.Builder()
          .baseUrl(BuildConfig.API_BASE_URL)
          .addConverterFactory(GsonConverterFactory.create(GsonProvider.INSTANCE.get()))
          .client(OkHttpProvider.INSTANCE.get())
          .build();

  @NonNull
  @Override
  public Retrofit get() {
    return instance;
  }
}
