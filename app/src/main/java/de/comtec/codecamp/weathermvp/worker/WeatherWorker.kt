package de.comtec.codecamp.weathermvp.worker

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters

class WeatherWorker(appContext: Context, workerParams: WorkerParameters) :
    Worker(appContext, workerParams) {
    override fun doWork(): Result {

        return Result.success()
    }

}