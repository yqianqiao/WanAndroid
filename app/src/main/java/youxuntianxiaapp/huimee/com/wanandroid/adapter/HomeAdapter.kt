package youxuntianxiaapp.huimee.com.wanandroid.adapter

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import youxuntianxiaapp.huimee.com.wanandroid.R
import youxuntianxiaapp.huimee.com.wanandroid.mvp.model.bean.Article
import youxuntianxiaapp.huimee.com.wanandroid.utils.ImageLoader

/**
 * Created by yx on 2019/4/6
 */
class HomeAdapter() : BaseQuickAdapter<Article, BaseViewHolder>(R.layout.item_home_list) {


    override fun convert(helper: BaseViewHolder?, item: Article?) {
        item ?: return
        helper ?: return
        helper.setText(R.id.tv_article_title, item.title)
                .setText(R.id.tv_article_author, item.author)
                .setText(R.id.tv_article_date, item.niceDate)
                .setImageResource(R.id.iv_like,
                        if (item.collect) R.drawable.ic_like else R.drawable.ic_like_not)
                .addOnClickListener(R.id.iv_like)
        val chapterName = when {
            item.superChapterName.isNotEmpty() and item.chapterName.isNotEmpty() ->
                "${item.superChapterName} / ${item.chapterName}"
            item.superChapterName.isNotEmpty() -> item.superChapterName
            item.chapterName.isNotEmpty() -> item.chapterName
            else -> ""
        }
        helper.setText(R.id.tv_article_chapterName, chapterName)
        if (item.envelopePic.isNotEmpty()) {
            helper.getView<ImageView>(R.id.iv_article_thumbnail)
                    .visibility = View.VISIBLE
            ImageLoader.load(mContext, item.envelopePic, helper.getView(R.id.iv_article_thumbnail))
        } else {
            helper.getView<ImageView>(R.id.iv_article_thumbnail).visibility = View.GONE
        }

        if (item.fresh) {
            helper.setVisible(R.id.tv_article_fresh,true)
        }else{
            helper.setVisible(R.id.tv_article_fresh,false)
        }
        if (item.top==="1"){
            helper.setVisible(R.id.tv_article_top,true)
        }else{
            helper.setVisible(R.id.tv_article_top,false)
        }
        if (item.tags.size>0){
            helper.setVisible(R.id.tv_article_tag,true)
            helper.setText(R.id.tv_article_tag,item.tags[0].name)
        }else{
            helper.setVisible(R.id.tv_article_tag,false)
        }
    }
}