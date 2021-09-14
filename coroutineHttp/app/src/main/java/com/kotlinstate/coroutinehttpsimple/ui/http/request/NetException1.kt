package me.hgj.jetpackmvvm.demo.data.repository.request

import org.apache.http.conn.ConnectTimeoutException
import org.json.JSONException
import retrofit2.HttpException
import java.lang.RuntimeException
import java.net.ConnectException
import java.net.ProtocolException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.net.ssl.SSLException

class NetException1(message: String?) : RuntimeException(message) {




}