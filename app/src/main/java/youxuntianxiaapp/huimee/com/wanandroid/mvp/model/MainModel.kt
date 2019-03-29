package youxuntianxiaapp.huimee.com.wanandroid.mvp.model

import io.reactivex.Observable
import youxuntianxiaapp.huimee.com.wanandroid.base.BaseModel
import youxuntianxiaapp.huimee.com.wanandroid.http.RetrofitHelper
import youxuntianxiaapp.huimee.com.wanandroid.mvp.contract.MainContract
import youxuntianxiaapp.huimee.com.wanandroid.mvp.model.bean.BaseBean
import youxuntianxiaapp.huimee.com.wanandroid.mvp.model.bean.HttpResult

/**
 * Created by yx on 2019/3/28
 */
class MainModel : BaseModel(), MainContract.Model {

    override fun logout(): Observable<HttpResult<BaseBean>> = RetrofitHelper.service.logout()

}