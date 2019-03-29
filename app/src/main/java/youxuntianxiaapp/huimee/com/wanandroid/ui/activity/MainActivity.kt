package youxuntianxiaapp.huimee.com.wanandroid.ui.activity

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v7.app.ActionBarDrawerToggle
import android.util.SparseArray
import android.view.View
import android.widget.TextView
import com.afollestad.materialdialogs.util.DialogUtils
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.nav_header_main.*
import kotlinx.android.synthetic.main.toolbar.*
import org.greenrobot.eventbus.EventBus
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import youxuntianxiaapp.huimee.com.wanandroid.R
import youxuntianxiaapp.huimee.com.wanandroid.base.BaseMvpActivity
import youxuntianxiaapp.huimee.com.wanandroid.constant.Constant
import youxuntianxiaapp.huimee.com.wanandroid.event.LoginEvent
import youxuntianxiaapp.huimee.com.wanandroid.ext.showToast
import youxuntianxiaapp.huimee.com.wanandroid.mvp.contract.MainContract
import youxuntianxiaapp.huimee.com.wanandroid.mvp.presenter.MainPresenter
import youxuntianxiaapp.huimee.com.wanandroid.ui.fragment.*
import youxuntianxiaapp.huimee.com.wanandroid.utils.DialogUtil
import youxuntianxiaapp.huimee.com.wanandroid.utils.Preference

class MainActivity : BaseMvpActivity<MainContract.View, MainContract.Presenter>(), MainContract.View {

    private var mIndex = FRAGMENT_HOME

    private val username: String by Preference(Constant.USERNAME_KEY, "")

    private var nav_username: TextView? = null

    private var lastIndex: Int? = null

    private var mHomeFragment: HomeFragment? = null
    private var mKnowledgeTreeFragment: KnowledgeTreeFragment? = null
    private var mNavigationFragment: NavigationFragment? = null
    private var mProjectFragment: ProjectFragment? = null
    private var mWeChatFragment: WeChatFragment? = null

    /**
     * 退出登录 Dialog
     */
    private val mDialog by lazy {
        DialogUtil.getWaitDialog(this@MainActivity, resources.getString(R.string.logout_ing))
    }


    private val fragmentMap = SparseArray<Fragment>(5)

    override fun showLogoutSuccess(success: Boolean) {
        if (success) {
            doAsync {
                Preference.clearPreference()
                uiThread {
                    mDialog.visibility = View.GONE
                    showToast(getString(R.string.logout_success))
                    isLogin = false
                    EventBus.getDefault().post(LoginEvent(isLogin))
                }
            }
        }

    }

    override fun attachLayoutRes() = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        if (savedInstanceState != null) {
            mIndex = savedInstanceState.getInt(BOTTOM_INDEX)
        }
        super.onCreate(savedInstanceState)
    }

    override fun initData() {
    }

    override fun initView() {
        super.initView()
        toolbar.run {
            title = getString(R.string.app_name)
            setSupportActionBar(this)
        }
        showFragment(FRAGMENT_HOME, getString(R.string.app_name))
        bottom_navigation.run {
            labelVisibilityMode = 1
            setOnNavigationItemSelectedListener {
                return@setOnNavigationItemSelectedListener when (it.itemId) {
                    R.id.action_home -> showFragment(FRAGMENT_HOME, getString(R.string.app_name))
                    R.id.action_knowledge_system -> showFragment(FRAGMENT_KNOWLEDGE, getString(R.string.knowledge_system))
                    R.id.action_wechat -> showFragment(FRAGMENT_WECHAT, getString(R.string.wechat))
                    R.id.action_navigation -> showFragment(FRAGMENT_NAVIGATION, getString(R.string.navigation))
                    R.id.action_project -> showFragment(FRAGMENT_PROJECT, getString(R.string.project))
                    else -> false
                }
            }
        }

        initDrawerLayout()

        nav_view.run {
            setNavigationItemSelectedListener(onDrawerNavigationItemSelectedListener)
            nav_username = getHeaderView(0).findViewById(R.id.tv_username)
        }

        nav_username?.run {
            text = if (!isLogin) getString(R.string.login) else username
            setOnClickListener {
                if (!isLogin) {
                    startActivity(Intent(this@MainActivity, LoginActivity::class.java))
                }
            }
        }

        floating_action_btn.setOnClickListener {

        }
    }

    private fun initDrawerLayout() {
        drawer_layout.run {
            val toggle = ActionBarDrawerToggle(
                    this@MainActivity,
                    this,
                    toolbar,
                    R.string.navigation_drawer_open,
                    R.string.navigation_drawer_close)
            addDrawerListener(toggle)
            toggle.syncState()
        }
    }

    private fun showFragment(index: Int, title: String): Boolean {
        val transaction = supportFragmentManager.beginTransaction()
        toolbar.title = title

        lastIndex?.let {
            transaction.hide(fragmentMap[lastIndex!!])
        }
        if (fragmentMap[index] == null) {
            when (index) {
                FRAGMENT_HOME -> fragmentMap.put(FRAGMENT_HOME, HomeFragment.getInstance())
                FRAGMENT_KNOWLEDGE -> fragmentMap.put(FRAGMENT_KNOWLEDGE, KnowledgeTreeFragment.getInstance())
                FRAGMENT_WECHAT -> fragmentMap.put(FRAGMENT_WECHAT, WeChatFragment.getInstance())
                FRAGMENT_NAVIGATION -> fragmentMap.put(FRAGMENT_NAVIGATION, NavigationFragment.getInstance())
                FRAGMENT_PROJECT -> fragmentMap.put(FRAGMENT_PROJECT, ProjectFragment.getInstance())
            }
            transaction.add(R.id.container, fragmentMap[index])
        }
        transaction.show(fragmentMap[index])
        transaction.commit()
        lastIndex = index
        return true
    }

    override fun start() {

    }

    override fun createPresenter(): MainContract.Presenter {
        return MainPresenter()
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState?.putInt(BOTTOM_INDEX, mIndex)
    }

    private val onDrawerNavigationItemSelectedListener =
            NavigationView.OnNavigationItemSelectedListener {
                when (it.itemId) {
                    R.id.nav_collect -> {
                        if (isLogin) {

                        } else {
                            showToast(getString(R.string.login_tint))
                        }
                    }
//                    R.id.nav_setting ->

//                    R.id.nav_about_us->
                    R.id.nav_logout -> {
                        DialogUtil.getConfirmDialog(this, resources.getString(R.string.confirm_logout),
                                DialogInterface.OnClickListener { _, _ ->
                                    mDialog.visibility = View.VISIBLE
                                    mPresenter?.logout()
                                }).show()
                    }
                }
                true
            }

    companion object {
        private const val BOTTOM_INDEX = "bottom_index"

        private const val FRAGMENT_HOME = 1
        private const val FRAGMENT_KNOWLEDGE = 2
        private const val FRAGMENT_NAVIGATION = 3
        private const val FRAGMENT_PROJECT = 4
        private const val FRAGMENT_WECHAT = 5
    }


}
