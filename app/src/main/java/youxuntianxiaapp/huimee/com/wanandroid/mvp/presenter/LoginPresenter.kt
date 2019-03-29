package youxuntianxiaapp.huimee.com.wanandroid.mvp.presenter

import youxuntianxiaapp.huimee.com.wanandroid.base.BasePresenter
import youxuntianxiaapp.huimee.com.wanandroid.ext.ss
import youxuntianxiaapp.huimee.com.wanandroid.mvp.contract.LoginContract
import youxuntianxiaapp.huimee.com.wanandroid.mvp.model.LoginModel
import youxuntianxiaapp.huimee.com.wanandroid.ui.activity.LoginActivity

/**
 * Created by yx on 2019/3/31
 */
class LoginPresenter : BasePresenter<LoginContract.Model, LoginContract.View>(), LoginContract.Presenter {

    override fun createModel(): LoginContract.Model? {
        return LoginModel()
    }

    override fun loginWanAndroid(username: String, password: String) {
        mModel?.loginWanAndroid(username, password)?.ss(mModel, mView) {
            mView?.loginSuccess(it.data)
        }
    }
}