package com.lakshay.movierating.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.jaeger.library.StatusBarUtil
import com.lakshay.movierating.R
import com.lakshay.movierating.databinding.ActivityMainBinding
import com.lakshay.movierating.ui.movie.MovieFragment
import com.lakshay.movierating.ui.person.PersonFragment
import com.lakshay.movierating.ui.profile.ProfileActivity
import com.lakshay.movierating.ui.search.SearchActivity
import com.lakshay.movierating.ui.trending.TrendingFragment
import com.lakshay.movierating.ui.tv.TvFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null

    private val binding : ActivityMainBinding
    get() = _binding as ActivityMainBinding

    private var movieFragment = MovieFragment.newInstance()
    private var tvFragment = TvFragment.newInstance()
    private var personFragment = PersonFragment.newInstance()
    private var trendingFragment = TrendingFragment.newInstance()

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, MainActivity::class.java)
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(requireNotNull(binding.root))
        supportActionBar?.hide()
        StatusBarUtil.setColorNoTranslucent(this, ContextCompat.getColor(this, R.color.colorPrimaryDark))

        initializeBottomNavigation()
        setListener()
    }

    private fun initializeBottomNavigation(){
        replaceFragment(movieFragment, movieFragment.tag)

        binding.bottomNav.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.miMovie -> replaceFragment(movieFragment, movieFragment.tag)
                R.id.miTvShows -> replaceFragment(tvFragment, tvFragment.tag)
                R.id.miPerson -> replaceFragment(personFragment, personFragment.tag)
                R.id.miTrending -> replaceFragment(trendingFragment, trendingFragment.tag)
                else -> replaceFragment(movieFragment, movieFragment.tag)
            }
            true
        }
    }

    private fun replaceFragment(fragment: Fragment, tag: String?) {
        supportFragmentManager.let { manager ->
            manager.beginTransaction().let { transaction ->
                var fragmnt = manager.findFragmentByTag(tag)
                if (fragmnt == null) {
                    fragmnt = fragment
                    transaction.replace(R.id.flFragmentsContainer, fragmnt, tag)
                } else {
                    transaction.show(fragmnt)
                }
                transaction.commit()
            }
        }
    }

    private fun setListener(){
        binding.includeView.tvSearchBarHome.setOnClickListener{
            startSearchActivity()
        }

        binding.includeView.ivProfile.setOnClickListener {
            startProfileActivity()
        }
    }

    private fun startSearchActivity(){
        startActivity(SearchActivity.newIntent(this))
    }

    private fun startProfileActivity(){
        startActivity(ProfileActivity.newIntent(this))
    }
}