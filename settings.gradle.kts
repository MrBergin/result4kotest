rootProject.name = "result4kotest"

pluginManagement {
    repositories {
        google()
        jcenter()
        gradlePluginPortal()
        mavenCentral()
        maven { url = uri("https://maven.pkg.jetbrains.space/public/p/compose/dev") }
    }

    resolutionStrategy {
        eachPlugin {
            if (requested.id.id.startsWith("com.android")) {
                useModule("com.android.tools.build:gradle:4.0.1")
            }
            if (requested.id.id.startsWith("org.jetbrains.kotlin")) {
                useModule("org.jetbrains.kotlin:kotlin-gradle-plugin:1.4.31")
            }
            if (requested.id.id.startsWith("org.jetbrains.compose")) {
                useModule("org.jetbrains.compose:compose-gradle-plugin:0.3.2")
            }
        }
    }
}

dependencyResolutionManagement {
    repositories {
        mavenCentral()
        mavenLocal()
    }
}