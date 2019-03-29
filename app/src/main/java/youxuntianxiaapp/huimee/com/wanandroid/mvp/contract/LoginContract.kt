package youxuntianxiaapp.huimee.com.wanandroid.mvp.contract

import io.reactivex.Observable
import youxuntianxiaapp.huimee.com.wanandroid.base.IModel
import youxuntianxiaapp.huimee.com.wanandroid.base.IPresenter
import youxuntianxiaapp.huimee.com.wanandroid.base.IView
import youxuntianxiaapp.huimee.com.wanandroid.mvp.model.bean.HttpResult
import youxuntianxiaapp.huimee.com.wanandroid.mvp.model.bean.LoginData

/**
 * Created by yx on 2019/3/31
 */
interface LoginContract {
    interface View : IView {
        fun loginSuccess(data: LoginData)
        fun loginFail(error:String)
    }

    interface Model : IModel {
        fun loginWanAndroid(username: String, password: String): Observable<HttpResult<LoginData>>
    }

    interface Presenter : IPresenter<View> {
        fun loginWanAndroid(username: String, password: String)
    }
}