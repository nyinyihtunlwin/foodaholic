package com.nyinyihtunlwin.projects.foodaholic.fragments


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.nyinyihtunlwin.projects.foodaholic.R
import com.nyinyihtunlwin.projects.foodaholic.databinding.FragmentLatestBinding
import com.nyinyihtunlwin.projects.foodaholic.mvvm.models.MealModel
import com.nyinyihtunlwin.projects.foodaholic.mvvm.viewmodels.LatestViewModel
import com.nyinyihtunlwin.projects.foodaholic.mvvm.viewmodels.LatestViewModelFactory
import com.nyinyihtunlwin.projects.foodaholic.mvvm.views.LatestView
import com.nyinyihtunlwin.projects.sharedmodule.ui.BaseFragment
import java.lang.ref.WeakReference

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class LatestFragment : BaseFragment(), LatestView {


    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val inflatedView =
            DataBindingUtil.inflate<FragmentLatestBinding>(inflater, R.layout.fragment_latest, container, false)
        inflatedView.viewModel = ViewModelProviders.of(
            this@LatestFragment,
            LatestViewModelFactory(WeakReference(activity!!.applicationContext), this)
        ).get(LatestViewModel::class.java)

        inflatedView.viewModel!!.startLoadingLatestMeals()

        inflatedView.viewModel!!.mResponseLD.observe(this, Observer {
            inflatedView.viewModel!!.setNewData(it as MutableList)
        })
        inflatedView.viewModel!!.mErrorLD.observe(this, Observer {
            Toast.makeText(activity!!.applicationContext, it.toString(), Toast.LENGTH_SHORT).show()
        })
        return inflatedView.root
    }

    override fun onDataLoaded(mealList: List<MealModel>) {
    }

    override fun onError(message: String) {
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            LatestFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
