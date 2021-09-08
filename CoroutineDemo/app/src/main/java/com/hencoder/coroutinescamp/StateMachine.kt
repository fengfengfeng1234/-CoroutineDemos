import android.app.Activity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.*

/**
 * 状态机 解析
 * |= ==> 两个二进制对应位为0时该位为0，否则为1
 *  0000 0101
 *  0000 0011
 * ==
 *  0000 0111
 *
 *
 *  &
 *  两个数都转为二进制，然后从高位开始比较，如果两个数都为1则为1，否则为0
 *  lifecycleScope.launch  ---》
 *       什么时候会调用invoke   https://blog.csdn.net/u010218288/article/details/86773259  流程
 *
 *      https://www.jianshu.com/p/edd8748ad49d
 *
 *      https://mp.weixin.qq.com/s/N9BiufCWTRuoh6J-QERlWQ
 */
class StateMachine : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycleScope.launch(start = CoroutineStart.LAZY) {
            testCoroutine()
        }
    }


    private suspend fun getId(): String {
        return GlobalScope.async(Dispatchers.IO) {
            delay(1000)
            "hearing"
        }.await()
    }


    private suspend fun testCoroutine() {
        val user = getUserInfo("strxxxx")
//        val friendList = getFriendList(user)
//        val feedList = getFeedList(friendList)
    }


    private suspend fun getUserInfo(str: String): String {
        var str = "getName1";
        var str2 = "getName2";
        var str3 = "getName3";
        withContext(Dispatchers.IO) {
            delay(1000L)
            str = "getLName1";
            str2 = "getLName2";
            str3 = "getLName2";
        }
        return str + str2
    }
    //挂起函数
// ↓
//    suspend fun getFriendList(user: String): String {
//        withContext(Dispatchers.IO) {
//            delay(1000L)
//        }
//        return "Tom, Jack"
//    }
    //挂起函数
    // ↓
//    suspend fun getFeedList(list: String): String {
//        withContext(Dispatchers.IO) {
//            delay(1000L)
//        }
//        return "{FeedList..}"
//    }


}