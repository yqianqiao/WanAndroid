package youxuntianxiaapp.huimee.com.wanandroid.mvp.contract

import io.reactivex.Observable
import youxuntianxiaapp.huimee.com.wanandroid.base.IModel
import youxuntianxiaapp.huimee.com.wanandroid.base.IPresenter
import youxuntianxiaapp.huimee.com.wanandroid.base.IView
import youxuntianxiaapp.huimee.com.wanandroid.mvp.model.bean.BaseBean
import youxuntianxiaapp.huimee.com.wanandroid.mvp.model.bean.HttpResult

interface MainContract {
    interface View : IView {
        fun showLogoutSuccess(success: Boolean)
    }

    interface Presenter : IPresenter<View> {
        fun logout()
    }

    interface Model : IModel {
        fun logout(): Observable<HttpResult<BaseBean>>
    }
}