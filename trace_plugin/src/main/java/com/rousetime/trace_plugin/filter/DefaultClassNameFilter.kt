package com.rousetime.trace_plugin.filter

/**
 * Created by idisfkj on 4/9/21.
 */
class DefaultClassNameFilter : ClassNameFilter {

    override fun filter(className: String): Boolean {
        whiteList.forEach {
            if (className.contains(it)) {
                return true
            }
        }
        return false
    }

    companion object {
        val whiteList = arrayListOf<String>(
//                "kotlin",
//                "android",
//                "androidx",
//                "org",
//                "retrofit2",
//                "rxjava2",
//                "io/reactivex",
//                "okhttp3",
//                "glide",
//                "google",
//                "okio"
        )
    }
}