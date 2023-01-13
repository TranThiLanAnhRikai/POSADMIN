package com.example.pos_admin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.pos_admin.databinding.FragmentFirstLoginBinding
import com.example.pos_admin.model.ShiftViewModel

class FirstLoginFragment : Fragment() {

    private var binding: FragmentFirstLoginBinding? = null



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentBinding = FragmentFirstLoginBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.firstLoginFragment = this
    }
    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    fun goToNextScreen() {
        findNavController().navigate(R.id.action_firstLoginFragment_to_secondLoginFragment)
    }
}