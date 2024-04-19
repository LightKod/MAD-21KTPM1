package com.example.mad_21ktpm1_group11

import android.opengl.Visibility
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.mad_21ktpm1_group11.fragments.BookByMovieFragment
import com.example.mad_21ktpm1_group11.fragments.HomeFragment
import com.example.mad_21ktpm1_group11.fragments.LoginFragment
import com.example.mad_21ktpm1_group11.fragments.MapFragment
import com.example.mad_21ktpm1_group11.fragments.NewsAndPromosFragment
import com.example.mad_21ktpm1_group11.fragments.PaymentPreviewFragment
import com.example.mad_21ktpm1_group11.fragments.UserDashboardFragment
import com.example.mad_21ktpm1_group11.fragments.VoucherRedeemFragment
import jp.wasabeef.glide.transformations.BlurTransformation

class MainActivity : AppCompatActivity() {
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var frameLayout: FrameLayout
    private lateinit var homeBtn: Button
    private lateinit var memberBtn: Button
    private lateinit var bookByMovieBtn: Button
    private lateinit var newsBtn: Button
    private lateinit var mapBtn: Button
    private lateinit var redeemBtn: Button

    private lateinit var ticketBtn: Button

    var isLoggedIn: Boolean = true
    private lateinit var loginBtn: TextView
    private lateinit var userName: TextView
    private lateinit var memberCode: TextView
    private lateinit var userCode: CardView
    private lateinit var logoutBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()
        addButtonClickEvent()
        toggleNavbarUser()
    }

    fun init(){
        drawerLayout = findViewById(R.id.drawerLayout)
        frameLayout = findViewById(R.id.frame_layout)
        homeBtn = findViewById(R.id.home_btn)
        memberBtn = findViewById(R.id.user_btn)
        bookByMovieBtn = findViewById(R.id.book_by_movie_btn)
        newsBtn = findViewById(R.id.news_btn)
        mapBtn = findViewById(R.id.map_btn)
        redeemBtn = findViewById(R.id.redeem_btn)

        ticketBtn = findViewById(R.id.ticket_btn)

        loginBtn = findViewById(R.id.login_btn)
        userName = findViewById(R.id.user_name)
        memberCode = findViewById(R.id.member_code)
        userCode = findViewById(R.id.user_code)
        logoutBtn = findViewById(R.id.logout_btn)

        supportFragmentManager.addOnBackStackChangedListener {
            Log.i("meo", "${supportFragmentManager.backStackEntryCount}")
        }

        replaceFragment(HomeFragment(), "home")
    }

    fun addButtonClickEvent(){
        homeBtn.setOnClickListener {
            addFragment(HomeFragment(), "home")
        }

        memberBtn.setOnClickListener {
            addFragment(UserDashboardFragment(), "member")
        }

        bookByMovieBtn.setOnClickListener {
            addFragment(BookByMovieFragment(), "book_by_movie")
        }

        newsBtn.setOnClickListener {
            addFragment(NewsAndPromosFragment(), "news")
        }

        mapBtn.setOnClickListener {
            addFragment(MapFragment(), "map")
        }

        redeemBtn.setOnClickListener {
            addFragment(VoucherRedeemFragment(), "redeem")
        }

        ticketBtn.setOnClickListener {
            addFragment(PaymentPreviewFragment(), "payment")
        }

        loginBtn.setOnClickListener {
            addFragment(LoginFragment(), "login")
        }

        logoutBtn.setOnClickListener {
            isLoggedIn = false
            toggleNavbarUser()
        }
    }

    fun openDrawer(){
        drawerLayout.openDrawer(GravityCompat.END)
    }

    private fun getCurrentFragmentName(): String? {
        val fragmentManager = supportFragmentManager
        val backStackCount = fragmentManager.backStackEntryCount

        if (backStackCount > 0) {
            // Get the name of the top entry in the back stack
            val topEntry = fragmentManager.getBackStackEntryAt(backStackCount - 1)
            return topEntry.name
        }

        // No entry in the back stack
        return null
    }

    private fun replaceFragment(fragment: Fragment, name: String? = null){
        val fragmentManager: FragmentManager = supportFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()

        fragmentTransaction.setCustomAnimations(R.anim.fade_in, R.anim.fade_out, R.anim.fade_in, R.anim.slide_out)
        fragmentTransaction.replace(frameLayout.id, fragment)
        fragmentTransaction.addToBackStack(name)

        fragmentTransaction.commit()

        if(drawerLayout.isDrawerOpen(GravityCompat.END)){
            drawerLayout.closeDrawer(GravityCompat.END)
        }
    }

    private fun clearAllFragments(){
        val fragmentManager: FragmentManager = supportFragmentManager
        fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
    }


    fun addFragment(fragment: Fragment, name: String? = null){
        val fragmentName = getCurrentFragmentName()
        if(fragmentName != name) {
            if(name == "home"){
                clearAllFragments()
                replaceFragment(HomeFragment(), "home")
            }
            else{
                replaceFragment(fragment, name)
            }
        }
    }

    fun goBack(){
        val fragmentManager: FragmentManager = supportFragmentManager
        if(fragmentManager.backStackEntryCount > 1){
            fragmentManager.popBackStack()
        }
        else{
            finish()
        }
    }

    override fun onBackPressed() {
        val fragmentManager: FragmentManager = supportFragmentManager
        if(fragmentManager.backStackEntryCount > 1){
            fragmentManager.popBackStack()
        }
        else if(fragmentManager.backStackEntryCount == 1){
            finish()
        }
        else{
            super.onBackPressed()
        }

    }

    fun toggleNavbarUser(){
        loginBtn.visibility = if(isLoggedIn) View.GONE else View.VISIBLE
        userName.visibility = if(isLoggedIn) View.VISIBLE else View.GONE
        memberCode.visibility = if(isLoggedIn) View.VISIBLE else View.GONE
        userCode.visibility = if(isLoggedIn) View.VISIBLE else View.GONE
        logoutBtn.visibility = if(isLoggedIn) View.VISIBLE else View.GONE
    }
}