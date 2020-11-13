package com.bouraoui.startwars.ui.fragment.splash

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bouraoui.startwars.R
import com.bouraoui.startwars.databinding.FragmentSplashScreenBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


/**
 * A simple [Fragment] subclass.
 * Use the [SplashScreenFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SplashScreenFragment : Fragment() {

    private  var binding : FragmentSplashScreenBinding?  =null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=  FragmentSplashScreenBinding.inflate(layoutInflater)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initContent()
    }


    private fun initContent() {
        GlobalScope.launch {
            delay(3000L)
            requireActivity().runOnUiThread {
                binding!!.logoIV.visibility = View.VISIBLE
                binding!!.thinkLogoIV.visibility = View.VISIBLE
                findNavController().popBackStack(R.id.splashScreenFragment, true)
                findNavController().navigate(R.id.moviesListFragment)
            }

        }
    }

}