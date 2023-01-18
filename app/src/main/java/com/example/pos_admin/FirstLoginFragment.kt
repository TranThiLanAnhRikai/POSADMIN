package com.example.pos_admin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.room.Room
import com.example.pos_admin.data.PosAdminRoomDatabase
import com.example.pos_admin.databinding.FragmentFirstLoginBinding


class FirstLoginFragment : Fragment() {

    private var binding: FragmentFirstLoginBinding? = null
    lateinit var database: PosAdminRoomDatabase


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentBinding = FragmentFirstLoginBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        database = Room.databaseBuilder(requireContext(), PosAdminRoomDatabase::class.java, "shifts_database.db")
            .build()

        (activity as AppCompatActivity?)!!.supportActionBar!!.hide()


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

    fun nextScreen() {
        findNavController().navigate(R.id.action_firstLoginFragment_to_secondLoginFragment)
    }
}