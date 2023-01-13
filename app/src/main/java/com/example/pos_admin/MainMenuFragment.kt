package com.example.pos_admin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.room.Room
import com.example.pos_admin.data.ShiftRoomDatabase
import com.example.pos_admin.databinding.FragmentMainMenuBinding
import com.example.pos_admin.model.ShiftViewModel
import com.example.pos_admin.model.ShiftViewModelFactory
import com.example.pos_admin.repository.ShiftRepository
import java.util.*

class MainMenuFragment : Fragment() {
    private lateinit var viewModel: ShiftViewModel

/*    val shiftDao =
        context?.let { Room.databaseBuilder(it, ShiftRoomDatabase::class.java, "shifts_table").build().shiftDao() }
    val repository = shiftDao?.let { ShiftRepository(it) }
    val factory = repository?.let { ShiftViewModelFactory(it) }
    val viewModel = factory?.let { ViewModelProvider(this, it).get(ShiftViewModel::class.java) }*/

    private var binding: FragmentMainMenuBinding? = null

    //    val API: String = "6a238ea1bff80bfc12ddc7be3d2a0641"
//    val CITY: String = "Tokyo,jp"
//    val url = "https://api.openweathermap.org/data/2.5/weather?q=$CITY&units=metric&appid=$API"
//    val jsonString = url.readText()
//    val jsonObj = JSONObject(jsonString)
//    val main = jsonObj.getJSONObject("main")
//    val sys = jsonObj.getJSONObject("sys")
//    val wind = jsonObj.getJSONObject("wind")
//    val weather = jsonObj.getJSONArray("weather").getJSONObject(0)
//    temp = main.getString("temp")+"°C"
//    tempMin = "Min Temp: " + main.getString("temp_min")+"°C"
//    tempMax = "Max Temp: " + main.getString("temp_max")+"°C"
//    windSpeed = wind.getString("speed")
//    humidity = main.getString("humidity")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentBinding = FragmentMainMenuBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        val dao = ShiftRoomDatabase.getInstance(requireContext()).shiftDAO
        val repository = ShiftRepository(dao)
        val factory = ShiftViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory).get(ShiftViewModel::class.java)
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.mainMenuFragment = this

        binding?.viewModel = viewModel

        binding?.bottomNavigationView?.setOnNavigationItemSelectedListener {
            handleBottomNavigation(
                it.itemId
            )
        }
        binding?.bottomNavigationView?.selectedItemId = R.id.bottom_navigation_view
    }

    private fun handleBottomNavigation(
        menuItemId: Int
    ): Boolean = when (menuItemId) {
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