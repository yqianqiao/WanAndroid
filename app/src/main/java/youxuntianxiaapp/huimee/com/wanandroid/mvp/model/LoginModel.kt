package youxuntianxiaapp.huimee.com.wanandroid.mvp.model

import io.reactivex.Observable
import youxuntianxiaapp.huimee.com.wanandroid.base.BaseModel
import youxuntianxiaapp.huimee.com.wanandroid.http.RetrofitHelper
import youxuntianxiaapp.huimee.com.wanandroid.mvp.contract.LoginContract
import youxuntianxiaapp.huimee.com.wanandroid.mvp.model.bean.HttpResult
import youxuntianxiaapp.huimee.com.wanandroid.mvp.model.bean.LoginData

/**
 * Created by yx on 2019/3/31
 */
class LoginModel : BaseModel(), LoginContract.Model {

    override fun loginWanAndroid(username: String, password: String): Observable<HttpResult<LoginData>> {
        return RetrofitHelper.service.loginWanAndroid(username, password)
    }
}