package us.shiroyama.android.my_repositories.common;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;

import com.squareup.otto.Bus;

/**
 * Singleton {@link Bus} Provider
 *
 * @author Fumihiko Shiroyama
 */

public enum BusProvider implements Singleton<Bus> {
  INSTANCE;

  @NonNull
  private final Bus bus = new MainThreadBus();

  @NonNull
  @Override
  public Bus get() {
    return bus;
  }

  /**
   * {@link Bus} that always post on Main Thread
   */
  private static class MainThreadBus extends Bus {
    private final Handler handler = new Handler(Looper.getMainLooper());

    @Override
    public void post(Object event) {
      if (Looper.myLooper() == Looper.getMainLooper()) {
        super.post(event);
      } else {
        handler.post(() -> super.post(event));
      }
    }
  }
}
