package youxuntianxiaapp.huimee.com.wanandroid.mvp.model.bean

import com.squareup.moshi.Json


data class HttpResult<T>(
        @Json(name = "data") var data: T
) : BaseBean()

//文章
data class ArticleResponseBody(
        @Json(name = "curPage") val curPage: Int,
        @Json(name = "datas") var datas: MutableList<Article>,
        @Json(name = "offset") val offset: Int,
        @Json(name = "over") val over: Boolean,
        @Json(name = "pageCount") val pageCount: Int,
        @Json(name = "size") val size: Int,
        @Json(name = "total") val total: Int

)

data class Article(
        @Json(name = "apkLink") val apkLink: String,
        @Json(name = "author") val author: String,
        @Json(name = "chapterId") val chapterId: Int,
        @Json(name = "chapterName") val chapterName: String,
        @Json(name = "collect") var collect: Boolean,
        @Json(name = "courseId") val courseId: Int,
        @Json(name = "desc") val desc: String,
        @Json(name = "envelopePic") val envelopePic: String,
        @Json(name = "fresh") val fresh: Boolean,
        @Json(name = "id") val id: Int,
        @Json(name = "link") val link: String,
        @Json(name = "niceDate") val niceDate: String,
        @Json(name = "origin") val origin: String,
        @Json(name = "projectLink") val projectLink: String,
        @Json(name = "publishTime") val publishTime: Long,
        @Json(name = "superChapterId") val superChapterId: Int,
        @Json(name = "superChapterName") val superChapterName: String,
        @Json(name = "tags") val tags: MutableList<Tag>,
        @Json(name = "title") val title: String,
        @Json(name = "type") val type: Int,
        @Json(name = "userId") val userId: Int,
        @Json(name = "visible") val visible: Int,
        @Json(name = "zan") val zan: Int,
        @Json(name = "top") var top: String
)

data class Tag(
        @Json(name = "name") val name: String,
        @Json(name = "url") val url: String
)

//轮播
data class Banner(
        @Json(name = "desc") val desc: String,
        @Json(name = "id") val id: Int,
        @Json(name = "imagePath") val imagePath: String,
        @Json(name = "isVisible") val isVisible: Int,
        @Json(name = "order") val order: Int,
        @Json(name = "title") val title: String,
        @Json(name = "type") val type: Int,
        @Json(name = "url") val url: String)

// 登录数据
data class LoginData(
        @Json(name = "chapterTops") val chapterTops: MutableList<String>,
        @Json(name = "collectIds") val collectIds: MutableList<String>,
        @Json(name = "email") val email: String,
        @Json(name = "icon") val icon: String,
        @Json(name = "id") val id: Int,
        @Json(name = "password") val password: String,
        @Json(name = "token") val token: String,
        @Json(name = "type") val type: Int,
        @Json(name = "username") val username: String
)