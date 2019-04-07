package youxuntianxiaapp.huimee.com.wanandroid.mvp.model

import youxuntianxiaapp.huimee.com.wanandroid.http.RetrofitHelper
import youxuntianxiaapp.huimee.com.wanandroid.mvp.contract.HomeConteact

/**
 * Created by yx on 2019/4/6
 */
class HomeModel : CommonModel(), HomeConteact.Model {
    override fun requestBanner() =
            RetrofitHelper.service.getBanners()

    override fun requestTopArticles() =
            RetrofitHelper.service.getTopArticles()

    override fun requestArticles(num: Int) =
            RetrofitHelper.service.getArticles(num)
}