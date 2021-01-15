package com.ashish.esoftwarica.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.ashish.esoftwarica.R
import com.ashish.esoftwarica.listStudents
import com.ashish.esoftwarica.model.StudentModel

class DashboardFragment : Fragment() {

    private lateinit var dashboardViewModel: DashboardViewModel
    private lateinit var etImgProfile:EditText
    private lateinit var etName:EditText
    private lateinit var etAge:EditText
    private lateinit var radioGroup: RadioGroup
    private lateinit var etAddress:EditText
    private lateinit var btnSave:Button
    var gender=""

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        dashboardViewModel =
                ViewModelProvider(this).get(DashboardViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_dashboard, container, false)

        etName=root.findViewById(R.id.etName)
        etAge=root.findViewById(R.id.etAge)
        etAddress=root.findViewById(R.id.etAddress)
        etImgProfile=root.findViewById(R.id.etImgAddress)
        radioGroup=root.findViewById(R.id.radioGroup)
        btnSave=root.findViewById(R.id.btnSave)


        btnSave.setOnClickListener {
            data()
            radioGroupListener()
        }

        return root
    }

    private fun radioGroupListener() {
        radioGroup.setOnCheckedChangeListener{group,checkId->
            when(checkId){
                R.id.rbMale->
                    gender="Male"
                R.id.rbFemale->
                    gender="Female"
                R.id.rbOthers->
                    gender="Others"
            }
        }
    }

    private fun data() {
        val image=etImgProfile.text.toString()
        val name=etName.text.toString()
        val age=etAge.text.toString()
        val address=etAddress.text.toString()
        listStudents.add(StudentModel(image,name,age,gender,address))

    }
}