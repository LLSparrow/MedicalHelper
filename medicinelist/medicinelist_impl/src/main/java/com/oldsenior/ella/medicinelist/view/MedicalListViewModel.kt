package com.oldsenior.ella.medicinelist.view

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.oldsenior.ella.medicinelist.model.Medication
import javax.inject.Inject

class MedicalListViewModel @Inject constructor() : ViewModel() {
    val medList: LiveData<List<Medication>> = MutableLiveData<List<Medication>>()
}