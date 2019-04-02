package youxuntianxiaapp.huimee.com.wanandroid.ui.activity

import android.content.Intent
import android.view.View
import kotlinx.android.synthetic.main.activity_register.*
import org.greenrobot.eventbus.EventBus
import youxuntianxiaapp.huimee.com.wanandroid.R
import youxuntianxiaapp.huimee.com.wanandroid.base.BaseMvpActivity
import youxuntianxiaapp.huimee.com.wanandroid.constant.Constant
import youxuntianxiaapp.huimee.com.wanandroid.event.LoginEvent
import youxuntianxiaapp.huimee.com.wanandroid.ext.loge
import youxuntianxiaapp.huimee.com.wanandroid.ext.showToast
import youxuntianxiaapp.huimee.com.wanandroid.mvp.contract.RegisterContract
import youxuntianxiaapp.huimee.com.wanandroid.mvp.model.bean.LoginData
import youxuntianxiaapp.huimee.com.wanandroid.mvp.presenter.RegisterPresenter
import youxuntianxiaapp.huimee.com.wanandroid.utils.Preference

class RegisterActivity : BaseMvpActivity<RegisterContract.View, RegisterContract.Presenter>(), RegisterContract.View, View.OnClickListener {


    private var user: String by Preference(Constant.USERNAME_KEY, "")

    private var pwd: String by Preference(Constant.PASSWORD_KEY, "")


    override fun attachLayoutRes() = R.layout.activity_register

    override fun createPresenter() = RegisterPresenter()


    override fun initData() {

    }

    override fun initView() {
        super.initView()
        btn_register.setOnClickListener(this)
        tv_sign_in.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_register -> register()
            R.id.tv_sign_in -> {
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            }
        }
    }

    private fun register() {
        if (validate()) {
            mPresenter?.registerWanAndroid(et_username.text.toString(),
                    et_password.text.toString(),
                    et_password2.text.toString())
        }
    }

    override fun useEventBus() = false

    override fun enableNetworkTip() = false

    override fun start() {

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
        loge(msg)
    }

    private fun validate(): Boolean {
        val username = et_username.text.toString()
        val password = et_password.text.toString()
        val password2 = et_password2.text.toString()
        when {
            username.isEmpty() -> {
                et_username.error = getString(R.string.username_not_empty)
                return false
            }
            password.isEmpty() -> {
                et_password.error = getString(R.string.password_not_empty)
                return false
            }
            password2.isEmpty() -> {
                et_password2.error = getString(R.string.confirm_password_not_empty)
                return false
            }
            password != password2 -> {
                et_password2.error = getString(R.string.password_cannot_match)
                return false
            }
            else -> return true
        }

    }
}
