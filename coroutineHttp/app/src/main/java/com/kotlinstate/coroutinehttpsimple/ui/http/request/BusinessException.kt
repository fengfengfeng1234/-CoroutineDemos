package me.hgj.jetpackmvvm.demo.data.repository.request

import java.lang.RuntimeException

class BusinessException(var code: Int, message: String?): RuntimeException(message) {
}