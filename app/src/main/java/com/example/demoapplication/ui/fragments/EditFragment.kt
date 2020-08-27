package com.example.demoapplication.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.example.demoapplication.R
import com.example.demoapplication.data.entity.UserEntity
import com.example.demoapplication.ui.activities.LocalizationTestActivity
import com.example.demoapplication.ui.activities.MainActivity
import com.example.demoapplication.ui.base.BaseFragment
import com.example.demoapplication.ui.viewModels.EditFragmentViewModel
import com.example.demoapplication.util.Output
import com.example.demoapplication.util.onClick
import com.zeugmasolutions.localehelper.Locales
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_edit.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.util.*

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class EditFragment : BaseFragment() {

    private val editFragmentVM: EditFragmentViewModel by viewModels()


    override fun getLayout(): Int = R.layout.fragment_edit


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        editFragmentVM.handleEvent(EditFragmentViewModel.EditFragmentEvent.FetchUsers)
        this.observerLiveData();
        confirmBtn.setOnClickListener {
            editFragmentVM.handleEvent(
                EditFragmentViewModel
                    .EditFragmentEvent.AddUser(
                        UserEntity(
                            name = etName.text.toString().trim(),
                            address = etAddress.text.toString().trim()
                        )
                    )
            )
        }

        confirmBtn.setOnLongClickListener{
//            editFragmentVM.handleEvent(EditFragmentViewModel.EditFragmentEvent.DeleteAllUsers)
            startActivity(Intent(requireContext(), LocalizationTestActivity::class.java))
            return@setOnLongClickListener true
        }

        lifecycleScope.launch {

            btnEnglish.setOnClickListener{
                Log.d("TAG", "onViewCreated: ")
                (activity as MainActivity?)!!.updateLocale(Locale.ENGLISH)
            }

            btnPortuguese.setOnClickListener{
                Log.d("TAG", "onViewCreated: ")
                (activity as MainActivity?)!!.updateLocale(Locale("pt","PT"))
            }

        }
    }

    private fun observerLiveData() {
        editFragmentVM._userLiveData.observe(viewLifecycleOwner, Observer { output ->
            when (output) {
                is Output.Loading -> progressBar.visibility = View.VISIBLE

                is Output.Error -> {
                    progressBar.visibility = View.GONE
                    apiResultTextView.text = output.exception.message
                }

                is Output.Success -> {
                    progressBar.visibility = View.GONE
                    if (output.data.isNotEmpty()) {
                        var sb = StringBuffer()
                        for (user in output.data) {
                            sb.append(user.name + " - " + user.address + "\n")
                        }
                        apiResultTextView.text = sb.toString()
                    } else {
                        apiResultTextView.text = "No Users found"
                    }


                }
            }
        })
    }


}