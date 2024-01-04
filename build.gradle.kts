// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.2.0-rc03" apply false
    id("org.jetbrains.kotlin.android") version "1.9.10" apply false
    id("com.android.library") version "8.2.0-rc03" apply false
    id("com.android.test") version "8.2.0-rc03" apply false
    id("org.jetbrains.kotlin.plugin.serialization") version "1.5.20" apply false
}

buildscript{
    repositories{
        google()
    }
    dependencies{
        val navVersion = "2.7.6"
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:$navVersion")
    }
}