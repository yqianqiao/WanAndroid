package youxuntianxiaapp.huimee.com.wanandroid.ui.fragment


import android.os.Bundle
import android.support.v4.app.Fragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import youxuntianxiaapp.huimee.com.wanandroid.R
import youxuntianxiaapp.huimee.com.wanandroid.base.BaseFragment
import youxuntianxiaapp.huimee.com.wanandroid.base.BaseMvpFragment
import youxuntianxiaapp.huimee.com.wanandroid.base.IPresenter
import youxuntianxiaapp.huimee.com.wanandroid.base.IView

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class HomeFragment :Fragment() {
    companion object {
        fun getInstance() = HomeFragment()
    }




    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }


}
