package com.example.mad_21ktpm1_group11.fragments

import android.app.AlertDialog
import android.content.res.ColorStateList
import android.net.Uri
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.core.widget.CompoundButtonCompat
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.mad_21ktpm1_group11.MainActivity
import com.example.mad_21ktpm1_group11.R
import com.example.mad_21ktpm1_group11.api.MovieApi
import com.example.mad_21ktpm1_group11.api.RetrofitClient
import com.example.mad_21ktpm1_group11.models.Movie
import com.example.mad_21ktpm1_group11.models.Person
import com.google.android.material.textfield.TextInputLayout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieManagementDetailFragment : Fragment() {
    private lateinit var mode: String
    private var id: Int = 0

    private lateinit var backBtn: ImageButton
    private lateinit var menuBtn: ImageButton

    private lateinit var textViewHeaderTitle: TextView

    private lateinit var cardView: CardView
    private lateinit var imageViewMoviePosterPreview: ImageView

    private lateinit var inputLayoutMovieName: TextInputLayout
    private lateinit var inputLayoutMovieDuration: TextInputLayout
    private lateinit var inputLayoutMovieRating: TextInputLayout

    private lateinit var editTextMovieName: EditText
    private lateinit var editTextMovieDuration: EditText
    private lateinit var editTextMovieRating: EditText
    private lateinit var editTextMovieBanner: EditText
    private lateinit var editTextMovieGenres: EditText
    private lateinit var editTextMovieDirector: EditText
    private lateinit var editTextMovieActors: EditText

    private lateinit var saveBtn: Button
    private lateinit var deleteBtn: Button

    private lateinit var movie: Movie
    var chosenUri: Uri? = null
    var selectedDirector: Person? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie_management_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init(view)

        arguments?.takeIf { it.containsKey("type") }?.apply {
            mode = getString("type")!!
            if(mode == "edit"){
                textViewHeaderTitle.text = "Update a movie"
                saveBtn.text = "Update"
                deleteBtn.visibility = View.VISIBLE

                id = getInt("id")
                Log.i("meo", "$id")
                fetchData()
            }
            else{
                textViewHeaderTitle.text = "Add a new movie"
                saveBtn.text = "Add"
                deleteBtn.visibility = View.GONE
            }
        }
    }

    private fun fetchData(){
        val movieService = RetrofitClient.instance.create(MovieApi::class.java)
        val call = movieService.getMovieByID(id)

        call.enqueue(object : Callback<Movie> {
            override fun onResponse(call: Call<Movie>, response: Response<Movie>) {
                if (response.isSuccessful) {
                    // Handle successful response
                    movie = response.body()!!
                    setData()
                } else {
                    val errorMessage = response.message()
                    Log.i("API", errorMessage)
                    Log.i("API", "GET FAILED")
                }
            }

            override fun onFailure(call: Call<Movie>, t: Throwable) {
                Log.i("API", t.message!!)
            }
        })
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

        textViewHeaderTitle = view.findViewById(R.id.textViewHeaderTitle)

        cardView = view.findViewById(R.id.cardView)
        imageViewMoviePosterPreview = view.findViewById(R.id.imageViewMoviePosterPreview)

        cardView.setOnClickListener {
            pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
        }

        inputLayoutMovieName = view.findViewById(R.id.inputLayoutMovieName)
        inputLayoutMovieDuration = view.findViewById(R.id.inputLayoutMovieDuration)
        inputLayoutMovieRating = view.findViewById(R.id.inputLayoutMovieRating)

        editTextMovieName = view.findViewById(R.id.editTextMovieName)
        editTextMovieDuration = view.findViewById(R.id.editTextMovieDuration)
        editTextMovieRating = view.findViewById(R.id.editTextMovieRating)
        editTextMovieBanner = view.findViewById(R.id.editTextMovieBanner)
        editTextMovieGenres = view.findViewById(R.id.editTextMovieGenres)
        editTextMovieDirector = view.findViewById(R.id.editTextMovieDirector)
        editTextMovieActors = view.findViewById(R.id.editTextMovieActors)

        editTextMovieName.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val movieName: String = s.toString().trim()
                if(movieName.isEmpty()){
                    inputLayoutMovieName.error = "This field cannot be empty"
                    inputLayoutMovieName.isErrorEnabled = true
                }
                else{
                    inputLayoutMovieName.error = ""
                    inputLayoutMovieName.isErrorEnabled = false
                }

            }

            override fun afterTextChanged(s: Editable?) {
            }
        })

        editTextMovieDuration.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val movieDuration: String = s.toString().trim()
                if(movieDuration.isEmpty()){
                    inputLayoutMovieDuration.error = "This field cannot be empty"
                    inputLayoutMovieDuration.isErrorEnabled = true
                }
                else{
                    inputLayoutMovieDuration.error = ""
                    inputLayoutMovieDuration.isErrorEnabled = false
                }

            }

            override fun afterTextChanged(s: Editable?) {
            }
        })

        editTextMovieRating.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val movieRating: String = s.toString().trim()
                if(movieRating.isEmpty()){
                    inputLayoutMovieRating.error = "This field cannot be empty"
                    inputLayoutMovieRating.isErrorEnabled = true
                }
                else{
                    inputLayoutMovieRating.error = ""
                    inputLayoutMovieRating.isErrorEnabled = false
                }

            }

            override fun afterTextChanged(s: Editable?) {
            }
        })

        editTextMovieGenres.setOnClickListener {
            val options = listOf<String>("a", "b", "c", "d", "e", "f", "g", "h", "a", "b", "c", "d", "e", "f", "g", "h", "a", "b", "c", "d", "e", "f", "g", "h")

            val dialogView = layoutInflater.inflate(R.layout.alert_dialog_checkbox_select, null)
            val dialogTitle = dialogView.findViewById<TextView>(R.id.textViewAlertDialogTitle)
            val linearLayout = dialogView.findViewById<LinearLayout>(R.id.linearLayout)
            var dialogSaveBtn = dialogView.findViewById<Button>(R.id.saveBtn)

            dialogTitle.text = "Choose genre(s)"

            val black_color = ContextCompat.getColor(this.requireContext(), R.color.black)
            val checked_color = ContextCompat.getColor(this.requireContext(), R.color.red)
            val unchecked_color = ContextCompat.getColor(this.requireContext(), R.color.greytext)

            val colorStateList = ColorStateList(
                arrayOf(intArrayOf(android.R.attr.state_checked), intArrayOf(-android.R.attr.state_checked)),
                intArrayOf(checked_color, unchecked_color)
            )

            for((index, option) in options.withIndex()){
                val checkBox = CheckBox(this.requireContext())
                checkBox.id = index
                checkBox.text = option
                checkBox.setTextColor(black_color)
                checkBox.layoutDirection = View.LAYOUT_DIRECTION_RTL
                checkBox.layoutParams = RadioGroup.LayoutParams(
                    RadioGroup.LayoutParams.MATCH_PARENT,
                    resources.getDimensionPixelSize(R.dimen.radio_button_height)
                )
                CompoundButtonCompat.setButtonTintList(
                    checkBox,
                    colorStateList
                )
                linearLayout.addView(checkBox)
            }

            val dialogBuilder = AlertDialog.Builder(this.requireContext())
                .setView(dialogView)
                .setCancelable(true)

            val dialog = dialogBuilder.create()

            dialogSaveBtn.setOnClickListener {
                val selectedItems = mutableListOf<String>()
                for (i in 0..<linearLayout.childCount){
                    val checkBox = linearLayout.getChildAt(i) as CheckBox
                    if(checkBox.isChecked){
                        selectedItems.add(options[checkBox.id])
                    }
                }
                editTextMovieGenres.setText(selectedItems.joinToString("/"))

                dialog?.dismiss()
            }

            dialog.window?.setBackgroundDrawableResource(R.drawable.dialog_rounded_background)
            dialog.show()
        }

        editTextMovieDirector.setOnClickListener {
            val options = listOf<Person>(Person(1, "Meo", "", ""), Person(2, "Woof", "", ""))

            val dialogView = layoutInflater.inflate(R.layout.alert_dialog_radio_select, null)
            val dialogTitle = dialogView.findViewById<TextView>(R.id.textViewAlertDialogTitle)
            val radioGroup = dialogView.findViewById<RadioGroup>(R.id.radioGroup)

            dialogTitle.text = "Choose a director"

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
                radioButton.text = option.name
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
                if(option.name == editTextMovieDirector.text.toString()){
                    radioButton.isChecked = true
                }
            }

            val dialogBuilder = AlertDialog.Builder(this.requireContext())
                .setView(dialogView)
                .setCancelable(true)

            val dialog = dialogBuilder.create()

            radioGroup.setOnCheckedChangeListener { group, checkedId ->
                val selectedRadioButton = dialogView.findViewById<RadioButton>(checkedId)
                selectedDirector = options[selectedRadioButton.id]
                editTextMovieDirector.setText(selectedRadioButton.text)

                dialog?.dismiss()
            }

            dialog.window?.setBackgroundDrawableResource(R.drawable.dialog_rounded_background)
            dialog.show()
        }

        editTextMovieActors.setOnClickListener {
            val options = listOf<String>("a", "b", "c", "d", "e", "f", "g", "h", "a", "b", "c", "d", "e", "f", "g", "h", "a", "b", "c", "d", "e", "f", "g", "h")

            val dialogView = layoutInflater.inflate(R.layout.alert_dialog_checkbox_select, null)
            val dialogTitle = dialogView.findViewById<TextView>(R.id.textViewAlertDialogTitle)
            val linearLayout = dialogView.findViewById<LinearLayout>(R.id.linearLayout)
            var dialogSaveBtn = dialogView.findViewById<Button>(R.id.saveBtn)

            dialogTitle.text = "Choose actor(s)"

            val black_color = ContextCompat.getColor(this.requireContext(), R.color.black)
            val checked_color = ContextCompat.getColor(this.requireContext(), R.color.red)
            val unchecked_color = ContextCompat.getColor(this.requireContext(), R.color.greytext)

            val colorStateList = ColorStateList(
                arrayOf(intArrayOf(android.R.attr.state_checked), intArrayOf(-android.R.attr.state_checked)),
                intArrayOf(checked_color, unchecked_color)
            )

            for((index, option) in options.withIndex()){
                val checkBox = CheckBox(this.requireContext())
                checkBox.id = index
                checkBox.text = option
                checkBox.setTextColor(black_color)
                checkBox.layoutDirection = View.LAYOUT_DIRECTION_RTL
                checkBox.layoutParams = RadioGroup.LayoutParams(
                    RadioGroup.LayoutParams.MATCH_PARENT,
                    resources.getDimensionPixelSize(R.dimen.radio_button_height)
                )
                CompoundButtonCompat.setButtonTintList(
                    checkBox,
                    colorStateList
                )
                linearLayout.addView(checkBox)
            }

            val dialogBuilder = AlertDialog.Builder(this.requireContext())
                .setView(dialogView)
                .setCancelable(true)

            val dialog = dialogBuilder.create()

            dialogSaveBtn.setOnClickListener {
                val selectedItems = mutableListOf<String>()
                for (i in 0..<linearLayout.childCount){
                    val checkBox = linearLayout.getChildAt(i) as CheckBox
                    if(checkBox.isChecked){
                        selectedItems.add(options[checkBox.id])
                    }
                }
                editTextMovieActors.setText(selectedItems.joinToString("/"))

                dialog?.dismiss()
            }

            dialog.window?.setBackgroundDrawableResource(R.drawable.dialog_rounded_background)
            dialog.show()
        }

        saveBtn = view.findViewById(R.id.saveBtn)

        saveBtn.setOnClickListener {
            if(inputLayoutMovieName.isErrorEnabled || inputLayoutMovieDuration.isErrorEnabled || inputLayoutMovieRating.isErrorEnabled
                || editTextMovieName.text.isEmpty() || editTextMovieDuration.text.isEmpty() || editTextMovieRating.text.isEmpty()
                || selectedDirector == null){
                Toast.makeText(this.requireContext(), "You haven't completed the form yet", Toast.LENGTH_SHORT).show()
            }
            else{
                Toast.makeText(this.requireContext(), "Ok", Toast.LENGTH_SHORT).show()
            }
        }

        deleteBtn = view.findViewById(R.id.deleteBtn)
    }

    val pickMedia = registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
        if (uri != null) {
            chosenUri = uri
            Glide.with(this.requireContext()).load(uri).into(imageViewMoviePosterPreview)
            if(imageViewMoviePosterPreview.visibility == View.GONE){
                imageViewMoviePosterPreview.visibility = View.VISIBLE
            }
        }
    }

    private fun setData(){
        Glide.with(this.requireContext()).load("https://image.tmdb.org/t/p/original" + movie.poster).into(imageViewMoviePosterPreview)
        if(imageViewMoviePosterPreview.visibility == View.GONE){
            imageViewMoviePosterPreview.visibility = View.VISIBLE
        }
        editTextMovieName.setText(movie.name)
        editTextMovieDuration.setText(movie.duration.toString())
    }
}