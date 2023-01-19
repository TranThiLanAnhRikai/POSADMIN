package com.example.pos_admin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.pos_admin.data.PosAdminRoomDatabase
import com.example.pos_admin.data.repository.UserRepository
import com.example.pos_admin.databinding.FragmentAddUsersBinding
import com.example.pos_admin.model.UsersViewModel
import com.example.pos_admin.model.UsersViewModelFactory


class AddUsersFragment : Fragment() {
    private lateinit var usersViewModel: UsersViewModel
    private var binding: FragmentAddUsersBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentBinding = FragmentAddUsersBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        val dao = PosAdminRoomDatabase.getDatabase(requireContext()).userDao()
        val repository = UserRepository(dao)
        val factory = UsersViewModelFactory(repository)
        usersViewModel = ViewModelProvider(this, factory)[UsersViewModel::class.java]
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.addUsersFragment = this
        binding?.usersViewModel = usersViewModel
        val secondCode = binding?.secondCodeEdttxt
        binding?.role?.setOnClickListener {
            if (binding?.admin?.isChecked() == true) {
                secondCode?.setVisibility(View.VISIBLE)
            }
        }
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