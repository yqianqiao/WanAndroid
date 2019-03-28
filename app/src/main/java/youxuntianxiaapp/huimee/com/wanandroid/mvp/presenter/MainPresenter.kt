package youxuntianxiaapp.huimee.com.wanandroid.mvp.presenter

import youxuntianxiaapp.huimee.com.wanandroid.base.BasePresenter
import youxuntianxiaapp.huimee.com.wanandroid.base.IPresenter
import youxuntianxiaapp.huimee.com.wanandroid.mvp.contract.MainContract

class MainPresenter :BasePresenter<MainContract.Model,MainContract.View>(),MainContract.Presenter {

    override fun createModel(): MainContract.Model? {
        return
    }

    override fun logout() {

    }

}