package us.shiroyama.android.my_repositories.infrastructure.repository.datasource.remote.service;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import us.shiroyama.android.my_repositories.infrastructure.entity.RepositoryEntity;

/**
 * REST Interface for GitHub
 *
 * @author Fumihiko Shiroyama
 */

public interface GitHubService {
  @GET("users/{user}/repos")
  Call<List<RepositoryEntity>> getUserRepositories(
      @Path("user") String user,
      @Query("sort") String sort,
      @Query("direction") String direction
  );
}
