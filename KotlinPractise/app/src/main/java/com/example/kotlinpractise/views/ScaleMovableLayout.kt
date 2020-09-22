package com.example.kotlinpractise.views

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.widget.FrameLayout
import android.widget.RelativeLayout
import com.example.kotlinpractise.R

private const val TAG = "ScaleMovableLayout"

class ScaleMovableLayout :FrameLayout{


    private val DEFAULT_WIDTH = 400;

    private val DEFAULT_HEIGHT = 200;

    private val PARENT_WIDTH = 1920;

    private val PARENT_HEIGHT = 1080;

    private val DEFAULT_RESIZE_WIDTH = 50;

    private val OPERATION_MOVE = 0;

    private val OPERATION_RESIZE_LEFT_TOP = 1;
    private val OPERATION_RESIZE_LEFT_BOTTOM = 2;
    private val OPERATION_RESIZE_RIGHT_TOP = 3;
    private val OPERATION_RESIZE_RIGHT_BOTTOM = 4;

    private var mContext:Context? = null;
    private var mWidth:Int = 0;
    private var mHeight:Int = 0;
    private var downRawX:Float = 0f;
    private var downRawY:Float = 0f;
    private var downX:Float = 0f;
    private var downY:Float = 0f;
    private var lastRawX:Float = 0f;
    private var lastRawY:Float = 0f;
    private var operationType:Int = OPERATION_MOVE;

    constructor(context: Context) : this(context,null) {
        Log.i(TAG,"constructor 1")

    }

    constructor(context: Context,attrs: AttributeSet?) : this(context,attrs,0) {
        Log.i(TAG,"constructor 2")

    }

    constructor(context: Context,attrs: AttributeSet?,defStyleAttr:Int):super(context, attrs,defStyleAttr)
    {
        Log.i(TAG,"constructor 3")
        initView(context);
    }

    private fun initView(context: Context)
    {
        mContext = context;
        mWidth = DEFAULT_WIDTH;
        mHeight = DEFAULT_HEIGHT;
        LayoutInflater.from(mContext).inflate(R.layout.lay_scale_movable,this,true);
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if(event != null)
        {
            when (event.action)
            {
                MotionEvent.ACTION_DOWN ->
                {
                    downRawX = event.rawX.also { lastRawX = it }
                    downRawY = event.rawY.also { lastRawY = it }

                    downX = event.x
                    downY = event.y
                    if(downX <= DEFAULT_RESIZE_WIDTH && downY <=DEFAULT_RESIZE_WIDTH)
                    {
                        operationType = OPERATION_RESIZE_LEFT_TOP;
                    }else if(downX <= DEFAULT_RESIZE_WIDTH && downY >= mHeight - DEFAULT_RESIZE_WIDTH)
                    {
                        operationType = OPERATION_RESIZE_LEFT_BOTTOM;

                    }else if(downX >= mWidth - DEFAULT_RESIZE_WIDTH && downY <=DEFAULT_RESIZE_WIDTH)
                    {
                        operationType = OPERATION_RESIZE_RIGHT_TOP;

                    }else if(downX >= mWidth - DEFAULT_RESIZE_WIDTH && downY >= mHeight - DEFAULT_RESIZE_WIDTH)
                    {
                        operationType = OPERATION_RESIZE_RIGHT_BOTTOM;

                    }else{
                        operationType = OPERATION_MOVE;
                    }
                    Log.i(TAG,"operationType $operationType downX: $downX downY $downY")
                }
                MotionEvent.ACTION_MOVE ->
                {
//                    var distanceX:Float = event.rawX - lastRawX;
//                    var distanceY:Float = event.rawY - lastRawY;
//                    var nextX = x + distanceX;
//                    var nextY = y + distanceY
                    val distanceX = event.rawX - lastRawX
                    val distanceY = event.rawY - lastRawY
                    val nextX = x + distanceX
                    val nextY = y + distanceY
                    if(operationType == OPERATION_MOVE)
                    {
//                        x = nextX;
//                        y = nextY;
                        x = nextX
                        y = nextY
                    }else if(operationType == OPERATION_RESIZE_LEFT_TOP)
                    {

                    }else if(operationType == OPERATION_RESIZE_RIGHT_TOP)
                    {

                    }else if(operationType == OPERATION_RESIZE_RIGHT_BOTTOM)
                    {

                    }

                    lastRawX = event.rawX;
                    lastRawY = event.rawY;

                }
                MotionEvent.ACTION_UP->{
                    if(operationType == OPERATION_MOVE)
                    {
                        val lp = layoutParams as? RelativeLayout.LayoutParams
                        if(lp != null)
                        {
                            lp.leftMargin = x.toInt()
                            lp.topMargin = y.toInt()
                            lp.rightMargin = PARENT_WIDTH - (x.toInt() + mWidth)
                            lp.bottomMargin = PARENT_HEIGHT - (y.toInt() + mHeight)
                            layoutParams = lp
                        }

                    }
                }else ->
            {
                //do nothing
            }


            }
        }
        return true
    }
}