package youxuntianxiaapp.huimee.com.wanandroid.http.exception

import com.google.gson.JsonParseException
import com.squareup.moshi.Json
import org.json.JSONException
import retrofit2.HttpException
import youxuntianxiaapp.huimee.com.wanandroid.ext.loge
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.text.ParseException

/**
 *    author : YX
 *    date   : 2019/3/29  17:03
 */
class ExceptionHandle {
    companion object {
        private const val TAG = "ExceptionHandle"
        var errorCode = HttpStatus.UNKNOWN_ERROR
        var errorMsg = "请求失败，请稍后重试"

        fun handleException(e: Throwable): String {
            e.printStackTrace()
            if (e is SocketTimeoutException
                    || e is ConnectException
                    || e is HttpException) {
                //网络异常
                loge(TAG, "网络连接异常: $e.message")
                errorMsg = "网络连接异常"
                errorCode = HttpStatus.NETWORK_ERROR
            } else if (e is JsonParseException
                    || e is JSONException
                    || e is ParseException) {
                //解析异常
                loge(TAG, "数据解析异常: $e.message")
                errorMsg = "数据解析异常"
                errorCode = HttpStatus.SERVER_ERROR
            } else if (e is ApiException) {
                errorMsg = e.message.toString()
                errorCode = HttpStatus.SERVER_ERROR
            } else if (e is IllegalArgumentException) {
                errorMsg = "参数错误"
                errorCode = HttpStatus.SERVER_ERROR
            } else {
                errorMsg = "未知错误，可能抛锚了吧~"
                errorCode = HttpStatus.UNKNOWN_ERROR
            }
            return errorMsg

        }
    }

}