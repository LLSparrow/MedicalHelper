//package com.oldsenior.ella.menu.view
//
//import android.databinding.DataBindingUtil
//import android.support.v7.util.DiffUtil
//import android.view.LayoutInflater
//import android.view.ViewGroup
//import com.oldsenior.ella.corelib.io.AppExecutors
//import com.oldsenior.ella.core_ui.view.recycler.DataBoundListAdapter
//import com.oldsenior.ella.medicallistlib.R
//import com.oldsenior.ella.medicallistlib.databinding.MedicalItemBinding
//import Medication
//
//class MedicalListAdapter(
//        private val dataBindingComponent: DataBindingComponent,
//        appExecutors: AppExecutors,
//        private val callback: ((Medication) -> Unit)?
//) : DataBoundListAdapter<Medication, MedicalItemBinding>(
//        appExecutors = appExecutors,
//        diffCallback = MedicationDiffItemCallback) {
//
//    override fun createBinding(parent: ViewGroup): MedicalItemBinding {
//        val binding = DataBindingUtil
//                .inflate<MedicalItemBinding>(
//                        LayoutInflater.from(parent.context),
//                        R.layout.medical_item,
//                        parent,
//                        false,
//                        dataBindingComponent
//                )
//        binding.root.setOnClickListener {
//            binding.medication?.let {
//                callback?.invoke(it)
//            }
//        }
//        return binding
//    }
//
//    override fun bind(binding: MedicalItemBinding, item: Medication) {
//        binding.medication = item
//    }
//}
//
//object MedicationDiffItemCallback : DiffUtil.ItemCallback<Medication>() {
//    override fun areItemsTheSame(oldItem: Medication, newItem: Medication): Boolean {
//        return oldItem.id == newItem.id
//    }
//
//    override fun areContentsTheSame(oldItem: Medication, newItem: Medication): Boolean {
//        return oldItem.avatarUrl == newItem.avatarUrl
//                && oldItem.description == newItem.description
//    }
//}