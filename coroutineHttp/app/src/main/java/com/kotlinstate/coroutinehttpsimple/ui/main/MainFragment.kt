package com.kotlinstate.coroutinehttpsimple.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.kotlinstate.coroutinehttpsimple.R
import com.kotlinstate.coroutinehttpsimple.ui.http.apiService
import com.medi.comm.network.result.awaitOrError
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

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
        viewModel.requestGlobal()
        viewModel.requestHomeData()

        viewLifecycleOwner.lifecycleScope.launch {
            val (result3, message3) = async {
                apiService.getProjecTitle()
            }.awaitOrError()
        }
    }

}