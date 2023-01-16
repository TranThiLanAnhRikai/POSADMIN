package com.example.pos_admin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.room.Room
import com.example.pos_admin.application.PosAdminApplication
import com.example.pos_admin.data.PosAdminRoomDatabase
import com.example.pos_admin.databinding.FragmentFirstLoginBinding
import com.example.pos_admin.model.ShiftViewModel
import com.example.pos_admin.model.ShiftViewModelFactory

class FirstLoginFragment : Fragment() {

    private var binding: FragmentFirstLoginBinding? = null
    lateinit var database: PosAdminRoomDatabase


    private val shiftViewModel: ShiftViewModel by activityViewModels {
        ShiftViewModelFactory(
            (activity?.application as PosAdminApplication).database.shiftDao()
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentBinding = FragmentFirstLoginBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        database = Room.databaseBuilder(requireContext(), PosAdminRoomDatabase::class.java, "shifts_database.db")
            .build()


        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.firstLoginFragment = this
        binding?.viewModel = shiftViewModel
    }
    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    fun goToNextScreen() {
        findNavController().navigate(R.id.action_firstLoginFragment_to_secondLoginFragment)
    }
}