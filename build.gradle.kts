plugins {
    kotlin("multiplatform")
    id("maven-publish")
}

group = "mr.bergin"
version = "LOCAL"

kotlin {
    jvm {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(kotlin("stdlib-common"))
                implementation("io.kotest:kotest-assertions-core:4.4.3")
                implementation("dev.forkhandles:result4k:LOCAL")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation("io.kotest:kotest-assertions-core:4.4.3")
                implementation("io.kotest:kotest-framework-api:4.4.3")
            }
        }
        val jvmTest by getting {
            dependencies {
                implementation("io.kotest:kotest-runner-junit5:4.4.3")
            }
        }
    }
    publishing {
        publications {
            withType<MavenPublication> {
                configurePom {
                    name(artifactId)
                    description(description ?: "")
                    developers {
                        developer {
                            name("Jordan Bergin")
                            email("jordan.j.bergin@protonmail.com")
                        }
                    }
                }
            }
        }
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}