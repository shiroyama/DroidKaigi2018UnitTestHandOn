package us.shiroyama.android.my_repositories.infrastructure.repository.datasource.remote;

import android.support.annotation.NonNull;

import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Response;
import us.shiroyama.android.my_repositories.infrastructure.entity.GitHubErrorEntity;
import us.shiroyama.android.my_repositories.infrastructure.exception.ApiException;

/**
 * Base REST Data Source
 *
 * @author Fumihiko Shiroyama
 */

public class BaseRestDataSource<T> {
  @NonNull
  private final Gson gson;

  public BaseRestDataSource(@NonNull Gson gson) {
    this.gson = gson;
  }

  public T stripResult(Response<T> response) throws IOException, ApiException {
    if (response.isSuccessful()) {
      T body = response.body();
      assert body != null;
      return body;
    }
    try (ResponseBody responseBody = response.errorBody()) {
      if (responseBody == null) {
        throw new ApiException();
      }
      GitHubErrorEntity gitHubErrorEntity = gson.fromJson(responseBody.charStream(), GitHubErrorEntity.class);
      throw new ApiException(gitHubErrorEntity);
    }
  }
}
