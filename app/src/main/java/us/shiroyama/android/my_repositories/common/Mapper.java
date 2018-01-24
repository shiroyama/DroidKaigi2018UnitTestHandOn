package us.shiroyama.android.my_repositories.common;

import android.support.annotation.NonNull;

/**
 * Mapper for FROM to TO
 *
 * @author Fumihiko Shiroyama
 */

public interface Mapper<FROM, TO> {
  @NonNull
  TO convert(@NonNull FROM from);
}
