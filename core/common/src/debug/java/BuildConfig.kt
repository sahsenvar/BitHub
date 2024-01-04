object BuildConfig {
    const val isDebuggable: Boolean = true
    val variant: Variant = Variant.Debug
    const val baseUrl: String = "https://api.github.com/"


    object GitHubAuthApp{
        const val appId = "766783"
        const val clientId = "Iv1.8ce25fc940e87f04"
        const val clientSecret = "b14c7e0a7d4ac44e5cc6a4366d48d3c945ade42b"
        const val baseUrl: String = "https://github.com/"
        const val loginCallBackUrl = "bithub://oauth-code-callback"
        const val loginUrl = "$baseUrl/login/oauth/authorize?client_id=$clientId&redirect_uri=$loginCallBackUrl"
        const val publicLink = "https://github.com/apps/fakebithubapp"
        var accessToken: String? = null
    }
}

enum class Variant{
    Debug,
    Release,
    Beta,
    BetaRelease
}