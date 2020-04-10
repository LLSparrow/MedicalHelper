package com.llsparrow.healthassistant.feature_personal_info_api.validator

sealed class ValidatorState

object Valid : ValidatorState()

object InValid : ValidatorState()