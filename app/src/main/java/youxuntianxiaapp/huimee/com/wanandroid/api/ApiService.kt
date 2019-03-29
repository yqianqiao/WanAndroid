package youxuntianxiaapp.huimee.com.wanandroid.api

import io.reactivex.Observable
import retrofit2.http.GET
import youxuntianxiaapp.huimee.com.wanandroid.mvp.model.bean.Banner
import youxuntianxiaapp.huimee.com.wanandroid.mvp.model.bean.BaseBean
import youxuntianxiaapp.huimee.com.wanandroid.mvp.model.bean.HttpResult

/**
 * Created by yx on 2019/3/28
 */
interface ApiService {
    /**
     * 获取轮播图
     */
    @GET("banner/json")
    fun getBanners():Observable<HttpResult<List<Banner>>>


    /**
     * 退出登录
     */
    @GET("user/logout/json")
    fun logout():Observable<HttpResult<BaseBean>>
}