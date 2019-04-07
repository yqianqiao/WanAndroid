package youxuntianxiaapp.huimee.com.wanandroid.ui.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import cn.bingoogolapple.bgabanner.BGABanner
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.item_home_banner.*

import youxuntianxiaapp.huimee.com.wanandroid.R
import youxuntianxiaapp.huimee.com.wanandroid.adapter.HomeAdapter
import youxuntianxiaapp.huimee.com.wanandroid.base.BaseFragment
import youxuntianxiaapp.huimee.com.wanandroid.base.BaseMvpFragment
import youxuntianxiaapp.huimee.com.wanandroid.base.IPresenter
import youxuntianxiaapp.huimee.com.wanandroid.base.IView
import youxuntianxiaapp.huimee.com.wanandroid.mvp.contract.HomeConteact
import youxuntianxiaapp.huimee.com.wanandroid.mvp.model.bean.ArticleResponseBody
import youxuntianxiaapp.huimee.com.wanandroid.mvp.model.bean.Banner
import youxuntianxiaapp.huimee.com.wanandroid.mvp.presenter.HomePresenter
import youxuntianxiaapp.huimee.com.wanandroid.utils.ImageLoader

/**
 * A simple [Fragment] subclass.
 *
 */
class HomeFragment : BaseMvpFragment<HomeConteact.View, HomeConteact.Presenter>(), HomeConteact.View {
    private var isRefresh = true
    /**
     * banner view
     */
    private var bannerView: View? = null

    private val homeAdapter: HomeAdapter by lazy {
        HomeAdapter()
    }

    private val bannerAdapter: BGABanner.Adapter<ImageView, String> by lazy {
        BGABanner.Adapter<ImageView, String> { banner, itemView, model, position ->
            ImageLoader.load(activity!!, model, itemView)
        }
    }
    private val linearLayoutManager: LinearLayoutManager by lazy {
        LinearLayoutManager(activity)
    }

    override fun createPresenter() = HomePresenter()

    override fun attachLayoutRes() = R.layout.fragment_home


    override fun lazyLoad() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun scrollToTop() {
        recyclerView.run {
            if (linearLayoutManager.findFirstVisibleItemPosition() > 20) {
                scrollToPosition(0)
            } else {
                smoothScrollToPosition(0)
            }
        }
    }

    override fun initView(view: View) {
        super.initView(view)
        mLayoutStatusView = multiple_status_view
        swipeRefreshLayout.setOnRefreshListener(onRefreshListener)

        recyclerView.run {
            layoutManager = linearLayoutManager
            adapter = homeAdapter
            itemAnimator = DefaultItemAnimator()
            addItemDecoration(DividerItemDecoration(activity, LinearLayout.VERTICAL))
        }
        bannerView = layoutInflater.inflate(R.layout.item_home_banner,null)
//        banner?.setDelegate(ba)
        
    }

    override fun setBanner(banner: List<Banner>) {

    }

    override fun setArticles(articles: ArticleResponseBody) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showCollectSuccess() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showCancelCollectSuccess() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    companion object {
        fun getInstance() = HomeFragment()
    }

    private val onRefreshListener = SwipeRefreshLayout.OnRefreshListener {
        isRefresh = true
        homeAdapter.setEnableLoadMore(false)
        mPresenter?.requestHomeData()
    }

}
