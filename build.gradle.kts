plugins {
    kotlin("multiplatform")
    id("org.jetbrains.compose")
    id("maven-publish")
}

group = "com.example"
version = "1.0.0"

repositories {
    google()
    mavenCentral()
    maven ( "https://jitpack.io")
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
}

kotlin {
    jvm()
    sourceSets {
        val jvmMain by getting {
            dependencies {
                api ("org.jetbrains.compose.material3:material3-desktop:1.2.1")
            }
        }
        val jvmTest by getting {
            dependencies {
                implementation(compose.desktop.currentOs)
                implementation("org.jetbrains.compose.material3:material3-desktop:${extra["compose.version"] as String}")
                implementation(kotlin("test"))
            }
        }
    }
}
