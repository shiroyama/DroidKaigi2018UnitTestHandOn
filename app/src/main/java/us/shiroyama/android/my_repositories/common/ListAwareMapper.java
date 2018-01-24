package us.shiroyama.android.my_repositories.common;

import android.support.annotation.NonNull;

import com.annimon.stream.Stream;

import java.util.List;

/**
 * Mapper for List<FROM> to List<TO>
 *
 * @author Fumihiko Shiroyama
 */

public abstract class ListAwareMapper<FROM, TO> implements Mapper<FROM, TO> {
  @NonNull
  public List<TO> convertList(@NonNull List<FROM> fromList) {
    return Stream.of(fromList).map(this::convert).toList();
  }
}
