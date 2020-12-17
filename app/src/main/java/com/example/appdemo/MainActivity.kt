package com.example.appdemo

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.RemoteViews
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat

const val CHANNEL_ID = "com_ID_MAINDEMOTEMP"
const val notificationId = 3
const val REQ_CODE = 1

class MainActivity : AppCompatActivity() {

    var notificationTitle = "My notification"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }



    fun createNoti(view: View) {
        RefreshData.delayedInit()
    }
}