package com.kotlinstate.coroutinehttpsimple.ui.main

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.kotlinstate.coroutinehttpsimple.HttpCanCelActivity
import com.kotlinstate.coroutinehttpsimple.R
import com.kotlinstate.coroutinehttpsimple.ui.http.apiService
import com.medi.comm.network.result.awaitOrError
import kotlinx.coroutines.*
import java.lang.Exception
//   返回多个参数  解构声明  （Destructuring declaration) 请求1
class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }
    //https://www.kotlincn.net/docs/reference/coroutines/composing-suspending-functions.html#%E4%BD%BF%E7%94%A8-async-%E5%B9%B6%E5%8F%91
    /**
     * 1. 并发 其中某一个异常处理
     *          1.全部终止
     *          2.终止某一个
     * 2. 作用域 类别的区分
     *          全局
     *          局部 activity  fragment
     * 3. 网络请求取消
     *   根据生命周期
     *          例如 viewModel
     *          手动自动取消
     * 4  网络异常导致并发中断
     *      1. 两个网络同时 其中有一个不行
     *      2. 依次网络请求场景
     *      3. 取消单独一个不影响另外一个
     * 协程 各种内联 关键字用法.
     */
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        ).get(MainViewModel::class.java)



        view?.findViewById<Button>(R.id.jumpActFinish)?.setOnClickListener {
            var intent = Intent(activity, HttpCanCelActivity::class.java);
            startActivity(intent)
        }
        view?.findViewById<Button>(R.id.ordinaryNetworkBt)?.setOnClickListener {
            requestSingle()
        }

        content = view?.findViewById<TextView>(R.id.content)!!

    }


    val handler = CoroutineExceptionHandler { _, exception ->
        Log.e("MainFragment", "CoroutineExceptionHandler got $exception")
    }

    lateinit var content: TextView

    /**
     * 并发请求
     */
    private fun requestSingle() {
        lifecycleScope.launch(handler) {

            val req1 = async {
                apiService.getAritrilList(0)
            }


            val req2 = async {
                apiService.getBanner()
            }

            req1.awaitOrError()
            req2.awaitOrError()

        }

    }

}