import org.gradle.jvm.tasks.Jar
import org.gradle.kotlin.dsl.kotlin
import org.gradle.kotlin.dsl.version

// Tasks
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

val kotlinVersion = "1.3.21"
val ktorVersion = "1.1.5"

plugins {
  kotlin("jvm") version "1.3.21"
  id("com.github.johnrengelman.shadow") version "5.0.0"
  application
}

repositories {
  jcenter()
  mavenCentral()
  "http://dl.bintray.com/kotlin".let {
    maven { setUrl("$it/ktor") }
    maven { setUrl("$it/kotlinx") }
  }
}

dependencies {
  implementation(kotlin("stdlib-jdk8", kotlinVersion))
  implementation("io.ktor:ktor-server-core:$ktorVersion")
  implementation("io.ktor:ktor-server-netty:$ktorVersion")

  testImplementation("org.jetbrains.kotlin:kotlin-test")
  testImplementation(kotlin("test-junit"))
}

application {
  mainClassName = "com.blunderlist.gateway.MainKt"
}

tasks.withType(KotlinCompile::class.java).all {
  kotlinOptions.jvmTarget = "1.8"
}

tasks.withType<ShadowJar> {
  baseName = "gateway"
}
