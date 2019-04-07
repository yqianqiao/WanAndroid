package youxuntianxiaapp.huimee.com.wanandroid.mvp.contract

import io.reactivex.Observable
import youxuntianxiaapp.huimee.com.wanandroid.mvp.model.bean.Article
import youxuntianxiaapp.huimee.com.wanandroid.mvp.model.bean.ArticleResponseBody
import youxuntianxiaapp.huimee.com.wanandroid.mvp.model.bean.Banner
import youxuntianxiaapp.huimee.com.wanandroid.mvp.model.bean.HttpResult

/**
 * Created by yx on 2019/4/6
 */
interface HomeConteact {
    interface View : CommonContract.View {
        fun scrollToTop()

        fun setBanner(banner: List<Banner>)

        fun setArticles(articles: ArticleResponseBody)
    }

    interface Model : CommonContract.Model {
        fun requestBanner(): Observable<HttpResult<List<Banner>>>

        fun requestTopArticles(): Observable<HttpResult<MutableList<Article>>>

        fun requestArticles(num: Int): Observable<HttpResult<ArticleResponseBody>>
    }

    interface Presenter : CommonContract.Presenter<View> {
        fun requestBanner()

        fun requestHomeData()

        fun requestArticles(num: Int)
    }
}