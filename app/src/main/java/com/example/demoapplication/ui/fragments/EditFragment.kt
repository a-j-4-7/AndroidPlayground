package com.example.demoapplication.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.demoapplication.R
import com.example.demoapplication.data.entity.UserEntity
import com.example.demoapplication.ui.viewModels.EditFragmentViewModel
import com.example.demoapplication.ui.viewModels.HomeFragmentViewModel
import com.example.demoapplication.util.Output
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_edit.*
import kotlinx.android.synthetic.main.fragment_edit.apiResultTextView
import kotlinx.android.synthetic.main.fragment_edit.progressBar
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class EditFragment : Fragment() {

    private val editFragmentVM: EditFragmentViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_edit, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        editFragmentVM.handleEvent(EditFragmentViewModel.EditFragmentEvent.FetchUsers)
        this.observerLiveData();
        confirmBtn.setOnClickListener {
            editFragmentVM.handleEvent(EditFragmentViewModel
                .EditFragmentEvent.AddUser(
                    UserEntity(
                        name = etName.text.toString().trim(),
                        address = etAddress.text.toString().trim())
                ))
        }

        confirmBtn.setOnLongClickListener{
            editFragmentVM.handleEvent(EditFragmentViewModel.EditFragmentEvent.DeleteAllUsers)
            return@setOnLongClickListener true
        }
    }

    private fun observerLiveData() {
        editFragmentVM._userLiveData.observe(viewLifecycleOwner, Observer {output ->
            when(output){
                is Output.Loading -> progressBar.visibility = View.VISIBLE

                is Output.Error -> {
                    progressBar.visibility = View.GONE
                    apiResultTextView.text = output.exception.message
                }

                is Output.Success -> {
                    progressBar.visibility = View.GONE
                    if(output.data.isNotEmpty()){
                        var sb = StringBuffer()
                        for (user in output.data){
                            sb.append(user.name + " - " + user.address +"\n")
                        }
                        apiResultTextView.text = sb.toString()
                    }else{
                        apiResultTextView.text = "No Users found"
                    }


                }
            }
        })
    }


}