package youxuntianxiaapp.huimee.com.wanandroid.mvp.presenter

import youxuntianxiaapp.huimee.com.wanandroid.base.BasePresenter
import youxuntianxiaapp.huimee.com.wanandroid.ext.Jsonloge
import youxuntianxiaapp.huimee.com.wanandroid.ext.loge
import youxuntianxiaapp.huimee.com.wanandroid.ext.ss
import youxuntianxiaapp.huimee.com.wanandroid.mvp.contract.RegisterContract
import youxuntianxiaapp.huimee.com.wanandroid.mvp.model.RegisterModel

/**
 * Created by yx on 2019/4/1
 */
class RegisterPresenter : BasePresenter<RegisterContract.Model, RegisterContract.View>(), RegisterContract.Presenter {
    override fun createModel() = RegisterModel()

    override fun registerWanAndroid(username: String, password: String, repassword: String) {
        mModel?.registerWanAndroid(username, password, repassword)
                ?.ss(mModel, mView) {
                    loge(it.toString())
                }
    }
}