plugins {
    kotlin("multiplatform") version "1.4.31"
}

kotlin {
    jvm {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }
    js(BOTH) {
        browser()
        nodejs()
    }
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(kotlin("stdlib-common"))
                implementation("io.kotest:kotest-assertions-core-jvm:4.4.3")
                implementation("dev.forkhandles:result4k:LOCAL")
            }
        }
    }
}