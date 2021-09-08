package com.hencoder.coroutinescamp.oprators

import kotlin.coroutines.Continuation
import kotlin.coroutines.startCoroutine

/**
 * author: taolipeng
 * time:   2021/4/12
 * 调用操作符（Invoke operator）
 * a() a.invoke()
 */
fun  main(){
    val family = OperatorDemo2()

   family.plus { family.addMember("xxx") }
}

class OperatorDemo2 {

    fun addMember(name: String) {
        println("xxxxxx --1111")
    }

    public operator fun plus(body: OperatorDemo2.() -> Unit) {
        body()
        println("xxxxxx")
    }
    public operator fun invoke() {
        println("无参数")
    }

}