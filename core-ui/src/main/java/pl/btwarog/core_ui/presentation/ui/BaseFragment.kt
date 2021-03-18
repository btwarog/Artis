package pl.btwarog.core_ui.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<VB : ViewBinding>(
	@LayoutRes layoutId: Int
) : Fragment(layoutId) {

	protected lateinit var binding: VB

	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		binding = getBinding(inflater, container)
		return binding.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		initView(savedInstanceState)
	}

	protected abstract fun getBinding(inflater: LayoutInflater, container: ViewGroup?): VB
	protected abstract fun initView(savedInstanceState: Bundle?)
}