package com.example.pos

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.forEach
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import com.example.pos.data.entity.MenuItem
import com.example.pos_admin.R
import com.example.pos_admin.adapter.MenuItemsAdapter
import com.example.pos_admin.data.PosAdminRoomDatabase
import com.example.pos_admin.data.repository.MenuItemRepository
import com.example.pos_admin.databinding.FragmentMenuBinding
import com.example.pos_admin.databinding.FragmentOrderBinding
import com.example.pos_admin.model.MenuViewModel
import com.example.pos_admin.model.MenuViewModelFactory


class OrderFragment : Fragment() {
    private var binding: FragmentOrderBinding? = null
    private lateinit var menuViewModel: MenuViewModel
    private lateinit var selectedItems: List<MenuItem>
    private lateinit var adapter: MenuItemsAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentBinding = FragmentOrderBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        val dao = PosAdminRoomDatabase.getDatabase(requireContext()).menuItemDao()
        val repository = MenuItemRepository(dao)
        val factory = MenuViewModelFactory(repository)
        menuViewModel = ViewModelProvider(this, factory)[MenuViewModel::class.java]
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.orderFragment = this
        binding?.menuViewModel = menuViewModel
        val recyclerView = binding?.orderItems
        val btnsContainer = binding?.btnsContainer
        btnsContainer?.forEach { it ->
            it.setOnClickListener{
                when(it.id) {
                    R.id.food_selected -> {
                        Log.d(TAG, "tag ${it.tag.toString()}")
                        selectedItems = (menuViewModel.getMenuItems(it.tag.toString())).value!!
                        adapter = MenuItemsAdapter(requireContext(), selectedItems!!)
                    }
                    R.id.dessert_selected -> {
                        selectedItems = (menuViewModel.getMenuItems(it.tag.toString())).value!!
                        adapter = MenuItemsAdapter(requireContext(), selectedItems!!)
                    }
                    R.id.drink_selected -> {
                        selectedItems = (menuViewModel.getMenuItems(it.tag.toString())).value!!
                        adapter = MenuItemsAdapter(requireContext(), selectedItems!!)
                    }
                }
                recyclerView?.adapter = adapter
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}