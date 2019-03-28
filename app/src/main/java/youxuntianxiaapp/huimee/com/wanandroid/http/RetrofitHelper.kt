package youxuntianxiaapp.huimee.com.wanandroid.http

import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import youxuntianxiaapp.huimee.com.wanandroid.BuildConfig
import youxuntianxiaapp.huimee.com.wanandroid.api.ApiService
import youxuntianxiaapp.huimee.com.wanandroid.app.App
import youxuntianxiaapp.huimee.com.wanandroid.constant.Constant
import youxuntianxiaapp.huimee.com.wanandroid.constant.HttpConstant
import youxuntianxiaapp.huimee.com.wanandroid.http.interceptor.CacheInterceptor
import youxuntianxiaapp.huimee.com.wanandroid.http.interceptor.HeaderInterceptor
import youxuntianxiaapp.huimee.com.wanandroid.http.interceptor.SaveCookieInterceptor
import java.io.File

/**
 * Created by yx on 2019/3/28
 */
object RetrofitHelper {
    private var retrofit: Retrofit? = null
    val service: ApiService by lazy {
        getRetrofit()!!.create(ApiService::class.java)
    }

    private fun getRetrofit(): Retrofit? {
        if (retrofit == null) {
            synchronized(RetrofitHelper::class.java) {
                if (retrofit == null) {
                    retrofit = Retrofit.Builder()
                            .baseUrl(Constant.BASE_URL)
                            .client(getOkHttpClient())
                            .addConverterFactory(MoshiConverterFactory.create())
                            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                            .build()
                }

            }
        }
        return retrofit
    }

    private fun getOkHttpClient(): OkHttpClient {
        val builder = OkHttpClient().newBuilder()
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        if (BuildConfig.DEBUG) {
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        } else {
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.NONE
        }
        //设置请求的缓存的大小跟位置
        val cacheFile = File(App.context.cacheDir, "cache")
        val cache = Cache(cacheFile, HttpConstant.MAX_CACHE_SIZE)
        builder.run {
            addInterceptor(httpLoggingInterceptor)
            addInterceptor(HeaderInterceptor())
            addInterceptor(SaveCookieInterceptor())
            addInterceptor(CacheInterceptor())
            //TODO 未完成
        }
        return builder.build()
    }
}