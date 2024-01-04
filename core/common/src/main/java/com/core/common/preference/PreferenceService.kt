package com.core.common.preference

interface PreferenceService {
    suspend fun findUserToken(default: String? = null) : PreferenceFindingResult<String>
    suspend fun upsertUserToken(value: String) : PreferenceWritingResult
}