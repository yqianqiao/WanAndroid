package youxuntianxiaapp.huimee.com.wanandroid.http.interceptor

import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.Response
import youxuntianxiaapp.huimee.com.wanandroid.app.App
import youxuntianxiaapp.huimee.com.wanandroid.utils.NetWorkUtil

/**
 * Created by yx on 2019/3/28
 */
class CacheInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        if (!NetWorkUtil.isNetworkAvailable(App.context)) {
            request = request.newBuilder()
                    .cacheControl(CacheControl.FORCE_CACHE)
                    .build()
        }
        val response = chain.proceed(request)
        if (NetWorkUtil.isNetworkAvailable(App.context)) {
            val maxAge = 60 * 3
            // 有网络时 设置缓存超时时间0个小时 ,意思就是不读取缓存数据,只对get有用,post没有缓冲
            //TODO 还未写完
        }
        return response
    }
}