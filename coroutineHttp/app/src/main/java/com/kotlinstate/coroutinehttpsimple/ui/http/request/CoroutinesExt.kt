package com.medi.comm.network.result

import android.util.Log
import com.kotlinstate.coroutinehttpsimple.ui.http.request.ApiResponse
import kotlinx.coroutines.Deferred
import me.hgj.jetpackmvvm.demo.data.repository.request.BusinessException
import me.hgj.jetpackmvvm.demo.data.repository.request.NetException
import me.hgj.jetpackmvvm.demo.data.repository.request.ResultCode
import org.apache.http.conn.ConnectTimeoutException
import org.json.JSONException
import retrofit2.HttpException
import java.net.ConnectException
import java.net.ProtocolException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.net.ssl.SSLException


suspend inline fun <reified T> Deferred<ApiResponse<T>>.awaitOrError(): Result<ApiResponse<T>> {
    return try {
        val result = await()
        if (result.errorCode != 0) {
            //业务异常
            var exception = BusinessException(result.errorCode, result.errorMsg)
            return Result.of(getErrorMessage(exception))
        }

        return Result.of(result)
    } catch (e: Exception) {
        return Result.of(getErrorMessage(e))
    }
}


fun getErrorMessage(error: Throwable): NetException {
    val msg: String
    val code: Int
    if (error is UnknownHostException
        || error is ProtocolException
        || error is ConnectException
    ) {
        msg = "网络异常，请检查网络环境！"
        code = ResultCode.NET_WORK_ERROR
    } else if (error is JSONException) {
        msg = "数据格式异常，请稍后再试！"
        code = ResultCode.JSON_PARSE_ERROR
    } else if (error is SSLException) {
        msg = "连接异常或者服务器异常，请稍后再试！"
        code = ResultCode.NET_WORK_ERROR
    } else if (error is SocketTimeoutException || error is ConnectTimeoutException) {
        msg = "连接服务器超时，请检查网络环境"
        code = ResultCode.NET_WORK_ERROR
    } else if (error is HttpException) { //http异常
        code = error.code()
        msg = "请求失败，请稍后再试 $code"
    } else if (error is BusinessException) { // 业务异常
        code = error.code
        msg = if (error.message == null) {
            ""
        } else {
            error.message!!;
        }

    } else {
        msg = "请求失败，请稍后再试"
        code = ResultCode.UNKNOWN_ERROR
    }
    return NetException(error, code, msg)
}
