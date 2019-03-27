package youxuntianxiaapp.huimee.com.wanandroid.base

interface IPresenter<in V : IView> {
    /**
     * 绑定view
     */
    fun attachView(mView: V)

    /**
     * 解绑 View
     */
    fun detachView()

}