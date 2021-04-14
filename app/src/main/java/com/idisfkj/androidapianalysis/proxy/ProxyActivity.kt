package com.idisfkj.androidapianalysis.proxy

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.idisfkj.androidapianalysis.MainModel
import com.idisfkj.androidapianalysis.R
import com.idisfkj.androidapianalysis.proxy.statistic.Statistic
import com.idisfkj.androidapianalysis.proxy.statistic.annomation.TrackClick
import com.idisfkj.androidapianalysis.proxy.statistic.annomation.TrackClickData
import com.idisfkj.androidapianalysis.proxy.statistic.annomation.TrackScan
import com.idisfkj.androidapianalysis.proxy.statistic.annomation.TrackScanData
import com.idisfkj.androidapianalysis.utils.ActivityUtils

/**
 * Created by idisfkj on 2020/9/1.
 * Email: idisfkj@gmail.com.
 */
class ProxyActivity : AppCompatActivity() {

//    private val mStatisticService = Statistic.instance.create(StatisticService::class.java)

    @TrackClickData
    private var mTrackModel = TrackModel()

    @TrackScanData
    private var mTrackScanData = mutableListOf<TrackModel>()

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
        onScan()
    }

    private fun getExtraData(): MainModel =
            intent?.extras?.getParcelable(ActivityUtils.EXTRA_DATA)
                    ?: throw NullPointerException("intent or extras is null")

    @TrackScan
    fun onScan() {
        mTrackScanData.add(TrackModel(name = BUTTON))
        mTrackScanData.add(TrackModel(name = TEXT))
    }

    @TrackClick
    fun onClick(view: View) {
        mTrackModel.time = System.currentTimeMillis() / 1000
        mTrackModel.name = if (view.id == R.id.button) BUTTON else TEXT
//        mStatisticService.trackClick(mTrackModel.name, mTrackModel.time)
    }
}