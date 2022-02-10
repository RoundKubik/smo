import org.jetbrains.compose.compose
import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.5.21"
    id("org.jetbrains.compose") version "1.0.0-alpha3"
}

group = "me.roundkubik"
version = "1.0"

repositories {
    google()
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
}
allprojects {
    repositories {
        google()
        mavenCentral()
    }
}
dependencies {
    implementation(compose.desktop.currentOs)
    implementation("com.google.dagger:dagger:2.40.1")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.2")

}
tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "11"
}

compose.desktop {
    application {
        mainClass = "MainKt"
        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "smo"
            packageVersion = "1.0.0"
        }
    }
}


