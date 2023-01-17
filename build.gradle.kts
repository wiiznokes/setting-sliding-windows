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
            resources.srcDirs("resources")

            dependencies {
                api ("org.jetbrains.compose.material3:material3-desktop:${extra["compose.version"] as String}")
            }
        }
        val jvmTest by getting {
            resources.srcDirs("resources")

            dependencies {
                implementation(compose.desktop.currentOs)
                implementation("org.jetbrains.compose.material3:material3-desktop:${extra["compose.version"] as String}")
                implementation(kotlin("test"))
            }
        }
    }
}
