package youxuntianxiaapp.huimee.com.wanandroid.http.exception

import java.lang.RuntimeException

/**
 *   Created by YX on 2019/3/29 17:11.
 */
class ApiException : RuntimeException {
    private var code: Int? = null

    constructor(throwable: Throwable, code: Int) : super(throwable) {
        this.code = code
    }

    constructor(message: String) : super(Throwable(message))
}