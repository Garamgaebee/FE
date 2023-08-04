package com.gachon.garamgaebi2.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.gachon.garamgaebi2.BR
import com.gachon.garamgaebi2.R
import com.gachon.garamgaebi2.base.BaseActivity
import com.gachon.garamgaebi2.databinding.ActivityLoginBinding
import com.gachon.garamgaebi2.databinding.ActivityRegisterBinding
import com.gachon.garamgaebi2.viewModel.RegisterViewModel

class RegisterActivity : BaseActivity<ActivityRegisterBinding>(ActivityRegisterBinding::inflate){

    var currentProcess = 0



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportFragmentManager.beginTransaction().add(binding.fragmentContainer.id, Register1EmailFragment()).commit()


        val viewModel = ViewModelProvider(this)[RegisterViewModel::class.java]
        binding.setVariable(BR.viewModel,viewModel)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        binding.registerProcessBtn.nextBtn.setOnClickListener {
            changeFragment()

        }



    }

    fun changeFragment(){
        when (currentProcess) {
            0 ->{
                // Register 1로 이동
                supportFragmentManager.beginTransaction().add(binding.fragmentContainer.id, Register2AuthenticationFragment()).commit()
                binding.registerProcessBtn.nextBtn.text= application.getString(R.string.authenticate)

            }
            1->{
                // Register 2로 이동
                supportFragmentManager.beginTransaction().add(binding.fragmentContainer.id, Register2AuthenticationFragment()).commit()
                binding.registerProcessBtn.nextBtn.text= application.getString(R.string.next)
            }
            2->{
                // Register 3로 이동
                supportFragmentManager.beginTransaction().add(binding.fragmentContainer.id, Register2AuthenticationFragment()).commit()
                binding.registerProcessBtn.nextBtn.text= application.getString(R.string.start)
            }
            3->{

            }
            4->{

            }
        }
    }

}