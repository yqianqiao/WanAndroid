package youxuntianxiaapp.huimee.com.wanandroid.base

import io.reactivex.disposables.Disposable

interface IModel {

    fun addDisposable(disposable: Disposable?)

    fun onDetach()
}