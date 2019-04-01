package youxuntianxiaapp.huimee.com.wanandroid.mvp.model

import io.reactivex.Observable
import youxuntianxiaapp.huimee.com.wanandroid.base.BaseModel
import youxuntianxiaapp.huimee.com.wanandroid.http.RetrofitHelper
import youxuntianxiaapp.huimee.com.wanandroid.mvp.contract.RegisterContract
import youxuntianxiaapp.huimee.com.wanandroid.mvp.model.bean.HttpResult
import youxuntianxiaapp.huimee.com.wanandroid.mvp.model.bean.LoginData

/**
 * Created by yx on 2019/4/1
 */
class RegisterModel : BaseModel(), RegisterContract.Model {
    override fun registerWanAndroid(username: String, password: String, repassword: String): Observable<HttpResult<LoginData>> {
        return RetrofitHelper.service.registerWanAndroid(username, password, repassword)
    }
}