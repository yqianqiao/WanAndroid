package youxuntianxiaapp.huimee.com.wanandroid.ui.activity

import android.content.Intent
import android.view.animation.Animation
import android.view.animation.ScaleAnimation
import kotlinx.android.synthetic.main.activity_splash.*
import youxuntianxiaapp.huimee.com.wanandroid.MainActivity
import youxuntianxiaapp.huimee.com.wanandroid.R
import youxuntianxiaapp.huimee.com.wanandroid.base.BaseActivity
import youxuntianxiaapp.huimee.com.wanandroid.ext.loge

class SplashActivity : BaseActivity() {
    private var scaleAnimation: ScaleAnimation? = null

    override fun useEventBus() = false

    override fun enableNetworkTip() = false

    override fun attachLayoutRes() = R.layout.activity_splash

    override fun initData() {
    }

    override fun initView() {
        scaleAnimation = ScaleAnimation(1.0f, 1.2f, 1.0f, 1.2f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f)
        scaleAnimation?.run {
            duration = 2000
            fillAfter = true
            fillBefore = false
            setAnimationListener(object : Animation.AnimationListener {
                override fun onAnimationRepeat(animation: Animation?) {
                    loge("onAnimationRepeat")
                }

                override fun onAnimationEnd(animation: Animation?) {
                    jumpToMain()
                }

                override fun onAnimationStart(animation: Animation?) {
                    loge("onAnimationStart")
                }
            })
        }
        iv_splash.startAnimation(scaleAnimation)
    }

    override fun start() {
    }

    fun jumpToMain() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
    }

}
