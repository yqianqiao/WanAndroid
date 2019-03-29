package youxuntianxiaapp.huimee.com.wanandroid.mvp.presenter

import youxuntianxiaapp.huimee.com.wanandroid.base.BasePresenter
import youxuntianxiaapp.huimee.com.wanandroid.ext.loge
import youxuntianxiaapp.huimee.com.wanandroid.ext.ss
import youxuntianxiaapp.huimee.com.wanandroid.mvp.contract.MainContract
import youxuntianxiaapp.huimee.com.wanandroid.mvp.model.MainModel

class MainPresenter : BasePresenter<MainContract.Model, MainContract.View>(), MainContract.Presenter {

    override fun createModel(): MainContract.Model? = MainModel()


    override fun logout() {
        mModel?.logout()?.ss(mModel, mView) {
            mView?.showLogoutSuccess(success = true)
        }
    }

}