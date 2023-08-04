package com.gachon.garamgaebi2.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.gachon.garamgaebi2.BR
import com.gachon.garamgaebi2.R
import com.gachon.garamgaebi2.base.BaseBindingFragment
import com.gachon.garamgaebi2.base.GaramgaebiApplication
import com.gachon.garamgaebi2.base.OrderBottomDialogFragment
import com.gachon.garamgaebi2.databinding.FragmentRegister2AuthenticationBinding
import com.gachon.garamgaebi2.viewModel.RegisterViewModel



class Register2AuthenticationFragment : BaseBindingFragment<FragmentRegister2AuthenticationBinding>(R.layout.fragment_register2_authentication) {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel = ViewModelProvider(this)[RegisterViewModel::class.java]
        binding.setVariable(BR.viewModel,viewModel)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        GaramgaebiApplication.registerProcess = 2

        binding.resendTv.setOnClickListener {

            val orderBottomDialogFragment = OrderBottomDialogFragment()
            orderBottomDialogFragment.show(parentFragmentManager, "OrderBottomDialogFragment")
        }

    }


}

