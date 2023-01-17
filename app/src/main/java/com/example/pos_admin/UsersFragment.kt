package com.example.pos_admin

import android.Manifest
import android.app.AlertDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.util.Base64
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.pos_admin.adapter.ShiftsAdapter
import com.example.pos_admin.adapter.UsersAdapter
import com.example.pos_admin.application.PosAdminApplication
import com.example.pos_admin.const.ItemType
import com.example.pos_admin.databinding.FragmentAddMenuBinding
import com.example.pos_admin.databinding.FragmentUsersBinding
import com.example.pos_admin.model.MenuViewModel
import com.example.pos_admin.model.MenuViewModelFactory
import com.example.pos_admin.model.UsersViewModel
import com.example.pos_admin.model.UsersViewModelFactory
import java.io.ByteArrayOutputStream

class UsersFragment : Fragment() {
    private val usersViewModel: UsersViewModel by activityViewModels {
        UsersViewModelFactory(
            (activity?.application as PosAdminApplication).database.userDao()
        )
    }
    private var binding: FragmentUsersBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentBinding = FragmentUsersBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.usersFragment = this
        binding?.usersViewModel = usersViewModel
        val recyclerView = binding?.users
        usersViewModel.getAllUsers().observe(viewLifecycleOwner, Observer { users ->
            val adapter = UsersAdapter(requireContext(), users)
            recyclerView?.adapter = adapter
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }


    fun goToNextScreen() {
        findNavController().navigate(R.id.action_usersFragment_to_addUsersFragment)
    }

}

