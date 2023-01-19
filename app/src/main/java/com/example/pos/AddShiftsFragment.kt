package com.example.pos_admin

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.pos_admin.const.ShiftTime
import com.example.pos_admin.data.PosAdminRoomDatabase
import com.example.pos_admin.data.repository.ShiftRepository
import com.example.pos_admin.databinding.FragmentAddShiftsBinding
import com.example.pos_admin.model.ShiftsViewModel
import com.example.pos_admin.model.ShiftsViewModelFactory
import java.text.SimpleDateFormat
import java.util.*


class AddShiftsFragment : Fragment(), DatePickerDialog.OnDateSetListener {
    private lateinit var shiftsViewModel: ShiftsViewModel
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
        //Get shiftsViewModel
        val dao = PosAdminRoomDatabase.getDatabase(requireContext()).shiftDao()
        val repository = ShiftRepository(dao)
        val factory = ShiftsViewModelFactory(repository)
        shiftsViewModel = ViewModelProvider(this, factory)[ShiftsViewModel::class.java]
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding?.addShiftsFragment = this
        binding?.shiftsViewModel = shiftsViewModel
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
            shiftsViewModel._shift.value = selectedShiftTime.ordinal
        }


        val dialog = builder.create()
        val container = binding?.shiftPickContainer
        container?.setOnClickListener {
            dialog.show()
        }
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        calendar.set(year, month, dayOfMonth)
        val selectedTimeStamp = calendar.timeInMillis
        displayFormattedDate(selectedTimeStamp)
        shiftsViewModel._date.value = formatter.format(selectedTimeStamp).toString()
        shiftsViewModel._date.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            binding?.datePick?.text = formatter.format(selectedTimeStamp).toString()
        })
        formatter.format(selectedTimeStamp).toString()
    }

    fun previousFragment() {
        findNavController().navigate(R.id.action_addShiftsFragment_to_shiftsFragment)
    }

    fun addNewShift() {
        shiftsViewModel.insertShift()
    }

    private fun displayFormattedDate(timeStamp: Long) {
        binding?.datePick?.text = formatter.format(timeStamp)
    }

}