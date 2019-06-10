package com.oldsenior.ella.medicinelist.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.oldsenior.ella.medicinelist.model.Medication
import javax.inject.Inject

class MedicalListViewModel @Inject constructor() : ViewModel() {
    val medList: LiveData<List<Medication>> = MutableLiveData<List<Medication>>()
}