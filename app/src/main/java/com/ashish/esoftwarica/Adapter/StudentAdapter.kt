package com.ashish.esoftwarica.Adapter

import android.app.Dialog
import android.content.Context
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.ashish.esoftwarica.R
import com.ashish.esoftwarica.model.StudentModel
import com.bumptech.glide.Glide


class StudentAdapter(
    val listStudents: ArrayList<StudentModel>,
    val context: Context
) : RecyclerView.Adapter<StudentAdapter.StudentViewHolder>() {
    class StudentViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val btnEdit: Button
        val btnDelete: Button
        val imgProfile: ImageView
        val tvName: TextView
        val tvAge: TextView
        val tvGender: TextView
        val tvAddress: TextView
//        val btnCancel:Button

        init {
            imgProfile = view.findViewById(R.id.imgProfile)
            tvName = view.findViewById(R.id.tvName)
            tvAge = view.findViewById(R.id.tvAge)
            tvGender = view.findViewById(R.id.tvGender)
            tvAddress = view.findViewById(R.id.tvAddress)
            btnEdit = view.findViewById(R.id.btnEdit)
            btnDelete = view.findViewById(R.id.btnDelete)
//            btnCancel = view.findViewById(R.id.btnCancel)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.add_student_actor, parent, false)
        return StudentViewHolder(view)
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        val student = listStudents[position]
        holder.tvName.text = student.etName
        holder.tvAge.text = student.etAge
        holder.tvGender.text = student.etGender
        holder.tvAddress.text = student.etAddress

        Glide.with(context)
            .load(student.etImgAddress)
            .into(holder.imgProfile)

        //update
        val dialog = Dialog(context)
        dialog.setContentView(R.layout.update_student_info)
        dialog.window!!.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        dialog.setCancelable(false)
        var etName: EditText = dialog.findViewById(R.id.etName)
        var etAge: EditText = dialog.findViewById(R.id.etAge)
        var etAddress: EditText = dialog.findViewById(R.id.etAddress)
        var etImgUrl: EditText = dialog.findViewById(R.id.etImgUrl)
        var radioGroup: RadioGroup = dialog.findViewById(R.id.radioGroup)
        var btnUpdate: Button = dialog.findViewById(R.id.btnUpdate)
        var btnCancel: Button = dialog.findViewById(R.id.btnCancel)
        var gender = ""
        holder.btnEdit.setOnClickListener {
            etImgUrl.setText(student.etImgAddress)
            etName.setText(student.etName.toString())
            etAge.setText(student.etAge.toString())
            etAddress.setText(student.etAddress)
            when (student.etGender) {
                "Male" -> radioGroup.check(R.id.rbMale)
                "Female" -> radioGroup.check(R.id.rbFemale)
                "Others" -> radioGroup.check(R.id.rbOthers)
            }
            dialog.show()
        }
        radioGroup.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.rbMale -> {
                    gender = "Male"
                }
                R.id.rbFemale -> {
                    gender = "Female"
                }
                R.id.rbOthers -> {
                    gender = "Others"
                }
            }
        }
        btnUpdate.setOnClickListener {
            if (TextUtils.isEmpty(etName.text)) {
                etName.error = "Enter Firstname"
                etName.requestFocus()
            } else if (TextUtils.isEmpty(etAge.text)) {
                etAge.error = "Enter Age"
                etAge.requestFocus()
            } else if (TextUtils.isEmpty(etAddress.text)) {
                etAddress.error = "Enter Address"
                etAddress.requestFocus()
            } else if (TextUtils.isEmpty(etImgUrl.text)) {
                etImgUrl.error = "Enter Image URL"
                etImgUrl.requestFocus()
            } else {
                listStudents[position].etName = etName.text.toString()
                listStudents[position].etAddress = etAddress.text.toString()
                listStudents[position].etImgAddress = etImgUrl.text.toString()
                listStudents[position].etAge = etAge.text.toString()
                listStudents[position].etGender = gender
                notifyDataSetChanged()
                dialog.cancel()

            }
        }
        btnCancel.setOnClickListener {
            dialog.cancel()
        }
        //update ends
//delete
        holder.btnDelete.setOnClickListener {
            listStudents.removeAt(position)
            notifyDataSetChanged()
        }

//delete ends
    }

    override fun getItemCount(): Int {
        return listStudents.size
    }

}