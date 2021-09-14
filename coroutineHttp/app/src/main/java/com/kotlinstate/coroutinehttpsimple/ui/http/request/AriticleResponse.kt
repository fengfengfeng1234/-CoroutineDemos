package com.kotlinstate.coroutinehttpsimple.ui.http.request

import android.annotation.SuppressLint
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


/**
 * 文章
 */
@SuppressLint("ParcelCreator")
@Parcelize
data class AriticleResponse(
    var apkLink: String,
    var author: String,//作者
    var chapterId: Int,
    var chapterName: String,
    var collect: Boolean,//是否收藏
    var courseId: Int,
    var desc: String,
    var envelopePic: String,
    var fresh: Boolean,
    var id: Int,
    var link: String,
    var niceDate: String,
    var origin: String,
    var prefix: String,
    var projectLink: String,
    var publishTime: Long,
    var superChapterId: Int,
    var superChapterName: String,
    var shareUser: String,
    var title: String,
    var type: Int,
    var userId: Int,
    var visible: Int,
    var zan: Int) : Parcelable {
    override fun toString(): String {
        return "AriticleResponse(apkLink='$apkLink', author='$author', chapterId=$chapterId, chapterName='$chapterName', collect=$collect, courseId=$courseId, desc='$desc', envelopePic='$envelopePic', fresh=$fresh, id=$id, link='$link', niceDate='$niceDate', origin='$origin', prefix='$prefix', projectLink='$projectLink', publishTime=$publishTime, superChapterId=$superChapterId, superChapterName='$superChapterName', shareUser='$shareUser', title='$title', type=$type, userId=$userId, visible=$visible, zan=$zan)"
    }
}
