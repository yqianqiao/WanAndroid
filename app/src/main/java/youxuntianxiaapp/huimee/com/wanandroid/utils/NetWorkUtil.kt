package youxuntianxiaapp.huimee.com.wanandroid.utils

import android.content.Context
import android.net.ConnectivityManager

object NetWorkUtil {


    /**
     * 判断是否有网络连接
     */
    fun isNetworkConnected(context: Context): Boolean {
        val manager = context.applicationContext
                .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val info = manager.activeNetworkInfo
        return !(null == info || !info.isConnected)
    }
}