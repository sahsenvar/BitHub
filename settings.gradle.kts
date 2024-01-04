pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven(url = "https://jitpack.io")
    }
}

rootProject.name = "BitHub"
include(":app")
include(":features:home:data")
include(":features:home:domain")
include(":features:home:presentation")
include(":features:loginregister:data")
include(":features:loginregister:domain")
include(":features:loginregister:presentation")
include(":core:common")
include(":core:api:remote")
include(":core:api:local")
include(":benchmark")
include(":features:users:data")
include(":features:users:domain")
include(":features:users:presentation")
