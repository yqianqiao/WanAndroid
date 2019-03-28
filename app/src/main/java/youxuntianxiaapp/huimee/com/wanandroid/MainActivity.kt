package youxuntianxiaapp.huimee.com.wanandroid

import youxuntianxiaapp.huimee.com.wanandroid.base.BaseMvpActivity
import youxuntianxiaapp.huimee.com.wanandroid.mvp.contract.MainContract
import youxuntianxiaapp.huimee.com.wanandroid.mvp.model.MainModel
import youxuntianxiaapp.huimee.com.wanandroid.mvp.presenter.MainPresenter

class MainActivity : BaseMvpActivity<MainContract.View, MainContract.Presenter>() {
    override fun attachLayoutRes() = R.layout.activity_main

    override fun initData() {
    }

    override fun start() {
    }

    override fun createPresenter(): MainContract.Presenter {
        return MainModel()
    }


}
