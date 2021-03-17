plugins {
    kotlin("multiplatform") version "1.4.31"
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
    }
    publishing {
        publications {
            withType<MavenPublication> {
                pom.withXml {
                    asNode().appendNode("name", artifactId)
                    asNode().appendNode("description", description)
                    asNode().appendNode("developers")
                        .appendNode("developer").appendNode("name", "Jordan Bergin").parent().appendNode("email", "jordan.j.bergin@protonmail.com")
                }
            }
        }
    }
}
