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
    jvm {
        testRuns["test"].executionTask.configure {
            useJUnitPlatform()
        }
    }
    sourceSets {
        val jvmMain by getting {
            dependencies {
                api ("org.jetbrains.compose.material3:material3-desktop:1.2.1")
            }
        }
        val jvmTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
    }
}
