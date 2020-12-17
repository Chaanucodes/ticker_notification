package com.example.appdemo

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.*
import android.graphics.drawable.Icon
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters


class RefreshDataWorker(appContext: Context, params: WorkerParameters) :
    CoroutineWorker(appContext, params) {

    companion object {
        var notificationTitle = "My notification"
    }

    override suspend fun doWork(): Result {
        try {
            createNotificationChannel()
            setUpTheNoti()
        } catch (ignore: Exception) {
            return Result.failure()
        }
        return Result.success()
    }

    private fun setUpTheNoti() {

        val contentIntent = Intent(applicationContext, MainActivity::class.java)
                .putExtra(applicationContext.resources.getString(R.string.keyIntent),
                        applicationContext.resources.getString(R.string.keyOfIntent))
        val pendingIntent = PendingIntent.getActivity(
                applicationContext,
                REQ_CODE,
                contentIntent,
                PendingIntent.FLAG_UPDATE_CURRENT
        )


        var builder = NotificationCompat.Builder(applicationContext, CHANNEL_ID)
            .setLargeIcon(BitmapFactory.decodeResource(applicationContext.resources, R.drawable.vector))

            .setSmallIcon(R.drawable.ic_baseline_phone_android_24)
            .setContentTitle(notificationTitle)
            .setContentText("DEMO TEXT")
            .setStyle(NotificationCompat.BigTextStyle()
                .bigText(
                    "This is a demo app. Demo work notification featuring ticker ability"))
            .setPriority(NotificationCompat.PRIORITY_MAX)
            .setTicker("Demo app ticker")
            .setContentText("Demo app")
            .setContentIntent(pendingIntent)
//            .addAction(R.drawable.ic_baseline_phone_android_24, "Action 1", pendingIntent)
            .also {
                it.color =  applicationContext.resources.getColor(R.color.colorPrimary)
                it.setColorized(true)
            }
        with(NotificationManagerCompat.from(applicationContext)) {
            // notificationId is a unique int for each notification that you must define
            notify(notificationId, builder.build())
        }
    }

    private fun createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "DEFAULT CHANNEL NAME"
            val descriptionText = "DEFAULT DESCRIPTION DEMO"
            val importance = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }
            // Register the channel with the system
            val notificationManager: NotificationManager =
                ContextCompat.getSystemService(applicationContext, NotificationManager::class.java) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

}
