package com.sunafil.mitienda

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import android.widget.RemoteViews
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.sunafil.mitienda.feature.maps.view.MapsActivity


/**
 * MyFirebaseMessagingService
 *
 * @author Bryam Soto
 * @since 23/12/22
 */

const val CHANNEL_ID = "NOTIFICATION_CHANNEL"
const val CHANNEL_NAME = "com.sunafil.mitienda"

class MyFirebaseMessagingService : FirebaseMessagingService() {

    private val TAG = MyFirebaseMessagingService::class.java.name

    override fun onMessageReceived(message: RemoteMessage) {
        message.notification?.let {
            generateNotification(it)
        }
    }

    private fun generateNotification(notification: RemoteMessage.Notification) {
        val intent = Intent(this, MapsActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        var pendingIntent = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_MUTABLE)
        } else {
            PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT)
        }
        var builder = NotificationCompat.Builder(applicationContext, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_background)
            .setAutoCancel(true)
            .setVibrate(longArrayOf(1000, 1000, 1000, 1000))
            .setOnlyAlertOnce(true)
            .setContentIntent(pendingIntent)

        builder = builder.setContent(getRemoteView(notification.title, notification.body))

        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationChannel =
                NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_HIGH)
            notificationManager.createNotificationChannel(notificationChannel)
        }

        notificationManager.notify(0, builder.build())
    }

    private fun getRemoteView(title: String?, message: String?): RemoteViews {
        val remoteViews = RemoteViews("com.sunafil.mitienda", R.layout.notification)
        remoteViews.setTextViewText(R.id.title, title ?: "")
        remoteViews.setTextViewText(R.id.message, message ?: "")
        remoteViews.setImageViewResource(R.id.image, R.drawable.ic_notification)
        return remoteViews
    }

    override fun onNewToken(token: String) {
        Log.d(TAG, "Refreshed token: $token")
        // Send token to server
    }

}