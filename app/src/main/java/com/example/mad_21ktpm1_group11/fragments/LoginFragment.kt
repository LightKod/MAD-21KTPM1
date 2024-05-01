package com.example.mad_21ktpm1_group11.fragments

import android.content.Context
import android.graphics.Paint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.mad_21ktpm1_group11.MainActivity
import com.example.mad_21ktpm1_group11.R
import com.example.mad_21ktpm1_group11.api.AuthService
import com.example.mad_21ktpm1_group11.api.RetrofitClient
import com.example.mad_21ktpm1_group11.models.AuthResponse
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import okhttp3.ResponseBody

class LoginFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private lateinit var editTextUserEmail :EditText
    private  lateinit var editTextPassword :EditText
    private lateinit var buttonLogin :Button

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
    }
    fun login(email: String, password: String) {
        // Tạo một đối tượng JsonObject chứa email và password
        val requestJson = JsonObject().apply {
            addProperty("email", email)
            addProperty("password", password)
        }

        // Gọi phương thức authenticate từ AuthService
        val authService = RetrofitClient.instance.create(AuthService::class.java)
        val call = authService.authenticate(requestJson)
        call.enqueue(object : Callback<AuthResponse> {
            override fun onResponse(call: Call<AuthResponse>, response: Response<AuthResponse>) {
                if (response.isSuccessful) {
                    val responseObject = response.body()

                    val token = responseObject?.token
                    if (!token.isNullOrEmpty()) {
                        // Token đã được nhận về thành công
                        Log.d("Login", "Received token: $token")

                        // Thực hiện các hành động tiếp theo sau khi đăng nhập thành công
                        val sharedPref = this@LoginFragment.requireContext().getSharedPreferences("auth", Context.MODE_PRIVATE)
                        val editor = sharedPref.edit()
                        editor.putString("token", "Bearer $token")
                        editor.apply()

                        (this@LoginFragment.activity as? MainActivity)?.toggleNavbarUser()
                        (this@LoginFragment.activity as? MainActivity)?.addFragment(HomeFragment(), "home")
                    } else {
                        Log.e("Login", "Token is null or empty")
                    }
                } else {
                    // Xử lý khi có lỗi từ server
                    // Log.e("Login", "Error: ${response.code()} - ${response.message()}")
                    Toast.makeText(requireContext(), "Error: ${response.code()} - ${ response.errorBody()?.string() }", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<AuthResponse>, t: Throwable) {
                // Xử lý khi gặp lỗi kết nối
                // Log.e("Login", "Failed to connect: ${t.message}")
                Toast.makeText(requireContext(), "Error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_login, container, false)
        val forgotPasswordTextView = view.findViewById<TextView>(R.id.forgotPasswordTextView)
        forgotPasswordTextView.paintFlags = forgotPasswordTextView.paintFlags or Paint.UNDERLINE_TEXT_FLAG
        val btnRegister = view.findViewById<Button>(R.id.button2)
        btnRegister.setOnClickListener {
            (this.activity as MainActivity).addFragment(PaymentPreviewFragment(),"register")
        }
        editTextUserEmail = view.findViewById(R.id.editTextUserEmail)
        editTextPassword = view.findViewById(R.id.editTextPassword)
        buttonLogin = view.findViewById(R.id.button)
        buttonLogin.setOnClickListener {
            val email = editTextUserEmail.text.toString()
            val password = editTextPassword.text.toString()
            login(email, password)
        }
        return view
    }



}