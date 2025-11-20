package ai.naver.carelink.data.datasources.local

import android.content.Context
import android.content.SharedPreferences

class AppStore(context: Context) {
    private val prefFileName = "care-link-prefs"
    private var _storage: SharedPreferences =
        context.getSharedPreferences(prefFileName, Context.MODE_PRIVATE)

    fun getUsername(): String? {
        return _storage.getString(PrefKeys.username, null)
    }

    fun setUsername(username: String) {
        _storage.edit().putString(PrefKeys.username, username).apply()
    }

    // get or set other values
}