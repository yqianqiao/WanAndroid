package youxuntianxiaapp.huimee.com.wanandroid.base

@Suppress("UNCHECKED_CAST")
abstract class BaseMvpActivity<in V : IView, P : IPresenter<V>> : BaseActivity() {

    protected var mPresenter: P? = null

    protected abstract fun createPresenter(): P

    override fun initView() {
        mPresenter = createPresenter()
        mPresenter?.attachView(this as V)
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter?.detachView()
        mPresenter = null
    }



}