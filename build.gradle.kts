plugins {
    kotlin("multiplatform")
    id("org.jetbrains.compose")
    id("maven-publish")
}

group = "com.example"
version = "2.0.1"

repositories {
    google()
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    maven("https://jitpack.io")
}

kotlin {
    jvm()
    sourceSets {
        val jvmMain by getting {
            resources.srcDirs("resources")

            dependencies {
                api("org.jetbrains.compose.material3:material3-desktop:${extra["compose.version"] as String}")
            }
        }
        val jvmTest by getting {
            resources.srcDirs("resources")

            dependencies {
                implementation(compose.desktop.currentOs)
                implementation(kotlin("test"))
            }
        }
    }
}
