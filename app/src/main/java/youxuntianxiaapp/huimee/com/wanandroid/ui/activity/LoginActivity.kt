package youxuntianxiaapp.huimee.com.wanandroid.ui.activity

import android.content.Intent
import android.view.View
import kotlinx.android.synthetic.main.activity_login.*
import org.greenrobot.eventbus.EventBus
import youxuntianxiaapp.huimee.com.wanandroid.R
import youxuntianxiaapp.huimee.com.wanandroid.base.BaseMvpActivity
import youxuntianxiaapp.huimee.com.wanandroid.constant.Constant
import youxuntianxiaapp.huimee.com.wanandroid.event.LoginEvent
import youxuntianxiaapp.huimee.com.wanandroid.ext.loge
import youxuntianxiaapp.huimee.com.wanandroid.ext.showToast
import youxuntianxiaapp.huimee.com.wanandroid.mvp.contract.LoginContract
import youxuntianxiaapp.huimee.com.wanandroid.mvp.model.bean.LoginData
import youxuntianxiaapp.huimee.com.wanandroid.mvp.presenter.LoginPresenter
import youxuntianxiaapp.huimee.com.wanandroid.utils.Preference

class LoginActivity : BaseMvpActivity<LoginContract.View, LoginContract.Presenter>(), LoginContract.View, View.OnClickListener {


    private var user: String by Preference(Constant.USERNAME_KEY, "aaa")

    private var pas: String by Preference(Constant.PASSWORD_KEY, "")

    private var token: String by Preference(Constant.TOKEN_KEY, "")

    private var username: String = ""
    private var password: String = ""

    override fun createPresenter() = LoginPresenter()

    override fun attachLayoutRes() = R.layout.activity_login

    override fun useEventBus() = false

    override fun enableNetworkTip() = false

    override fun initData() {
    }

    override fun start() {

    }

    override fun initView() {
        super.initView()
        et_username.setText(user)
        btn_login.setOnClickListener(this)
        tv_sign_up.setOnClickListener(this)


    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_login -> login()
            R.id.tv_sign_up -> {
                startActivity(Intent(this, RegisterActivity::class.java))
                finish()
            }

        }
    }

    private fun login() {

        if (validate()) {
            mPresenter?.loginWanAndroid(username, password)
        }
    }

    override fun loginSuccess(data: LoginData) {
        showToast(getString(R.string.login_success))
        isLogin = true
        loge(data.password)
        user =data.username
        pas = data.password
        token = data.token

        EventBus.getDefault().post(LoginEvent(true))
        finish()
    }

    override fun loginFail(error: String) {
        showToast(error)
    }

    private fun validate(): Boolean {
        username = et_username.text.toString()
        password = et_password.text.toString()
        if (username.isEmpty()) {
            et_username.error = getString(R.string.username_not_empty)
            return false
        } else if (password.isEmpty()) {
            et_password.error = getString(R.string.password_not_empty)
            return false
        }
        return true
    }

}
