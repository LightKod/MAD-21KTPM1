package com.example.mad_21ktpm1_group11.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.mad_21ktpm1_group11.MainActivity
import com.example.mad_21ktpm1_group11.R
import com.example.mad_21ktpm1_group11.adapters.ImageAdapter
import com.example.mad_21ktpm1_group11.adapters.ImageURLAdapter
import com.example.mad_21ktpm1_group11.adapters.RecyclerViewNewsAdapter
import com.example.mad_21ktpm1_group11.adapters.SliderMenuAdapter
import com.example.mad_21ktpm1_group11.decorators.SpacingItemDecorator
import com.example.mad_21ktpm1_group11.models.News
import jp.wasabeef.glide.transformations.BlurTransformation
import kotlin.math.abs

class HomeFragment : Fragment() {
    private lateinit var menuBtn: ImageButton

    private lateinit var imageViewDashboardBackground: ImageView

    private lateinit var viewPagerAdvertisement: ViewPager2
    private lateinit var viewPagerMovieList: ViewPager2
    private lateinit var viewPagerPromotion: ViewPager2
    private lateinit var viewPagerSliderMenu: ViewPager2
    private lateinit var viewPagerSliderMenuIndicator: LinearLayout
    private lateinit var dotsImage: Array<ImageView>

    private lateinit var recyclerViewNews: RecyclerView

    private lateinit var textViewMovieName: TextView
    private lateinit var textViewMovieInfo: TextView

    private lateinit var movieImageList: ArrayList<Int>
    private lateinit var advertisementImageList: ArrayList<String>
    private lateinit var newsList: ArrayList<News>

    private lateinit var viewPagerMovieListAdapter: ImageAdapter
    private lateinit var viewPagerAdvertisementAdapter: ImageURLAdapter
    private lateinit var recyclerViewNewsAdapter: RecyclerViewNewsAdapter
    private lateinit var viewPagerSliderMenuAdapter: SliderMenuAdapter

    private lateinit var imageViewUserIcon: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        init(view)
        initViewPagers()
        handleViewPagerEvents()

        initRecyclerViews()

        return view
    }

    private fun init(view: View){
        imageViewDashboardBackground = view.findViewById(R.id.imageViewDashboardBackground)

        menuBtn = view.findViewById(R.id.menuBtn)
        viewPagerAdvertisement = view.findViewById(R.id.viewPagerAdvertisement)
        viewPagerMovieList = view.findViewById(R.id.viewPagerMovieList)
        viewPagerPromotion = view.findViewById(R.id.viewPagerPromotion)
        viewPagerSliderMenu = view.findViewById(R.id.viewPagerSliderMenu)
        viewPagerSliderMenuIndicator = view.findViewById(R.id.viewPagerSliderMenuIndicator)

        recyclerViewNews = view.findViewById(R.id.recyclerViewNews)

        textViewMovieName = view.findViewById(R.id.textViewMovieName)
        textViewMovieInfo = view.findViewById(R.id.textViewMovieInfo)

        movieImageList = ArrayList()
        movieImageList.add(R.drawable.one)
        movieImageList.add(R.drawable.two)
        movieImageList.add(R.drawable.three)
        movieImageList.add(R.drawable.four)

        advertisementImageList = ArrayList()
        advertisementImageList.add("https://iguov8nhvyobj.vcdn.cloud/media/banner/cache/1/b58515f018eb873dafa430b6f9ae0c1e/i/m/imgpsh_fullsize_anim_2.png")
        advertisementImageList.add("https://iguov8nhvyobj.vcdn.cloud/media/banner/cache/1/b58515f018eb873dafa430b6f9ae0c1e/9/8/980x448_4_-min_2.jpg")
        advertisementImageList.add("https://iguov8nhvyobj.vcdn.cloud/media/banner/cache/1/b58515f018eb873dafa430b6f9ae0c1e/9/8/980x448_-min.png")
        advertisementImageList.add("https://iguov8nhvyobj.vcdn.cloud/media/banner/cache/1/b58515f018eb873dafa430b6f9ae0c1e/9/8/980x448_-min_11.jpg")
        advertisementImageList.add("https://iguov8nhvyobj.vcdn.cloud/media/banner/cache/1/b58515f018eb873dafa430b6f9ae0c1e/9/8/980x448_1_-min_4.png")
        advertisementImageList.add("https://iguov8nhvyobj.vcdn.cloud/media/banner/cache/1/b58515f018eb873dafa430b6f9ae0c1e/w/e/web_rolling_banner_980_x_448_px-min.png")

        newsList = ArrayList()
        newsList.add(News("LONG LONG LONG LONG LONG LONG LONG LONG TITLE", "https://iguov8nhvyobj.vcdn.cloud/media/wysiwyg/2024/032024/350_X_495.png"))
        newsList.add(News("LONG LONG LONG LONG LONG LONG LONG LONG TITLE", "https://iguov8nhvyobj.vcdn.cloud/media/wysiwyg/2024/032024/B_p_Cola_350x495.jpg"))
        newsList.add(News("SHORT TITLE", "https://iguov8nhvyobj.vcdn.cloud/media/wysiwyg/2024/032024/350_X_495.png"))
        newsList.add(News("SHORT TITLE", "https://iguov8nhvyobj.vcdn.cloud/media/wysiwyg/2024/032024/B_p_Cola_350x495.jpg"))
        newsList.add(News("LONG LONG LONG LONG LONG LONG LONG LONG TITLE", "https://iguov8nhvyobj.vcdn.cloud/media/wysiwyg/2024/032024/350_X_495.png"))


        imageViewUserIcon = view.findViewById(R.id.imageViewUserIcon)

        menuBtn.setOnClickListener {
            (this.activity as? MainActivity)?.openDrawer()
        }

        imageViewUserIcon.setOnClickListener {
            (this.activity as? MainActivity)?.addFragment(UserDashboardFragment(), "member")
        }
    }

    private fun initViewPagers() {
        viewPagerMovieListAdapter = ImageAdapter(this, movieImageList)

        viewPagerMovieList.adapter = viewPagerMovieListAdapter
        viewPagerMovieList.offscreenPageLimit = 2
        viewPagerMovieList.clipToPadding = false
        viewPagerMovieList.clipChildren = false
        viewPagerMovieList.setCurrentItem(1, false)

        textViewMovieName.setText(movieImageList[1])
        textViewMovieInfo.setText(movieImageList[1])
        Glide.with(this)
            .load(movieImageList[1])
            .apply(RequestOptions.bitmapTransform(BlurTransformation(25, 3)))
            .into(imageViewDashboardBackground)


        val movieListTransformer = CompositePageTransformer()
        movieListTransformer.addTransformer { page, position ->
            val absPosition = abs(position)
            page.rotationY = -15 * position
            val scaleFactor = 0.85f + (1 - absPosition) * 0.15f
            page.scaleY = scaleFactor
            page.scaleX = scaleFactor
            page.elevation = 0f
        }
        viewPagerMovieList.setPageTransformer(movieListTransformer)

        viewPagerAdvertisementAdapter = ImageURLAdapter(this, advertisementImageList)

        viewPagerAdvertisement.adapter = viewPagerAdvertisementAdapter
        viewPagerAdvertisement.offscreenPageLimit = 2
        viewPagerAdvertisement.clipToPadding = false
        viewPagerAdvertisement.clipChildren = false
        viewPagerAdvertisement.setCurrentItem(1, false)

        val advertisementTransformer = CompositePageTransformer()
        advertisementTransformer.addTransformer(MarginPageTransformer(30))
        viewPagerAdvertisement.setPageTransformer(advertisementTransformer)

        viewPagerSliderMenuAdapter = SliderMenuAdapter(childFragmentManager, lifecycle)

        viewPagerSliderMenuAdapter.addFragment(SliderMenuFirstFragment())
        viewPagerSliderMenuAdapter.addFragment(SliderMenuSecondFragment())

        viewPagerSliderMenu.adapter = viewPagerSliderMenuAdapter

        dotsImage = Array(viewPagerSliderMenuAdapter.itemCount) {ImageView(this.requireContext())}

        dotsImage.forEach {
            it.setImageResource(R.drawable.dot_unselected)
            viewPagerSliderMenuIndicator.addView(it, LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            ).apply {
                setMargins(6,0,6,0)
            })
        }
        dotsImage[0].setImageResource(R.drawable.dot_selected)
    }

    private fun handleViewPagerEvents() {
        viewPagerMovieList.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)

                textViewMovieName.setText(movieImageList[position])
                textViewMovieInfo.setText(movieImageList[position])
                Glide.with(this@HomeFragment)
                    .load(movieImageList[position])
                    .placeholder(R.drawable.black)
                    .apply(RequestOptions.bitmapTransform(BlurTransformation(25, 3)))
                    .into(imageViewDashboardBackground)
            }
        })

        viewPagerMovieListAdapter.onItemClick = { name ->
            Toast.makeText(context, "Item clicked: " + name, Toast.LENGTH_SHORT).show()
        }

        viewPagerSliderMenu.registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                dotsImage.mapIndexed{ index, imageView ->
                    if(position == index){
                        imageView.setImageResource(R.drawable.dot_selected)
                    }
                    else{
                        imageView.setImageResource(R.drawable.dot_unselected)
                    }
                }
                super.onPageSelected(position)
            }
        })

        addInfiniteScroll(viewPagerMovieList)
        addInfiniteScroll(viewPagerAdvertisement)
    }

    private fun addInfiniteScroll(viewPager2: ViewPager2){
        val recyclerView = viewPager2.getChildAt(0) as RecyclerView
        val layoutManager = recyclerView.layoutManager as LinearLayoutManager
        val itemCount = viewPager2.adapter?.itemCount ?: 0

        recyclerView.addOnScrollListener(object: RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val firstItemVisible = layoutManager.findFirstVisibleItemPosition()
                val lastItemVisible = layoutManager.findLastVisibleItemPosition()
                if(firstItemVisible == (itemCount - 1) && dx > 0){
                    recyclerView.scrollToPosition(1)
                }
                else if(lastItemVisible == 0 && dx < 0){
                    recyclerView.scrollToPosition(itemCount - 2)
                }
            }
        })
    }

    private fun initRecyclerViews(){
        recyclerViewNewsAdapter = RecyclerViewNewsAdapter(this, newsList)
        recyclerViewNews.adapter = recyclerViewNewsAdapter
        recyclerViewNews.layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false)
        recyclerViewNews.addItemDecoration(SpacingItemDecorator(this.requireContext(), 50))
    }
}