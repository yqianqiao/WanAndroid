package youxuntianxiaapp.huimee.com.wanandroid.rx

import io.reactivex.observers.ResourceObserver
import youxuntianxiaapp.huimee.com.wanandroid.app.App
import youxuntianxiaapp.huimee.com.wanandroid.base.IView
import youxuntianxiaapp.huimee.com.wanandroid.http.exception.ExceptionHandle
import youxuntianxiaapp.huimee.com.wanandroid.http.exception.HttpStatus
import youxuntianxiaapp.huimee.com.wanandroid.mvp.model.bean.BaseBean
import youxuntianxiaapp.huimee.com.wanandroid.utils.NetWorkUtil

/**
 *    author : YX
 *    date   : 2019/3/2916:53
 *    desc   :
 */
abstract class BaseObserver<T : BaseBean>(view: IView? = null) : ResourceObserver<T>() {
    private var mView = view

    abstract fun onSuccess(t: T)

    abstract fun onError(error: String)

    override fun onComplete() {
        mView?.hideLoading()
    }

    override fun onStart() {
        super.onStart()
        mView?.showLoading()
        if (!NetWorkUtil.isNetworkConnected(App.context)) {
            mView?.showDefaultMsg("网络连接不可用,请检查网络设置!")
            onComplete()
        }
    }

    override fun onNext(t: T) {
        when {
            t.errorCode == HttpStatus.SUCCESS -> onSuccess(t)
            t.errorCode == HttpStatus.TOKEN_INVAILD -> {
                // Token 过期，重新登录
            }
            else -> mView?.showDefaultMsg(t.errorMsg)
        }
    }

    override fun onError(e: Throwable) {
        mView?.hideLoading()
        onError(ExceptionHandle.handleException(e))
    }
}