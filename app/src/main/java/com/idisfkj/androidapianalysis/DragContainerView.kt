package com.idisfkj.androidapianalysis

import android.content.Context
import android.support.v4.widget.ViewDragHelper
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.widget.RelativeLayout

/**
 * Created by idisfkj on 2018/8/19.
 * Email : idisfkj@gmail.com.
 */
class DragContainerView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : RelativeLayout(context, attrs, defStyleAttr) {
    init {
        createViewDragHelper()
    }

    private lateinit var mViewDragHelper: ViewDragHelper
    private lateinit var mHorizontalView: View
    private lateinit var mVerticalView: View
    private lateinit var mFlexibleView: View
    private var mLeft: Int = 0
    private var mTop: Int = 0

    private fun createViewDragHelper() {
        mViewDragHelper = ViewDragHelper.create(this, object : ViewDragHelper.Callback() {
            override fun tryCaptureView(child: View?, pointerId: Int): Boolean {
                if (mLeft == 0 || mTop == 0) {
                    mLeft = mFlexibleView.left
                    mTop = mFlexibleView.top
                }
                return child == mHorizontalView || child == mVerticalView || child == mFlexibleView
            }

            override fun clampViewPositionVertical(child: View?, top: Int, dy: Int): Int {
                if (child != mHorizontalView) {
                    return top
                }
                return child.top
            }

            override fun clampViewPositionHorizontal(child: View?, left: Int, dx: Int): Int {
                if (child != mVerticalView) {
                    return left
                }
                return child.left
            }

//            override fun getViewHorizontalDragRange(child: View?): Int {
//                return measuredWidth - (child?.measuredWidth?:0)
//            }
//
//            override fun getViewVerticalDragRange(child: View?): Int {
//                return measuredHeight - (child?.measuredHeight?:0)
//            }

            override fun onViewReleased(releasedChild: View?, xvel: Float, yvel: Float) {
                if (releasedChild == mFlexibleView) {
                    mViewDragHelper.settleCapturedViewAt(mLeft, mTop)
                    invalidate()
                } else {
                    super.onViewReleased(releasedChild, xvel, yvel)
                }
            }
        })
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
        mHorizontalView = getChildAt(0)
        mVerticalView = getChildAt(1)
        mFlexibleView = getChildAt(2)
    }

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        return mViewDragHelper.shouldInterceptTouchEvent(ev)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        mViewDragHelper.processTouchEvent(event)
        return true
    }

    override fun computeScroll() {
        if (mViewDragHelper.continueSettling(true)) {
            invalidate()
        }
    }
}