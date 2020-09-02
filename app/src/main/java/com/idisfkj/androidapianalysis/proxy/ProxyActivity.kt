package com.idisfkj.androidapianalysis.proxy

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.idisfkj.androidapianalysis.MainModel
import com.idisfkj.androidapianalysis.R
import com.idisfkj.androidapianalysis.proxy.statistic.Statistic
import com.idisfkj.androidapianalysis.proxy.statistic.StatisticTrack
import com.idisfkj.androidapianalysis.utils.ActivityUtils

/**
 * Created by idisfkj on 2020/9/1.
 * Email: idisfkj@gmail.com.
 */
class ProxyActivity : AppCompatActivity() {

    private val mStatisticService = Statistic.instance.create(StatisticService::class.java)

    companion object {
        private const val BUTTON = "statistic_button"
        private const val TEXT = "statistic_text"
        const val PAGE_NAME = "ProxyActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val extraData = getExtraData()
        setContentView(extraData.layoutId)
        title = extraData.title

//        StatisticTrack.instance.track(StatisticTrack.SCAN_TYPE, PAGE_NAME, StatisticTrack.Parameter(BUTTON))
//        StatisticTrack.instance.track(StatisticTrack.SCAN_TYPE, PAGE_NAME, StatisticTrack.Parameter(TEXT))
        mStatisticService.buttonScan(BUTTON)
        mStatisticService.textScan(TEXT)
    }

    private fun getExtraData(): MainModel =
            intent?.extras?.getParcelable(ActivityUtils.EXTRA_DATA)
                    ?: throw NullPointerException("intent or extras is null")

    fun onClick(view: View) {
        if (view.id == R.id.button) {
//            StatisticTrack.instance.track(StatisticTrack.CLICK_TYPE, PAGE_NAME, StatisticTrack.Parameter(BUTTON, System.currentTimeMillis() / 1000))
            mStatisticService.buttonClick(BUTTON, System.currentTimeMillis() / 1000)
        } else if (view.id == R.id.text) {
//            StatisticTrack.instance.track(StatisticTrack.CLICK_TYPE, PAGE_NAME, StatisticTrack.Parameter(TEXT, System.currentTimeMillis() / 1000))
            mStatisticService.textClick(TEXT, System.currentTimeMillis() / 1000)
        }
    }
}