package com.example.pos_admin

import android.os.Bundle
import android.text.TextUtils.replace
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.pos_admin.databinding.FragmentMainMenuBinding
import com.example.pos_admin.databinding.FragmentSecondLoginBinding
import com.example.pos_admin.model.PosAdminViewModel

class MainMenuFragment : Fragment() {


    private var binding: FragmentMainMenuBinding? = null

    // Use the 'by activityViewModels()' Kotlin property delegate from the fragment-ktx artifact
    private val sharedViewModel: PosAdminViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentBinding = FragmentMainMenuBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //binding?.mainMenuFragment = this
        binding?.bottomNavigationView?.setOnNavigationItemSelectedListener {
            handleBottomNavigation(
                it.itemId
            )
        }
        binding?.bottomNavigationView?.selectedItemId = R.id.bottom_navigation_view
    }

    private fun handleBottomNavigation(
        menuItemId: Int
    ): Boolean = when(menuItemId) {
        R.id.main_menu_users_button -> {
            findNavController().navigate(R.id.action_mainMenuFragment_to_usersFragment)
            true
        }
        R.id.main_menu_menu_button -> {
            findNavController().navigate(R.id.action_mainMenuFragment_to_menuFragment)
            true
        }
        R.id.main_menu_notifications_button -> {
            findNavController().navigate(R.id.action_mainMenuFragment_to_notificationsFragment)
            true
        }
        else -> false
    }


    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    fun goToSalesAnalysisFragment() {
        findNavController().navigate(R.id.action_mainMenuFragment_to_salesAnalysisFragment)
    }

    fun goToShiftsFragment() {
        findNavController().navigate(R.id.action_mainMenuFragment_to_shiftsFragment)
    }

}