package youxuntianxiaapp.huimee.com.wanandroid.base

interface IView {
    /**
     * 显示加载
     */
    fun showLoading(msg: String = "请稍等")

    /**
     * 隐藏加载
     */
    fun hideLoading()

    /**
     * 使用默认的样式显示
     */
    fun showDefaultMsg(msg: String)

    /**
     * 显示信息
     */
    fun showMsg(msg: String)

    /**
     * 显示错误信息
     */
    fun showError(errorMsg: String)
}