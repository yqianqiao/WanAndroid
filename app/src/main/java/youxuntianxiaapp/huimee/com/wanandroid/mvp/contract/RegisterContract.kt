package youxuntianxiaapp.huimee.com.wanandroid.mvp.contract

import io.reactivex.Observable
import youxuntianxiaapp.huimee.com.wanandroid.base.IModel
import youxuntianxiaapp.huimee.com.wanandroid.base.IPresenter
import youxuntianxiaapp.huimee.com.wanandroid.base.IView
import youxuntianxiaapp.huimee.com.wanandroid.mvp.model.bean.HttpResult
import youxuntianxiaapp.huimee.com.wanandroid.mvp.model.bean.LoginData

/**
 * Created by yx on 2019/4/1
 */
interface RegisterContract {
    interface View : IView {
        fun registerSuccess(data: LoginData)
        fun registerFail(msg: String)
    }

    interface Model : IModel {
        fun registerWanAndroid(username: String, password: String, repassword: String): Observable<HttpResult<LoginData>>
    }

    interface Presenter : IPresenter<View> {
        fun registerWanAndroid(username: String, password: String, repassword: String)
    }
}