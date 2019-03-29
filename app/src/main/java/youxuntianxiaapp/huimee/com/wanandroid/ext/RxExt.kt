package youxuntianxiaapp.huimee.com.wanandroid.ext

import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import youxuntianxiaapp.huimee.com.wanandroid.R
import youxuntianxiaapp.huimee.com.wanandroid.app.App
import youxuntianxiaapp.huimee.com.wanandroid.base.IModel
import youxuntianxiaapp.huimee.com.wanandroid.base.IView
import youxuntianxiaapp.huimee.com.wanandroid.http.exception.ExceptionHandle
import youxuntianxiaapp.huimee.com.wanandroid.http.exception.HttpStatus
import youxuntianxiaapp.huimee.com.wanandroid.http.function.RetryWithDelay
import youxuntianxiaapp.huimee.com.wanandroid.mvp.model.bean.BaseBean
import youxuntianxiaapp.huimee.com.wanandroid.rx.SchedulerUtils
import youxuntianxiaapp.huimee.com.wanandroid.utils.NetWorkUtil

fun <T : BaseBean> Observable<T>.ss(
        model: IModel?,
        view: IView?,
        isShowLoading: Boolean = true,
        onSuccess: (T) -> Unit
) {
    this.compose(SchedulerUtils.ioToMain())
            .retryWhen(RetryWithDelay())
            .subscribe(object : Observer<T> {
                override fun onComplete() {
                    view?.hideLoading()
                }

                override fun onSubscribe(d: Disposable) {
                    if (isShowLoading) {
                        view?.showLoading()
                    }
                    model?.addDisposable(d)
                    if (!NetWorkUtil.isNetworkConnected(App.context)) {
                        view?.showDefaultMsg(App.instance.resources.getString(R.string.network_unavailable_tip))
                        onComplete()
                    }
                }

                override fun onNext(t: T) {
                    when {
                        t.errorCode == HttpStatus.SUCCESS -> onSuccess.invoke(t)
                        t.errorCode == HttpStatus.TOKEN_INVAILD -> {
                            // Token 过期，重新登录
                        }
                        else -> view?.showDefaultMsg(t.errorMsg)
                    }
                }

                override fun onError(e: Throwable) {
                    view?.hideLoading()
                    view?.showDefaultMsg(ExceptionHandle.handleException(e))
                }
            })
}

fun <T : BaseBean> Observable<T>.sss(
        view: IView?,
        isShowLoading: Boolean = true,
        onSuccess: (T) -> Unit
): Disposable {
    if (isShowLoading) {
        view?.showLoading()
    }
    return this.compose(SchedulerUtils.ioToMain())
            .retryWhen(RetryWithDelay())
            .subscribe({
                when {
                    it.errorCode == HttpStatus.SUCCESS -> onSuccess.invoke(it)
                    it.errorCode == HttpStatus.TOKEN_INVAILD -> {
                        // Token 过期，重新登录
                    }
                    else -> view?.showDefaultMsg(it.errorMsg)
                }
                view?.hideLoading()
            }, {
                view?.hideLoading()
                view?.showDefaultMsg(ExceptionHandle.handleException(it))
            })

}