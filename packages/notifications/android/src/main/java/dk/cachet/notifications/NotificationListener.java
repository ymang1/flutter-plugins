package dk.cachet.notifications;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.content.Intent;
import android.os.Build.VERSION_CODES;
import android.os.Bundle;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import androidx.annotation.RequiresApi;

/**
 * Notification listening service. Intercepts notifications if permission is given to do so.
 */
@SuppressLint("OverrideAbstract")
@RequiresApi(api = VERSION_CODES.JELLY_BEAN_MR2)
public class NotificationListener extends NotificationListenerService {

  public static String NOTIFICATION_INTENT = "notification_event";
  public static String NOTIFICATION_USER_NAME = "user_name";
  public static String NOTIFICATION_PACKAGE_NAME = "package_name";
  public static String NOTIFICATION_PACKAGE_MESSAGE = "package_message";
  public static String NOTIFICATION_PACKAGE_TITLE = "package_title";
  public static String NOTIFICATION_PACKAGE_EXTRA = "package_extra";

  @RequiresApi(api = VERSION_CODES.KITKAT)
  @Override
  public void onNotificationPosted(StatusBarNotification notification) {
    String userName = sbn.getUser().toString();
    // Retrieve package name to set as title.
    String packageName = notification.getPackageName();
    // Retrieve extra object from notification to extract payload.
    Bundle extras = notification.getNotification().extras;

    // Pass data from one activity to another.
    Intent intent = new Intent(NOTIFICATION_INTENT);
    intent.putExtra(NOTIFICATION_PACKAGE_NAME, packageName);

    if(userName != null){
      intent.putExtra(NOTIFICATION_USER_NAME, userName);
  }    

    if (extras != null) {
      CharSequence extraTitle = extras.getCharSequence(Notification.EXTRA_TITLE);
      if (extraTitle != null)
        intent.putExtra(NOTIFICATION_PACKAGE_TITLE, extraTitle.toString());

      CharSequence extraText = extras.getCharSequence(Notification.EXTRA_TEXT);
      if (extraText != null)
        intent.putExtra(NOTIFICATION_PACKAGE_MESSAGE, extraText.toString());        
    }
    sendBroadcast(intent);
  }
}
