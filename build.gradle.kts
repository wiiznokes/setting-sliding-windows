plugins {
    kotlin("multiplatform")
    id("org.jetbrains.compose")
    id("maven-publish")
}

group = "com.example"
version = "3.1.1"

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

            @OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class)
            dependencies {
                api(compose.material3)
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
