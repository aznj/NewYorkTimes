package dev.aznj.newyorktimes

import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<T : ViewBinding> : Fragment() {

    protected var _binding: T? = null
    protected val binding
        get() = _binding!!

    abstract fun setupViewModel()

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}