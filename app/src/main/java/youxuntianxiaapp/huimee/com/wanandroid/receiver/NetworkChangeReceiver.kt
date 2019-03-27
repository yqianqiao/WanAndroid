package youxuntianxiaapp.huimee.com.wanandroid.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import org.greenrobot.eventbus.EventBus
import youxuntianxiaapp.huimee.com.wanandroid.constant.Constant
import youxuntianxiaapp.huimee.com.wanandroid.event.NetworkChangeEvent
import youxuntianxiaapp.huimee.com.wanandroid.utils.NetWorkUtil
import youxuntianxiaapp.huimee.com.wanandroid.utils.Preference

class NetworkChangeReceiver : BroadcastReceiver() {
    /**
     * 缓存上一次网络状态
     */
    private var hasNetwork: Boolean by Preference(Constant.HAS_NETWORK_KEY, true)

    override fun onReceive(context: Context, intent: Intent?) {
        val isConnected = NetWorkUtil.isNetworkConnected(context)
        if (isConnected) {
            if (isConnected != hasNetwork) {
                EventBus.getDefault().post(NetworkChangeEvent(isConnected))
            }
        } else {
            EventBus.getDefault().post(NetworkChangeEvent(isConnected))
        }
    }
}