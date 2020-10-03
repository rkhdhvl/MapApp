package com.practice.maps_recyclerview.view

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.widget.FrameLayout

class MyFrameLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0): FrameLayout(context, attrs, defStyle) {

    private var shouldDisallowInterceptTouchEvent : Boolean = false

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        parent.requestDisallowInterceptTouchEvent(shouldDisallowInterceptTouchEvent)
        return super.onInterceptTouchEvent(ev)
    }

    fun controlTouchEvent(b:Boolean){
       shouldDisallowInterceptTouchEvent = b
   }

}
