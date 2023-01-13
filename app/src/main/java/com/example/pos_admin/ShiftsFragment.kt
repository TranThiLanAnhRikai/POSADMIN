package com.example.pos_admin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.room.Room
import com.example.pos_admin.data.ShiftRoomDatabase
import com.example.pos_admin.databinding.FragmentMainMenuBinding
import com.example.pos_admin.databinding.FragmentShiftsBinding
import com.example.pos_admin.model.ShiftViewModel
import com.example.pos_admin.model.ShiftViewModelFactory
import com.example.pos_admin.repository.ShiftRepository


class ShiftsFragment : Fragment() {


    private var binding: FragmentShiftsBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentBinding = FragmentShiftsBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.shiftsFragment = this
       /* binding?.viewModel = viewModel*/

    }


    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}