package pl.btwarog

import org.gradle.api.JavaVersion

object AppConfig {

	const val buildToolsVersion = "30.0.3"
	const val minSdk = 23
	const val compileSdk = 30
	const val targetSdk = 30
	const val jvmTarget = "1.8"
	val javaVersion = JavaVersion.VERSION_1_8
}