package com.sj7.searchablespinner.searchablespinner

import android.content.Context
import android.graphics.Color
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sj7.searchablespinner.databinding.ItemSpineerListBinding
import com.sj7.searchablespinner.common.DialogListener
import com.sj7.searchablespinner.common.Spinner
import com.sj7.spinner.common.Utilities

class SearchableSpinnerListAdapter(
    private var context: Context,
    private var list: ArrayList<Spinner>,
    private var showTick: Boolean,
    private var listener: DialogListener,
    private var isSearchList: Boolean = false
) : RecyclerView.Adapter<SearchableSpinnerListAdapter.ViewHolder>() {
    private var mainList = ArrayList<Spinner>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
                ItemSpineerListBinding.inflate(
                        LayoutInflater.from(context),
                        parent,
                        false
                )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (holder.adapterPosition >= 0) {
            holder.apply {
                binding.apply {
                    tvText.text = list[holder.adapterPosition].name

                    if (holder.adapterPosition == itemCount - 1)
                        view.visibility = View.INVISIBLE
                    else
                        view.visibility = View.VISIBLE

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        ivCheck.setColorFilter(Utilities.fetchPrimaryColor(context))
                    }

                    if(showTick){
                        if (list[holder.adapterPosition].isSelected) {
                            ivCheck.visibility = View.VISIBLE
                        } else {
                            ivCheck.visibility = View.GONE
                        }
                    }else{
                        ivCheck.visibility = View.GONE
                        if (list[holder.adapterPosition].isSelected) {
                            itemView.setBackgroundColor(Color.parseColor("#dedede"))
                        } else {
                            itemView.setBackgroundColor(Color.WHITE)
                        }
                    }
                }
                itemView.setOnClickListener {
                    if (holder.adapterPosition >= 0) {

                        for (i in 0 until list.size) {
                            list[i].isSelected = false
                        }
                        list[holder.adapterPosition].isSelected = true

                        if (isSearchList) {
                            if (mainList.size >= (list[holder.adapterPosition].parentId + 1)) {
                                for (i in 0 until mainList.size) {
                                    mainList[i].isSelected = false
                                }
                                mainList[list[holder.adapterPosition].parentId].isSelected = true
                            }
                        }

                        notifyDataSetChanged()
                        listener.saveChanges()
                    }
                }
            }
        }
    }

    override fun getItemCount(): Int = list.size

    class ViewHolder(viewBinding: ItemSpineerListBinding) :
            RecyclerView.ViewHolder(viewBinding.root) {
        var binding = viewBinding
    }

    fun setSelection(index: Int) {
        if (list.size >= index + 1) {
            for (i in 0 until list.size) {
                list[i].isSelected = false
            }
            list[index].isSelected = true
            notifyDataSetChanged()
        }
    }

    fun setMainListForSearch(mainList:ArrayList<Spinner>){
        this.mainList = mainList
    }
/*
    fun setMainList(list: ArrayList<Spinner>) {
        this.list = list
        this.isSearchList = false
        notifyDataSetChanged()
    }

    fun setSearchList(searchList: ArrayList<Spinner>, mainList: ArrayList<Spinner>) {
        this.list = searchList
        this.mainList = mainList
        this.isSearchList = true
        notifyDataSetChanged()
    }*/
}