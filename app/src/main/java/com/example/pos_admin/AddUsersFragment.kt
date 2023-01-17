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
import androidx.navigation.fragment.findNavController
import com.example.pos_admin.application.PosAdminApplication
import com.example.pos_admin.const.ItemType
import com.example.pos_admin.databinding.FragmentAddMenuBinding
import com.example.pos_admin.databinding.FragmentAddUsersBinding
import com.example.pos_admin.model.MenuViewModel
import com.example.pos_admin.model.MenuViewModelFactory
import com.example.pos_admin.model.UsersViewModel
import com.example.pos_admin.model.UsersViewModelFactory
import java.io.ByteArrayOutputStream


class AddUsersFragment : Fragment() {
    private val usersViewModel: UsersViewModel by activityViewModels {
        UsersViewModelFactory(
            (activity?.application as PosAdminApplication).database.userDao()
        )
    }
    private var binding: FragmentAddUsersBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentBinding = FragmentAddUsersBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.addUsersFragment = this
        binding?.usersViewModel = usersViewModel

    }
    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    fun addNewUser() {
        usersViewModel.insertNewUser()

    }

    fun backToUsers() {
        findNavController().navigate(R.id.action_addUsersFragment_to_usersFragment)
    }
}