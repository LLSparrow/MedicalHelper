package com.llsparrow.healthassistant.feature_medicine_list.domain

import android.content.Intent
import com.llsparrow.core_base_api.domain.interactor.ResultInteractor
import com.llsparrow.core_base_api.io.AppDispatchers
import com.llsparrow.healthassistant.core_di.FeatureScope
import com.llsparrow.healthassistant.feature_medicine_list.domain.model.Medicine
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

@FeatureScope
class MedicineListInteractor @Inject constructor(
    private val repository: MedicineRepository,
    private val dispatchers: AppDispatchers
) : ResultInteractor<MedicineListInteractor.Params, List<Medicine>>() {

    override val dispatcher: CoroutineDispatcher
        get() = dispatchers.IO

    data class Params(val title: String)

    override suspend fun doWork(params: Params): List<Medicine> {
        throw NotImplementedError()
    }
}