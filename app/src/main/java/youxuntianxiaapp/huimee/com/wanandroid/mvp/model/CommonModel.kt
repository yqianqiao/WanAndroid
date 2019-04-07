package youxuntianxiaapp.huimee.com.wanandroid.mvp.model

import youxuntianxiaapp.huimee.com.wanandroid.base.BaseModel
import youxuntianxiaapp.huimee.com.wanandroid.http.RetrofitHelper
import youxuntianxiaapp.huimee.com.wanandroid.mvp.contract.CommonContract

/**
 * Created by yx on 2019/4/6
 */
open class CommonModel : BaseModel(), CommonContract.Model {
    override fun addCollectArticle(id: Int) =
            RetrofitHelper.service.addCollectArticle(id)

    override fun cancelCollectArticle(id: Int) =
            RetrofitHelper.service.cancelCollectArticle(id)
}