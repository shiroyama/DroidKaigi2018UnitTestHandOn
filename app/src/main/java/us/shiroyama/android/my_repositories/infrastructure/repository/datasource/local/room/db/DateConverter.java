package us.shiroyama.android.my_repositories.infrastructure.repository.datasource.local.room.db;

import android.arch.persistence.room.TypeConverter;
import android.support.annotation.NonNull;

import org.threeten.bp.Instant;
import org.threeten.bp.ZoneId;
import org.threeten.bp.ZonedDateTime;

/**
 * Type Converter from and to {@link ZonedDateTime} and long
 *
 * @author Fumihiko Shiroyama
 */

public class DateConverter {
  @TypeConverter
  public static ZonedDateTime fromTimeToDate(@NonNull Long time) {
    return ZonedDateTime.ofInstant(Instant.ofEpochSecond(time), ZoneId.systemDefault());
  }

  @TypeConverter
  public static Long fromDateToTime(@NonNull ZonedDateTime date) {
    return date.toInstant().getEpochSecond();
  }
}
