package youxuntianxiaapp.huimee.com.wanandroid.rx

import youxuntianxiaapp.huimee.com.wanandroid.rx.scheduler.IoMainScheduler

object SchedulerUtils {
    fun <T> ioToMain(): IoMainScheduler<T> = IoMainScheduler()
}