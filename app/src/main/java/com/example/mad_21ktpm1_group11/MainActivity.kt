package com.example.mad_21ktpm1_group11

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.mad_21ktpm1_group11.fragments.BookByMovieFragment
import com.example.mad_21ktpm1_group11.fragments.HomeFragment
import com.example.mad_21ktpm1_group11.fragments.UserDashboardFragment

class MainActivity : AppCompatActivity() {
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var frameLayout: FrameLayout
    private lateinit var homeBtn: Button
    private lateinit var memberBtn: Button
    private lateinit var bookByMovieBtn: Button
    /*    private lateinit var catViewBtn: Button
        private lateinit var homeViewBtn: Button*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()
        addButtonClickEvent()

    }

    fun init(){
        drawerLayout = findViewById(R.id.drawerLayout)
        frameLayout = findViewById(R.id.frame_layout)
        homeBtn = findViewById(R.id.home_btn)
        memberBtn = findViewById(R.id.user_btn)
        bookByMovieBtn = findViewById(R.id.book_by_movie_btn)

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
    }

    fun openDrawer(){
        drawerLayout.openDrawer(GravityCompat.END)
    }

    fun getCurrentFragmentName(): String? {
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

    fun replaceFragment(fragment: Fragment, name: String? = null){
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

    fun addFragment(fragment: Fragment, name: String? = null){
        if(getCurrentFragmentName() != name)
            replaceFragment(fragment, name)
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
}