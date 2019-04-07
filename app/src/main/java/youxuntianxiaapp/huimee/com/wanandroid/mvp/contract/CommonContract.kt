package youxuntianxiaapp.huimee.com.wanandroid.mvp.contract

import io.reactivex.Observable
import youxuntianxiaapp.huimee.com.wanandroid.base.IModel
import youxuntianxiaapp.huimee.com.wanandroid.base.IPresenter
import youxuntianxiaapp.huimee.com.wanandroid.base.IView
import youxuntianxiaapp.huimee.com.wanandroid.mvp.model.bean.BaseBean
import youxuntianxiaapp.huimee.com.wanandroid.mvp.model.bean.HttpResult

/**
 * Created by yx on 2019/4/6
 */
interface CommonContract {
    interface View : IView {
        fun showCollectSuccess()

        fun showCancelCollectSuccess()
    }

    interface Model : IModel {

        fun addCollectArticle(id: Int): Observable<HttpResult<BaseBean>>

        fun cancelCollectArticle(id: Int): Observable<HttpResult<BaseBean>>

    }

    interface Presenter<in V : View> : IPresenter<V> {

        fun addCollectArticle(id: Int)

        fun cancelCollectArticle(id: Int)

    }
}