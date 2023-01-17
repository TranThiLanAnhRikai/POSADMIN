package com.example.pos_admin

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.pos_admin.application.PosAdminApplication
import com.example.pos_admin.const.ShiftTime
import com.example.pos_admin.databinding.FragmentAddShiftsBinding
import com.example.pos_admin.model.ShiftViewModel
import com.example.pos_admin.model.ShiftViewModelFactory
import java.text.SimpleDateFormat
import java.util.*


class AddShiftsFragment : Fragment(), DatePickerDialog.OnDateSetListener {
    private val shiftViewModel: ShiftViewModel by activityViewModels {
        ShiftViewModelFactory(
            (activity?.application as PosAdminApplication).database.shiftDao()
        )
    }
    private var binding: FragmentAddShiftsBinding? = null
    private val calendar = Calendar.getInstance()
    private val formatter = SimpleDateFormat("yyyy, MM, dd, EEEE", Locale.US)
    val shiftOptions = arrayOf(ShiftTime.MORNING, ShiftTime.AFTERNOON, ShiftTime.NOON)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentBinding = FragmentAddShiftsBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding?.addShiftsFragment = this
        binding?.viewModel = shiftViewModel
        binding?.datePick?.setOnClickListener {
            DatePickerDialog(
                requireContext(),
                this,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            )
                .show()
        }
        val options = shiftOptions.map { it.name }.toTypedArray()
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Choose a shift")
        builder.setItems(options) { _, which ->
            val selectedShiftTime = shiftOptions[which]
            binding?.shiftText?.text = selectedShiftTime.shiftName
            shiftViewModel._shift.value = selectedShiftTime.ordinal
        }


        val dialog = builder.create()
        val container = binding?.shiftPickContainer
        container?.setOnClickListener {
            dialog.show()
        }

/*        shiftViewModel._shift.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            shiftViewModel.insert()
            Toast.makeText(requireContext(), "Shift added", Toast.LENGTH_SHORT).show()
        })*/

    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        calendar.set(year, month, dayOfMonth)
        val selectedTimeStamp = calendar.timeInMillis
        displayFormattedDate(selectedTimeStamp)
        shiftViewModel._date.value = formatter.format(selectedTimeStamp).toString()
        shiftViewModel._date.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            binding?.datePick?.text = formatter.format(selectedTimeStamp).toString()
        })
        formatter.format(selectedTimeStamp).toString()
    }

    fun goBackToShiftsFragment() {
        findNavController().navigate(R.id.action_addShiftsFragment_to_shiftsFragment)
    }

    fun addNewShift() {
        shiftViewModel.insertShift()
    }

    private fun displayFormattedDate(timeStamp: Long) {
        binding?.datePick?.text = formatter.format(timeStamp)
    }

}