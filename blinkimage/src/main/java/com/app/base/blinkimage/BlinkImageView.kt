package com.app.base.blinkimage

import android.content.Context
import android.util.AttributeSet
import android.widget.ImageView

/**
 * Created by HASEE on 2018/7/8.
 */
class BlinkImageView :ImageView{

    companion object {
        private const val STATUS_BLINKING = 1
        private const val STATUS_UN_BLINK = 2

    }

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    var blinkDuration = 0L//ms

    var currentStatus = STATUS_UN_BLINK

    fun startBlink(){
        if(currentStatus== STATUS_BLINKING){
            return
        }
        currentStatus= STATUS_BLINKING
        handler.postDelayed(BlinkRunnable(),0)
    }

    fun stopBlink(){
        if(currentStatus== STATUS_UN_BLINK){
            return
        }
        handler.removeCallbacksAndMessages(null)
        alpha =1f
        postInvalidate()
        currentStatus= STATUS_UN_BLINK
    }

    private inner class BlinkRunnable : Runnable{
        override fun run() {
            if(alpha==1f){
                alpha = 0f
            }else{
                alpha = 1f
            }
            postInvalidate()
            handler.postDelayed(BlinkRunnable(),blinkDuration)
        }
    }


}