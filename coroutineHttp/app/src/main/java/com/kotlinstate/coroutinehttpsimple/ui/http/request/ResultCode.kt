package me.hgj.jetpackmvvm.demo.data.repository.request

interface ResultCode {
    companion object {
        const val SUCCESS = 200 //服务端返回正常
        const val FAILED = 0 //服务端返回异常
        const val ERROR = 201 //安全认证和登录失效
        const val LOGIN_ERROR = 202 //消费者登录失效

        /**
         * 这里是自定义的errorCode，和服务器无关
         */
        const val NET_WORK_ERROR = -1 //网络异常
        const val UNKNOWN_ERROR = -2 // 未知异常
        const val JSON_PARSE_ERROR = -3 //json 序列化异常
        const val SUCCESS_MAP_ERROR = -4 //successMap 操作符中异常
        const val ERROR_MAP_ERROR = -5 //errorMap 操作符中异常
        const val PROCESS_ERROR = -6 //process 操作符中异常
        const val SUCCESS_PROCESS_ERROR = -7 //successProcess 操作符中异常
        const val ERROR_PROCESS_ERROR = -8 //errorProcess 操作符中异常
        const val MERGE_ERROR = -9 //merge 操作符中异常
    }
}