package com.sonia.recyclerviewadapter

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.sonia.recyclerviewadapter.databinding.ActivityMainBinding
import com.sonia.recyclerviewadapter.databinding.CustomDialogBinding

class MainActivity : AppCompatActivity(), RecyclerInterface {
    private var binding: ActivityMainBinding?=null
    private var factList = arrayListOf<Facts>()
    private var recyclerAdapter = RecyclerAdapter(factList,this)
    lateinit var linearLayoutManager: LinearLayoutManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding?.recyclerView?.layoutManager = linearLayoutManager
        binding?.recyclerView?.adapter = recyclerAdapter
        binding?.btnfab?.setOnClickListener {
            var dialogBinding = CustomDialogBinding.inflate(layoutInflater)
            var dialog = Dialog(this).apply {
                setContentView(dialogBinding.root)
                show()
            }
            dialog.window?.setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            dialogBinding.btnadd.setOnClickListener {
                if (dialogBinding.ettitle.text?.toString().isNullOrEmpty()) {
                    dialogBinding.ettitle.error = "Enter Your rollno "
                } else if (dialogBinding.etdescription.text?.toString().isNullOrEmpty()) {
                    dialogBinding.etdescription.error = "Enter Your Name "
                } else {
                    factList.add(
                        Facts(
                            dialogBinding.ettitle.text.toString(),
                            dialogBinding.etdescription.text.toString(),
                        )
                    )
                    recyclerAdapter.notifyDataSetChanged()
                    dialog.dismiss()
                }
            }
            dialog.show()
        }
    }
    override fun delete(position: Int) {
        AlertDialog.Builder(this@MainActivity).apply {
            setTitle("Delete")
            setMessage("Are u sure to delet this")
            setCancelable(false)
            setNegativeButton("No") { _, _ ->
                setCancelable(true)
            }
            setPositiveButton("Yes") { _, _ ->
                Toast.makeText(this@MainActivity,"Successfully Deleted", Toast.LENGTH_SHORT)
                    .show()
                factList.removeAt(position)
                recyclerAdapter.notifyDataSetChanged()
            }
            show()
        }
    }

    override fun update(position: Int) {
        var dialogBinding=CustomDialogBinding.inflate(layoutInflater)
        var dialog=Dialog(this).apply {
            setContentView(dialogBinding.root)
            show()
        }
        val update="Update"
        dialogBinding.btnadd.setText(update)
        val upTitle:String=factList[position].title.toString()
        dialogBinding.ettitle.setText(upTitle)
        val upDesc:String=factList[position].description.toString()
        dialogBinding.etdescription.setText(upDesc)
        dialog.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        dialogBinding.btnadd.setOnClickListener {
            if (dialogBinding.ettitle.text?.toString()?.trim().isNullOrEmpty()){
                dialogBinding.ettitle.error="Enter Title"
            }else if (dialogBinding.etdescription.text?.toString()?.trim().isNullOrEmpty()){
                dialogBinding.etdescription.error="Enter Description"
            }else{ factList.set(position,
                Facts(dialogBinding.ettitle.text?.toString(),dialogBinding.etdescription.text?.toString()?.trim())
            )
                recyclerAdapter.notifyDataSetChanged()
                dialog.dismiss()
            }
        }
        dialog.show()
    }
}