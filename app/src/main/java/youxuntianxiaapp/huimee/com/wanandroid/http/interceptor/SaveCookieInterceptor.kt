package youxuntianxiaapp.huimee.com.wanandroid.http.interceptor

import okhttp3.Interceptor
import okhttp3.Response
import youxuntianxiaapp.huimee.com.wanandroid.constant.HttpConstant

/**
 * Created by yx on 2019/3/28
 */
class SaveCookieInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val response = chain.proceed(request)
        val requestUrl = request.url().toString()
        val domain = request.url().host()

        val headers = response.headers(HttpConstant.SET_COOKIE_KEY)
        if ((requestUrl.contains(HttpConstant.SAVE_USER_LOGIN_KEY)
                        || requestUrl.contains(HttpConstant.SAVE_USER_REGISTER_KEY))
                && !headers.isEmpty()) {
            val cookie = HttpConstant.encodeCookie(headers)
            HttpConstant.saveCookie(requestUrl, domain, cookie)
        }
        return response
    }
}