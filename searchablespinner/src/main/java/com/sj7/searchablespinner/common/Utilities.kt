package com.sj7.spinner.common

import android.content.Context
import android.content.res.TypedArray
import android.os.Build
import android.util.TypedValue
import androidx.annotation.RequiresApi

class Utilities {
    companion object{
        @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
        fun fetchPrimaryColor(context: Context): Int {
            val typedValue = TypedValue()
            val a: TypedArray =
                context.obtainStyledAttributes(typedValue.data, intArrayOf(android.R.attr.colorPrimary))
            val color = a.getColor(0, 0)
            a.recycle()
            return color
        }
    }
}