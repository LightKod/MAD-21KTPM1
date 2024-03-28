package com.example.mad_21ktpm1_group11.fragments

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.content.res.ColorStateList
import android.icu.util.Calendar
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.widget.CompoundButtonCompat
import androidx.fragment.app.Fragment
import com.example.mad_21ktpm1_group11.MainActivity
import com.example.mad_21ktpm1_group11.R
import com.google.android.material.textfield.TextInputLayout
import java.util.regex.Pattern


class UserDetailFragment : Fragment() {
    private lateinit var backBtn: ImageButton
    private lateinit var menuBtn: ImageButton

    private lateinit var inputLayoutUserName: TextInputLayout
    private lateinit var inputLayoutUserPhone: TextInputLayout
    private lateinit var inputLayoutUserEmail: TextInputLayout
    private lateinit var inputLayoutUserDOB: TextInputLayout
    private lateinit var inputLayoutUserGender: TextInputLayout
    private lateinit var inputLayoutUserCity: TextInputLayout

    private lateinit var editTextUserName: EditText
    private lateinit var editTextUserPhone: EditText
    private lateinit var editTextUserEmail: EditText
    private lateinit var editTextUserDOB: EditText
    private lateinit var editTextUserGender: EditText
    private lateinit var editTextUserCity: EditText

    private lateinit var saveBtn: Button

    private val dummyUserName: String = "Nguyễn Văn A"
    private val dummyUserPhone: String = "0123456789"
    private val dummyUserEmail: String = "mail@mail.com"
    private val dummyUserDOB: String = "01/01/2000"
    private val dummyUserGender: String = "Male"
    private val dummyUserCity: String = "Hồ Chí Minh"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_user_detail, container, false)

        init(view)

        return view
    }

    private fun init(view: View){
        backBtn = view.findViewById(R.id.backBtn)
        menuBtn = view.findViewById(R.id.menuBtn)

        backBtn.setOnClickListener {
            (this.activity as? MainActivity)?.goBack()
        }

        menuBtn.setOnClickListener {
            (this.activity as? MainActivity)?.openDrawer()
        }

        inputLayoutUserName = view.findViewById(R.id.inputLayoutUserName)
        inputLayoutUserPhone = view.findViewById(R.id.inputLayoutUserPhone)
        inputLayoutUserEmail = view.findViewById(R.id.inputLayoutUserEmail)
        inputLayoutUserDOB = view.findViewById(R.id.inputLayoutUserDOB)
        inputLayoutUserGender = view.findViewById(R.id.inputLayoutUserGender)
        inputLayoutUserCity = view.findViewById(R.id.inputLayoutUserCity)

        editTextUserName = view.findViewById(R.id.editTextUserName)
        editTextUserPhone = view.findViewById(R.id.editTextUserPhone)
        editTextUserEmail = view.findViewById(R.id.editTextUserEmail)
        editTextUserDOB = view.findViewById(R.id.editTextUserDOB)
        editTextUserGender = view.findViewById(R.id.editTextUserGender)
        editTextUserCity = view.findViewById(R.id.editTextUserCity)

        saveBtn = view.findViewById(R.id.saveBtn)

        editTextUserName.setText(dummyUserName)
        editTextUserPhone.setText(dummyUserPhone)
        editTextUserEmail.setText(dummyUserEmail)
        editTextUserDOB.setText(dummyUserDOB)
        editTextUserGender.setText(dummyUserGender)
        editTextUserCity.setText(dummyUserCity)

        editTextUserName.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val userName: String = s.toString().trim()
                if(userName.split(" ").filter{ it.isNotEmpty() }.size < 2){
                    inputLayoutUserName.error = "User's name should have more than 2 words"
                    inputLayoutUserName.isErrorEnabled = true
                }
                else{
                    inputLayoutUserName.error = ""
                    inputLayoutUserName.isErrorEnabled = false
                }

            }

            override fun afterTextChanged(s: Editable?) {
            }
        })

        editTextUserPhone.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val phoneNumber: String = s.toString().trim()
                val pattern: Pattern = Pattern.compile("^0\\d{9}\$")
                if(!pattern.matcher(phoneNumber).find()){
                    inputLayoutUserPhone.error = "Invalid phone number"
                    inputLayoutUserPhone.isErrorEnabled = true
                }
                else{
                    inputLayoutUserPhone.error = ""
                    inputLayoutUserPhone.isErrorEnabled = false
                }

            }

            override fun afterTextChanged(s: Editable?) {
            }
        })

        editTextUserDOB.setOnClickListener {
            val calendar: Calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            val datePickerDialog = DatePickerDialog(
                this.requireContext(),
                R.style.DialogTheme,
                DatePickerDialog.OnDateSetListener { _, selectedYear, selectedMonth, selectedDay ->
                    val formattedDay = if (selectedDay < 10) "0${selectedDay}" else "$selectedDay"
                    val formattedMonth = if (selectedMonth + 1 < 10) "0${selectedMonth + 1}" else "${selectedMonth + 1}"
                    val date = "$formattedDay/$formattedMonth/$selectedYear"
                    editTextUserDOB.setText(date)
                },
                year,
                month,
                day
            )

            datePickerDialog.show()
        }

        editTextUserGender.setOnClickListener {
            val options = listOf<String>("Male", "Female", "Other")

            val dialogView = layoutInflater.inflate(R.layout.alert_dialog_radio_select, null)
            val dialogTitle = dialogView.findViewById<TextView>(R.id.textViewAlertDialogTitle)
            val radioGroup = dialogView.findViewById<RadioGroup>(R.id.radioGroup)

            dialogTitle.text = "Choose a gender"

            val black_color = ContextCompat.getColor(this.requireContext(), R.color.black)
            val checked_color = ContextCompat.getColor(this.requireContext(), R.color.red)
            val unchecked_color = ContextCompat.getColor(this.requireContext(), R.color.greytext)

            val colorStateList = ColorStateList(
                arrayOf(intArrayOf(android.R.attr.state_checked), intArrayOf(-android.R.attr.state_checked)),
                intArrayOf(checked_color, unchecked_color)
            )

            for((index, option) in options.withIndex()){
                val radioButton = RadioButton(this.requireContext())
                radioButton.id = index
                radioButton.text = option
                radioButton.setTextColor(black_color)
                radioButton.layoutDirection = View.LAYOUT_DIRECTION_RTL
                radioButton.layoutParams = RadioGroup.LayoutParams(
                    RadioGroup.LayoutParams.MATCH_PARENT,
                    resources.getDimensionPixelSize(R.dimen.radio_button_height)
                )
                CompoundButtonCompat.setButtonTintList(
                    radioButton,
                    colorStateList
                )
                radioGroup.addView(radioButton)
                if(option == editTextUserGender.text.toString()){
                    radioButton.isChecked = true
                }
            }

            val dialogBuilder = AlertDialog.Builder(this.requireContext())
                .setView(dialogView)
                .setCancelable(true)

            val dialog = dialogBuilder.create()

            radioGroup.setOnCheckedChangeListener { group, checkedId ->
                val selectedRadioButton = dialogView.findViewById<RadioButton>(checkedId)
                editTextUserGender.setText(selectedRadioButton.text)

                dialog?.dismiss()
            }

            dialog.window?.setBackgroundDrawableResource(R.drawable.dialog_rounded_background)
            dialog.show()
        }

        editTextUserCity.setOnClickListener {
            val options = listOf<String>(
                "Hà Nội",
                "Hồ Chí Minh",
                "Hải Phòng",
                "Đà Nẵng",
                "Biên Hòa",
                "Nha Trang",
                "Vũng Tàu",
                "Cần Thơ",
                "Hải Dương",
                "Bắc Ninh",
                "Thái Nguyên",
                "Nam Định",
                "Quảng Ninh",
                "Hưng Yên",
                "Thanh Hóa",
                "Vinh",
                "Huế",
                "Buôn Ma Thuột",
                "Qui Nhơn",
                "Phan Thiết",
                "Vĩnh Long",
                "Long Xuyên",
                "Thủ Dầu Một",
                "Bắc Giang",
                "Thái Bình",
                "Bến Tre",
                "Quảng Ngãi",
                "Đồng Hới",
                "Bà Rịa",
                "Hạ Long",
                "Lạng Sơn",
                "Cao Lãnh",
                "Mỹ Tho",
                "Hòa Bình",
                "Lào Cai",
                "Đồng Xoài",
                "Sóc Trăng",
                "Kon Tum",
                "Bạc Liêu",
                "Hà Tĩnh",
                "Yên Bái",
                "Tây Ninh",
                "Phủ Lý",
                "Vị Thanh",
                "Ninh Bình",
                "Điện Biên Phủ",
                "Bắc Kạn",
                "Hà Tiên",
                "Tân An",
                "Đà Lạt",
                "Phan Rang-Tháp Chàm",
                "Sa Đéc",
                "Bảo Lộc",
                "Long Khánh",
                "Cẩm Phả",
                "Tam Kỳ",
                "La Gi",
                "Cửa Lò",
                "Lai Châu",
                "Hưng Nguyên",
                "Sầm Sơn",
                "Cao Bằng",
                "Hà Giang",
                "Đông Hà"
            )

            val dialogView = layoutInflater.inflate(R.layout.alert_dialog_radio_select, null)
            val dialogTitle = dialogView.findViewById<TextView>(R.id.textViewAlertDialogTitle)
            val radioGroup = dialogView.findViewById<RadioGroup>(R.id.radioGroup)

            dialogTitle.text = "Choose a city"

            val black_color = ContextCompat.getColor(this.requireContext(), R.color.black)
            val checked_color = ContextCompat.getColor(this.requireContext(), R.color.red)
            val unchecked_color = ContextCompat.getColor(this.requireContext(), R.color.greytext)

            val colorStateList = ColorStateList(
                arrayOf(intArrayOf(android.R.attr.state_checked), intArrayOf(-android.R.attr.state_checked)),
                intArrayOf(checked_color, unchecked_color)
            )

            for((index, option) in options.withIndex()){
                val radioButton = RadioButton(this.requireContext())
                radioButton.id = index
                radioButton.text = option
                radioButton.setTextColor(black_color)
                radioButton.layoutDirection = View.LAYOUT_DIRECTION_RTL
                radioButton.layoutParams = RadioGroup.LayoutParams(
                    RadioGroup.LayoutParams.MATCH_PARENT,
                    resources.getDimensionPixelSize(R.dimen.radio_button_height)
                )
                CompoundButtonCompat.setButtonTintList(
                    radioButton,
                    colorStateList
                )
                radioGroup.addView(radioButton)
                if(option == editTextUserCity.text.toString()){
                    radioButton.isChecked = true
                }
            }

            val dialogBuilder = AlertDialog.Builder(this.requireContext())
                .setView(dialogView)
                .setCancelable(true)

            val dialog = dialogBuilder.create()

            radioGroup.setOnCheckedChangeListener { group, checkedId ->
                val selectedRadioButton = dialogView.findViewById<RadioButton>(checkedId)
                editTextUserCity.setText(selectedRadioButton.text)

                dialog?.dismiss()
            }

            dialog.window?.setBackgroundDrawableResource(R.drawable.dialog_rounded_background)
            dialog.show()
        }

        saveBtn.setOnClickListener {
            if(inputLayoutUserName.isErrorEnabled || inputLayoutUserPhone.isErrorEnabled){
                Toast.makeText(this.requireContext(), "You haven't filled out the form yet", Toast.LENGTH_SHORT).show()
            }
            else{
                Toast.makeText(this.requireContext(), "It's a me Save Button!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}