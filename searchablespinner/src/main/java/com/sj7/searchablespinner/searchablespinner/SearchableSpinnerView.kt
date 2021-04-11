package com.sj7.searchablespinner.searchablespinner

import android.content.Context
import android.os.Build
import android.text.TextUtils
import android.util.AttributeSet
import android.view.Gravity
import androidx.annotation.Nullable
import androidx.appcompat.widget.AppCompatTextView
import com.sj7.searchablespinner.R

class SearchableSpinnerView : AppCompatTextView {

    var showSearchBar = true
    var setCancelable = false
    var setCanceledOnTouchOutside = false
    var showTick = false

    constructor(context: Context) : super(context) {
        initView()
    }

    constructor(context: Context, @Nullable attrs: AttributeSet) : super(context, attrs) {
        //initView()
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.SearchableSpinnerView)
        try{
            for(i in 0 until typedArray.indexCount){
                val attr = typedArray.getIndex(i)
                if(attr == R.styleable.SearchableSpinnerView_showSearchBar){
                    showSearchBar = typedArray.getBoolean(attr,true)
                    initView()
                }else if(attr == R.styleable.SearchableSpinnerView_setCancelable){
                    setCancelable = typedArray.getBoolean(attr,false)
                    initView()
                }else if(attr == R.styleable.SearchableSpinnerView_setCanceledOnTouchOutside){
                    setCanceledOnTouchOutside = typedArray.getBoolean(attr,false)
                    initView()
                }else if(attr == R.styleable.SearchableSpinnerView_showTick){
                    showTick = typedArray.getBoolean(attr,false)
                    initView()
                }
            }

        }finally {
            typedArray.recycle()
        }
    }

    constructor(context: Context, @Nullable attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        initView()
    }

    private fun initView() {
        this.apply {
            gravity = Gravity.CENTER_VERTICAL
            isSingleLine = true
            compoundDrawablePadding = 5
            ellipsize = TextUtils.TruncateAt.END
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            this.setCompoundDrawablesRelativeWithIntrinsicBounds(
                0, 0,
                R.drawable.ic_drop_down_arrow_black, 0
            )
        }
    }
}