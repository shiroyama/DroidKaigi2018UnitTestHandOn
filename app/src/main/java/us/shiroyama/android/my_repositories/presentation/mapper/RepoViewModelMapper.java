package us.shiroyama.android.my_repositories.presentation.mapper;

import android.support.annotation.NonNull;

import us.shiroyama.android.my_repositories.common.ListAwareMapper;
import us.shiroyama.android.my_repositories.common.Singleton;
import us.shiroyama.android.my_repositories.domain.model.Repository;
import us.shiroyama.android.my_repositories.presentation.viewmodel.RepoViewModel;


/**
 * Mapper from {@link Repository} to {@link RepoViewModel}
 *
 * @author Fumihiko Shiroyama
 */

public class RepoViewModelMapper extends ListAwareMapper<Repository, RepoViewModel> {
  @NonNull
  @Override
  public RepoViewModel convert(@NonNull Repository repository) {
    return new RepoViewModel(
        repository.getName(),
        repository.getHtmlUrl(),
        repository.getDescription(),
        repository.getOwner().getLoginId(),
        repository.getOwner().getAvatarUrl(),
        repository.getUpdatedAt()
    );
  }

  public enum Factory implements Singleton<RepoViewModelMapper> {
    INSTANCE;

    private final RepoViewModelMapper instance = new RepoViewModelMapper();

    @NonNull
    @Override
    public RepoViewModelMapper get() {
      return instance;
    }
  }
}
