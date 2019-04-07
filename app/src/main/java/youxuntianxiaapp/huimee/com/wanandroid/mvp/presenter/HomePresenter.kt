package youxuntianxiaapp.huimee.com.wanandroid.mvp.presenter

import io.reactivex.Observable
import io.reactivex.functions.BiFunction
import youxuntianxiaapp.huimee.com.wanandroid.ext.ss
import youxuntianxiaapp.huimee.com.wanandroid.mvp.contract.HomeConteact
import youxuntianxiaapp.huimee.com.wanandroid.mvp.model.HomeModel
import youxuntianxiaapp.huimee.com.wanandroid.mvp.model.bean.Article
import youxuntianxiaapp.huimee.com.wanandroid.mvp.model.bean.ArticleResponseBody
import youxuntianxiaapp.huimee.com.wanandroid.mvp.model.bean.HttpResult
import youxuntianxiaapp.huimee.com.wanandroid.utils.SettingUtil

/**
 * Created by yx on 2019/4/6
 */
class HomePresenter : CommonPresenter<HomeConteact.Model, HomeConteact.View>(), HomeConteact.Presenter {

    override fun createModel() = HomeModel()

    override fun requestBanner() {
        mModel?.requestBanner()?.ss(mModel, mView) {
            mView?.setBanner(it.data)
        }
    }

    override fun requestHomeData() {
        requestBanner()
        val observable = if (SettingUtil.getIsShowTopArticle()) {
            mModel?.requestArticles(0)
        } else {
            Observable.zip(mModel?.requestTopArticles(), mModel?.requestArticles(0),
                    BiFunction<HttpResult<MutableList<Article>>, HttpResult<ArticleResponseBody>
                            , HttpResult<ArticleResponseBody>> { t1, t2 ->
                        t1.data.forEach {
                            //自顶数据没有标识，手动加一个
                            it.top = "1"
                        }
                        t2.data.datas.addAll(0, t1.data)
                        t2
                    })
        }
        observable?.ss(mModel, mView, false) {
            mView?.setArticles(it.data)
        }
    }

    override fun requestArticles(num: Int) {
        mModel?.requestArticles(num)?.ss(mModel, mView, num == 0) {
            mView?.setArticles(it.data)
        }
    }
}