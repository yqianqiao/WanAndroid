package youxuntianxiaapp.huimee.com.wanandroid

import youxuntianxiaapp.huimee.com.wanandroid.base.BaseMvpActivity
import youxuntianxiaapp.huimee.com.wanandroid.mvp.contract.MainContract

class MainActivity : BaseMvpActivity<MainContract.View, MainContract.Presenter>() {
    override fun attachLayoutRes() = R.layout.activity_main

    override fun initData() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun start() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun createPresenter(): MainContract.Presenter {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}
