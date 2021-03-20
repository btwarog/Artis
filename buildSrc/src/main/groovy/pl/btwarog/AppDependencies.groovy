package pl.btwarog

class AppDependencies {
	private static class Versions {
		/* Gradle */
		static final gradle_android_version = "4.1.2"
		/* Kotlin */
		static final kotlin_version = "1.4.21"
		static final kotlin_coroutines_version = "1.4.2"
		/* Google */
		static final appcompat_version = "1.2.0"
		static final material_version = "1.3.0"
		static final core_ktx_version = "1.3.2"
		static final fragment_ktx_version = "1.3.1"
		static final constraint_layout_version = "2.0.4"
		static final recycler_view_version = "1.1.0"
		static final paging_version = "3.0.0-beta02"
		static final lifecycle_version = "2.3.0"
		static final navigation_version = "2.3.4"
		/* Apollo */
		static final apollo_version = "2.5.4"
		/* OkHttp */

		static final okhttp_version = "4.9.0"
		/* Dagger */

		static final dagger_version = "2.33"
		/* Coil */

		static final coil_version = "1.1.1"
		/* Timberkt */

		static final timberkt_version = "1.5.1"
		/* Tests */

		static final junit_version = "4.13.2"

		static final assertj_version = "3.19.0"

		static final mockk_version = "1.11.0"

		static final turbine_version = "0.4.1"

		static final android_junit_version = "1.1.2"

		static final android_espresso_version = "3.3.0"
	}


	/* Gradle Plugins */
	static final tools_gradle_android = "com.android.tools.build:gradle:$Versions.gradle_android_version"
	static final tools_kotlin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$Versions.kotlin_version"
	static final apollo_gradle_plugin = "com.apollographql.apollo:apollo-gradle-plugin:$Versions.apollo_version"
	/* Kotlin */
	static final app_kotlin = "org.jetbrains.kotlin:kotlin-stdlib:$Versions.kotlin_version"
	static final coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$Versions.kotlin_coroutines_version"
	/* Google */
	static final appcompat = "androidx.appcompat:appcompat:$Versions.appcompat_version"
	static final material = "com.google.android.material:material:$Versions.material_version"
	static final core_ktx = "androidx.core:core-ktx:$Versions.core_ktx_version"
	static final fragment_ktx = "androidx.fragment:fragment-ktx:$Versions.fragment_ktx_version"
	static final constraint_layout = "androidx.constraintlayout:constraintlayout:$Versions.constraint_layout_version"
	static final recycler_view = "androidx.recyclerview:recyclerview:$Versions.recycler_view_version"
	static final paging_runtime = "androidx.paging:paging-runtime-ktx:$Versions.paging_version"
	static final lifecycle_runtime = "androidx.lifecycle:lifecycle-runtime-ktx:$Versions.lifecycle_version"
	static final lifecycle_viewmodel_ktx = "androidx.lifecycle:lifecycle-viewmodel-ktx:$Versions.lifecycle_version"
	static final lifecycle_viewmodel_savedstate = "androidx.lifecycle:lifecycle-viewmodel-savedstate:$Versions.lifecycle_version"
	static final navigation_fragment_ktx = "androidx.navigation:navigation-fragment-ktx:$Versions.navigation_version"
	static final navigation_ui_ktx = "androidx.navigation:navigation-ui-ktx:$Versions.navigation_version"
	/* Apollo */
	static final apollo_runtime = "com.apollographql.apollo:apollo-runtime:$Versions.apollo_version"
	static final apollo_coroutines = "com.apollographql.apollo:apollo-coroutines-support:$Versions.apollo_version"
	/* OkHttp */
	static final okhttp = "com.squareup.okhttp3:okhttp:$Versions.okhttp_version"
	static final okhttp_logging_interceptor = "com.squareup.okhttp3:logging-interceptor:$Versions.okhttp_version"
	/* Dagger */

	static final dagger = "com.google.dagger:dagger:$Versions.dagger_version"

	static final dagger_compiler = "com.google.dagger:dagger-compiler:$Versions.dagger_version"
	/* Coil */

	static final coil = "io.coil-kt:coil:$Versions.coil_version"
	/* Timberkt */

	static final timberkt = "com.github.ajalt:timberkt:$Versions.timberkt_version"
	/* Unit Tests */

	static final jacoco_version = "0.8.6"

	static final junit = "junit:junit:$Versions.junit_version"

	static final assertj = "org.assertj:assertj-core:$Versions.assertj_version"

	static final mockk = "io.mockk:mockk:$Versions.mockk_version"

	static final coroutines_test = "org.jetbrains.kotlinx:kotlinx-coroutines-test:$Versions.kotlin_coroutines_version"

	static final turbine = "app.cash.turbine:turbine:$Versions.turbine_version"
	/* Instrumentation Tests */

	static final android_junit = "androidx.test.ext:junit:$Versions.android_junit_version"

	static final android_espresso = "androidx.test.espresso:espresso-core:$Versions.android_espresso_version"
}