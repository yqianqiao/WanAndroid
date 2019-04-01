package youxuntianxiaapp.huimee.com.wanandroid.ui.activity

import org.greenrobot.eventbus.EventBus
import youxuntianxiaapp.huimee.com.wanandroid.R
import youxuntianxiaapp.huimee.com.wanandroid.base.BaseMvpActivity
import youxuntianxiaapp.huimee.com.wanandroid.constant.Constant
import youxuntianxiaapp.huimee.com.wanandroid.event.LoginEvent
import youxuntianxiaapp.huimee.com.wanandroid.ext.showToast
import youxuntianxiaapp.huimee.com.wanandroid.mvp.contract.RegisterContract
import youxuntianxiaapp.huimee.com.wanandroid.mvp.model.bean.LoginData
import youxuntianxiaapp.huimee.com.wanandroid.mvp.presenter.RegisterPresenter
import youxuntianxiaapp.huimee.com.wanandroid.utils.Preference

class RegisterActivity : BaseMvpActivity<RegisterContract.View, RegisterContract.Presenter>(), RegisterContract.View {


    private var user: String by Preference(Constant.USERNAME_KEY, "")

    private var pwd: String by Preference(Constant.PASSWORD_KEY, "")

    override fun attachLayoutRes() = R.layout.activity_register

    override fun createPresenter() = RegisterPresenter()

    override fun initData() {

    }

    override fun useEventBus()=false

    override fun start() {
        mPresenter?.registerWanAndroid("aaaxcgb", "1234567", "1234567")
    }

    override fun registerSuccess(data: LoginData) {
        showToast(getString(R.string.register_success))
        isLogin = true
        user = data.username
        pwd = data.password

        EventBus.getDefault().post(LoginEvent(true))
        finish()
    }

    override fun registerFail(msg: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
