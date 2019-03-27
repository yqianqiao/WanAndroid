package youxuntianxiaapp.huimee.com.wanandroid.ext

import android.app.Activity
import android.content.Context
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import youxuntianxiaapp.huimee.com.wanandroid.R
import youxuntianxiaapp.huimee.com.wanandroid.widget.CustomToast


fun Any.loge(content: String?) {
    loge(this.javaClass.simpleName, content ?: "")
}

fun loge(tag: String, content: String?) {
    Log.e(tag, content ?: "")
}

/**
 * toast
 */
fun Fragment.showToast(content: String) {
    CustomToast(this.activity?.applicationContext, content).show()
}
fun Context.showToast(content: String) {
    CustomToast(this, content).show()
}

fun Activity.showSnackMsg(msg: String) {
    val snackbar = Snackbar.make(this.window.decorView, msg, Snackbar.LENGTH_SHORT)
    val view = snackbar.view
    view.findViewById<TextView>(R.id.snackbar_text).setTextColor(ContextCompat.getColor(this, R.color.white))
    snackbar.show()
}
