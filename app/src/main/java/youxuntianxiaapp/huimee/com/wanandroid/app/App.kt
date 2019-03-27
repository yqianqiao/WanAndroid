package youxuntianxiaapp.huimee.com.wanandroid.app

import android.app.Application
import android.content.Context
import android.support.multidex.MultiDexApplication
import com.squareup.leakcanary.LeakCanary
import com.squareup.leakcanary.RefWatcher
import kotlin.properties.Delegates

class App : MultiDexApplication() {
    private var refWatcher: RefWatcher? = null

    companion object {
        private const val TAG = "App"
        var context: Context by Delegates.notNull()
            private set
        lateinit var instance: Application
        fun getRefWatcher(): RefWatcher? {
            val app = context.applicationContext as App
            return app.refWatcher
        }

    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        context = applicationContext
        refWatcher = setUpLeakCanary()

    }


    private fun setUpLeakCanary(): RefWatcher =
            if (LeakCanary.isInAnalyzerProcess(this)) RefWatcher.DISABLED
            else LeakCanary.install(this)
}