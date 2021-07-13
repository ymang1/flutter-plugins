package dk.cachet.notifications;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build.VERSION_CODES;
import androidx.annotation.RequiresApi;
import io.flutter.plugin.common.EventChannel.EventSink;
import java.util.HashMap;

/**
 * Receives events from @NotificationListener
 **/
public class NotificationReceiver extends BroadcastReceiver {

  private EventSink eventSink;

  public NotificationReceiver(EventSink eventSink) {
    this.eventSink = eventSink;
  }

  @RequiresApi(api = VERSION_CODES.JELLY_BEAN_MR2)
  @Override
  public void onReceive(Context context, Intent intent) {
    // Unpack intent contents
    String packageName = intent.getStringExtra(NotificationListener.NOTIFICATION_PACKAGE_NAME);
    String packageTitle = intent.getStringExtra(NotificationListener.NOTIFICATION_TITLE);
    String packageMessage = intent.getStringExtra(NotificationListener.NOTIFICATION_MESSAGE);
    String userName = intent.getStringExtra(NotificationListener.NOTIFICATION_USER_NAME);
    
    // Send data back via the Event Sink
    HashMap<String, Object> data = new HashMap<>();
    data.put("packageName", packageName);
    data.put("packageTitle", packageTitle);
    data.put("packageMessage", packageMessage);
    map.put("userName", userName);
    eventSink.success(data);
  }
}
