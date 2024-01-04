import android.content.Context
import androidx.datastore.preferences.core.stringPreferencesKey
import com.core.common.extentions.mainDataStore
import com.core.common.preference.BasePreference
import com.core.common.preference.PreferenceFindingResult
import com.core.common.preference.PreferenceService
import com.core.common.preference.PreferenceWritingResult


class PreferenceHelper(context: Context) : BasePreference(context.mainDataStore), PreferenceService {
    companion object {
        private val KeyAccessToken = stringPreferencesKey("KEY_ACCESS_TOKEN")
    }

    override suspend fun findUserToken(default: String?): PreferenceFindingResult<String> {
        return if (default.toBoolean())
            KeyAccessToken.findValue(default!!)
        else
            KeyAccessToken.findValues()

    }

    override suspend fun upsertUserToken(value: String): PreferenceWritingResult {
        return KeyAccessToken.upsertValue(value)
    }


}