package us.shiroyama.android.my_repositories.common;

import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.TypeAdapter;

import org.threeten.bp.ZonedDateTime;
import org.threeten.bp.format.DateTimeFormatter;

import java.lang.reflect.Type;

/**
 * Singleton {@link Gson} Provider
 *
 * @author Fumihiko Shiroyama
 */

public enum GsonProvider implements Singleton<Gson> {
  INSTANCE;

  @NonNull
  private final Gson gson = new GsonBuilder()
      .registerTypeAdapter(ZonedDateTime.class, new ZonedDateTimeDeserializer())
      .create();

  @NonNull
  @Override
  public Gson get() {
    return gson;
  }

  /**
   * {@link TypeAdapter} for {@link ZonedDateTime}
   */
  private static class ZonedDateTimeDeserializer implements JsonDeserializer<ZonedDateTime> {
    @Override
    public ZonedDateTime deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
      String zonedDateTime = json.getAsString();
      DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_DATE_TIME;
      return ZonedDateTime.parse(zonedDateTime, dateTimeFormatter);
    }
  }
}
