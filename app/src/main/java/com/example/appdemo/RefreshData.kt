package com.example.appdemo

import android.util.Log
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit

object RefreshData  {

    private val applicationScope = CoroutineScope(Dispatchers.Default)

    fun delayedInit(duration : Long = 1) {
        applicationScope.launch {
            Log.i("...", "..a.a.")
            setupRecurringWork(duration)
        }
    }

    private fun setupRecurringWork(duration : Long = 5) {
        val myWorkRequest = OneTimeWorkRequestBuilder<RefreshDataWorker>()
            .setInitialDelay(duration, TimeUnit.SECONDS)
            .build()

        WorkManager
            .getInstance()
            .enqueueUniqueWork(
                "DemoWORKS",
                ExistingWorkPolicy.KEEP,
                myWorkRequest)
    }
}