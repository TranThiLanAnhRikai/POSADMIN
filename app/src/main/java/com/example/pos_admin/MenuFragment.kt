package com.example.pos_admin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.pos_admin.application.PosAdminApplication
import com.example.pos_admin.databinding.FragmentMenuBinding
import com.example.pos_admin.model.MenuViewModel
import com.example.pos_admin.model.MenuViewModelFactory

class MenuFragment : Fragment() {
    private val menuViewModel: MenuViewModel by activityViewModels {
        MenuViewModelFactory(
            (activity?.application as PosAdminApplication).database.menuItemDao()
        )
    }
   private var binding: FragmentMenuBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentBinding = FragmentMenuBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.menuFragment = this
        binding?.menuViewModel = menuViewModel
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    fun goToAddMenuFragment() {
        findNavController().navigate(R.id.action_menuFragment_to_addMenuFragment)
    }
}