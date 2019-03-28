package youxuntianxiaapp.huimee.com.wanandroid.app

import android.app.Activity
import android.app.Application
import android.content.Context
import android.os.Bundle
import android.support.multidex.MultiDexApplication
import android.support.v7.app.AppCompatDelegate
import android.util.Log
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.BuildConfig
import com.orhanobut.logger.Logger
import com.orhanobut.logger.PrettyFormatStrategy
import com.squareup.leakcanary.LeakCanary
import com.squareup.leakcanary.RefWatcher
import org.litepal.LitePal
import youxuntianxiaapp.huimee.com.wanandroid.utils.DisplayManager
import youxuntianxiaapp.huimee.com.wanandroid.utils.SettingUtil
import java.util.*
import kotlin.properties.Delegates

class App : MultiDexApplication() {
    private var refWatcher: RefWatcher? = null

    companion object {
        private const val TAG = "App"
        var context: Context by Delegates.notNull()
            private set
        lateinit var instance: Application
        fun getRefWatcher(context: Context): RefWatcher? {
            val app = context.applicationContext as App
            return app.refWatcher
        }

    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        context = applicationContext
        refWatcher = setUpLeakCanary()
        initConfig()
        DisplayManager.init(this)
        initLitePal()
        registerActivityLifecycleCallbacks(mActivityLifecycleCallbacks)
        initTheme()
    }
    /**
     * 初始化 LitePal
     */
    private fun initLitePal() {
    LitePal.initialize(this)

    }
    private fun initTheme() {

        if (SettingUtil.getIsAutoNightMode()) {
            val nightStartHour = SettingUtil.getNightStartHour()!!.toInt()
            val nightStartMinute = SettingUtil.getNightStartMinute()!!.toInt()
            val dayStartHour = SettingUtil.getDayStartHour()!!.toInt()
            val dayStartMinute = SettingUtil.getDayStartMinute()!!.toInt()

            val calendar = Calendar.getInstance()
            val currentHour = calendar.get(Calendar.HOUR_OF_DAY)
            val currentMinute = calendar.get(Calendar.MINUTE)

            val nightValue = nightStartHour * 60 + nightStartMinute
            val dayValue = dayStartHour * 60 + dayStartMinute
            val currentValue = currentHour * 60 + currentMinute

            if (currentValue >= nightValue || currentValue <= dayValue) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                SettingUtil.setIsNightMode(true)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                SettingUtil.setIsNightMode(false)
            }
        } else {
            //获取当前主题
            if (SettingUtil.getIsNightMode()) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }
    }

    /**
     * 初始化Logger配置
     */
    private fun initConfig() {
        val formatStrategy = PrettyFormatStrategy.newBuilder()
                .showThreadInfo(false)  //隐藏线程信息
                .methodCount(0)         //决定打印多少行（每一行代表一个方法）默认2
                .methodOffset(7)        //内部方法调用向上偏移的行数 默认是0
                .tag("yx")
                .build()
        Logger.addLogAdapter(object : AndroidLogAdapter(formatStrategy) {
            override fun isLoggable(priority: Int, tag: String?): Boolean {
                return BuildConfig.DEBUG
            }
        })
    }


    private fun setUpLeakCanary(): RefWatcher =
            if (LeakCanary.isInAnalyzerProcess(this)) RefWatcher.DISABLED
            else LeakCanary.install(this)


    private val mActivityLifecycleCallbacks = object : Application.ActivityLifecycleCallbacks {
        override fun onActivityPaused(activity: Activity?) {

        }

        override fun onActivityResumed(activity: Activity?) {
        }

        override fun onActivityStarted(activity: Activity?) {
            Log.d(TAG, "onStart: " + activity?.componentName?.className)
        }

        override fun onActivityDestroyed(activity: Activity?) {
            Log.d(TAG, "onDestroy: " + activity?.componentName?.className)
        }

        override fun onActivitySaveInstanceState(activity: Activity?, outState: Bundle?) {
        }

        override fun onActivityStopped(activity: Activity?) {
        }

        override fun onActivityCreated(activity: Activity?, savedInstanceState: Bundle?) {
            Log.d(TAG, "onCreated: " + activity?.componentName?.className)
        }
    }
}