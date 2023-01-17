package com.example.pos_admin

import android.Manifest
import android.app.AlertDialog
import android.content.ContentValues.TAG
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Base64.DEFAULT
import android.util.Base64.encodeToString
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityCompat.startActivityForResult
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.pos_admin.application.PosAdminApplication
import com.example.pos_admin.const.ItemType
import com.example.pos_admin.databinding.FragmentAddMenuBinding
import com.example.pos_admin.model.MenuViewModel


import com.example.pos_admin.model.MenuViewModelFactory
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*


class AddMenuFragment : Fragment() {
    private val cameraRequestId  = 1222
    private val itemTypes =  arrayOf(ItemType.APPETIZER, ItemType.SOUP, ItemType.DESERT, ItemType.ENTREE, ItemType.DRINK)
    private val menuViewModel: MenuViewModel by activityViewModels {
        MenuViewModelFactory(
            (activity?.application as PosAdminApplication).database.menuItemDao()
        )
    }
    private var binding: FragmentAddMenuBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentBinding = FragmentAddMenuBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.addMenuFragment = this
        binding?.menuViewModel = menuViewModel
        val options = itemTypes.map { it.name }.toTypedArray()
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Choose the type")
        builder.setItems(options) { _, which ->
            val selectedItemType = itemTypes[which]
            binding?.typePick?.text = selectedItemType.typeName
            menuViewModel.type.value = selectedItemType.typeName
        }


        val dialog = builder.create()
        val container = binding?.typePickContainer
        container?.setOnClickListener {
            dialog.show()
        }
        if (ContextCompat.checkSelfPermission(
                requireContext(), Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_DENIED
        )
            ActivityCompat.requestPermissions(
                requireActivity(), arrayOf(Manifest.permission.CAMERA),
                cameraRequestId
            )
        binding?.addImgText?.setOnClickListener {
            val cameraInt = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(cameraInt, cameraRequestId)
        }


    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == cameraRequestId){
            val images:Bitmap = data?.extras?.get("data") as Bitmap
            binding?.image?.setImageBitmap(images)
            Log.d(TAG, "img $images")
            val bitmap: Bitmap = images
            val byteArrayOutputStream = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream)
            val imageInByte = byteArrayOutputStream.toByteArray()
            val imageToStore = encodeToString(imageInByte, DEFAULT)
            menuViewModel.image.value = imageToStore
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    fun addNewItem() {
        menuViewModel.insertItem()

    }

    fun backToMenu() {
        findNavController().navigate(R.id.action_addMenuFragment_to_menuFragment)
    }
}
