package com.luckylittlesparrow.healthassistant.feature_medicine_list.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.luckylittlesparrow.healthassistant.feature_medicine_list.databinding.FragmentMedicineListBinding
import com.luckylittlesparrow.healthassistant.core_ui.utils.executeAfter
import com.luckylittlesparrow.healthassistant.core_ui.view.AutoClearedValue
import com.luckylittlesparrow.healthassistant.core_ui.view.BaseFragment
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * @author Gusev Andrei
 * @since  1.0
 */
class MedicineListFragment : BaseFragment() {
    private val authViewModel: MedicineListViewModel by viewModel()

    private var binding by AutoClearedValue<FragmentMedicineListBinding>()

    private val medicineListAdapter: MedicineListAdapter by inject()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentMedicineListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        observeViewModel()

        medicineListAdapter.viewModel = authViewModel

        binding.executeAfter {
            adapter = medicineListAdapter
            lifecycleOwner = viewLifecycleOwner
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        authLoginSignUpButton.setOnClickListener {
//            authViewModel.createAccount(it,authLoginEmailEditText,)
//        }
    }


    private fun observeViewModel() {
//        authViewModel.loginResult().observe(this) {
//            toast(it.id)
//        }
    }
}