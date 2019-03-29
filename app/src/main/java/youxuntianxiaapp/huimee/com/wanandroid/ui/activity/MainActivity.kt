package youxuntianxiaapp.huimee.com.wanandroid.ui.activity

import youxuntianxiaapp.huimee.com.wanandroid.R
import youxuntianxiaapp.huimee.com.wanandroid.base.BaseMvpActivity
import youxuntianxiaapp.huimee.com.wanandroid.mvp.contract.MainContract
import youxuntianxiaapp.huimee.com.wanandroid.mvp.presenter.MainPresenter

class MainActivity : BaseMvpActivity<MainContract.View, MainContract.Presenter>() {
    override fun attachLayoutRes() = R.layout.activity_main

    override fun initData() {
    }

    override fun start() {
        mPresenter?.logout()
    }

    override fun createPresenter(): MainContract.Presenter {
        return MainPresenter()
    }


}
