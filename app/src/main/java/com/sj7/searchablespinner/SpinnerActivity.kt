package com.sj7.searchablespinner

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.sj7.searchablespinner.databinding.ActivitySpinnerBinding
import com.sj7.searchablespinner.searchablespinner.SearchableSpinnerDialog
import com.sj7.searchablespinner.common.Spinner
import com.sj7.searchablespinner.common.SpinnerListener

class SpinnerActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySpinnerBinding
    private var context: Context = this@SpinnerActivity

    private lateinit var sdUser: SearchableSpinnerDialog
    private var userList = ArrayList<Spinner>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySpinnerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUserList()
    }

    private fun setUserList() {
        if (userList.isNotEmpty())
            userList.clear()

        userList.add(
            Spinner(
                "0",
                "--Select--"
            )
        )
        for (i in 1..26){
            userList.add(
                Spinner(
                    i.toString(),
                    "User $i"
                )
            )
        }

        setUserListAdapter()
    }

    private fun setUserListAdapter() {
        sdUser = SearchableSpinnerDialog()

        sdUser.setSpinnerDialog(
                context, binding.spUser, "Select User", userList,
                object : SpinnerListener {
                    override fun setOnItemClickListener(position: Int) {
                        Toast.makeText(context, userList[position].id, Toast.LENGTH_SHORT).show()
                    }
                }
        )

        //by default user having id 2 is selected
        sdUser.findSelectedItemIndexById("2")
    }
}