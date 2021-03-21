package pl.btwarog

internal object Versions {

	/* Gradle */
	const val gradle_android_version = "4.1.2"

	/* Kotlin */
	const val kotlin_version = "1.4.30"
	const val kotlin_coroutines_version = "1.4.3"

	/* Google */
	const val appcompat_version = "1.2.0"
	const val material_version = "1.3.0"
	const val core_ktx_version = "1.3.2"
	const val fragment_ktx_version = "1.3.1"
	const val constraint_layout_version = "2.0.4"
	const val recycler_view_version = "1.1.0"
	const val paging_version = "3.0.0-beta02"
	const val lifecycle_version = "2.3.0"
	const val navigation_version = "2.3.4"
	const val room_version = "2.3.0-beta03"

	/* Apollo */
	const val apollo_version = "2.5.4"

	/* OkHttp */
	const val okhttp_version = "4.9.0"

	/* Dagger */
	const val dagger_version = "2.33"

	/* Glide */
	const val glide_verion = "4.12.0"

	/* Timberkt */
	const val timberkt_version = "1.5.1"

	/* Tests */
	const val junit_version = "4.13.2"
	const val assertj_version = "3.19.0"
	const val mockk_version = "1.11.0"
	const val turbine_version = "0.4.1"
	const val android_junit_version = "1.1.2"
	const val android_espresso_version = "3.3.0"
}

object AppDependencies {

	const val tools_gradle_android = "com.android.tools.build:gradle:${Versions.gradle_android_version}"
	const val tools_kotlin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin_version}"
	const val apollo_gradle_plugin = "com.apollographql.apollo:apollo-gradle-plugin:${Versions.apollo_version}"

	/* Kotlin */
	const val app_kotlin = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin_version}"
	const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.kotlin_coroutines_version}"

	/* Google */
	const val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat_version}"
	const val material = "com.google.android.material:material:${Versions.material_version}"
	const val core_ktx = "androidx.core:core-ktx:${Versions.core_ktx_version}"
	const val fragment_ktx = "androidx.fragment:fragment-ktx:${Versions.fragment_ktx_version}"
	const val constraint_layout = "androidx.constraintlayout:constraintlayout:${Versions.constraint_layout_version}"
	const val recycler_view = "androidx.recyclerview:recyclerview:${Versions.recycler_view_version}"
	const val paging_runtime = "androidx.paging:paging-runtime-ktx:${Versions.paging_version}"
	const val lifecycle_runtime = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycle_version}"
	const val lifecycle_viewmodel_ktx = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle_version}"
	const val lifecycle_viewmodel_savedstate =
		"androidx.lifecycle:lifecycle-viewmodel-savedstate:${Versions.lifecycle_version}"
	const val navigation_fragment_ktx = "androidx.navigation:navigation-fragment-ktx:${Versions.navigation_version}"
	const val navigation_ui_ktx = "androidx.navigation:navigation-ui-ktx:${Versions.navigation_version}"
	const val room_ktx = "androidx.room:room-ktx:${Versions.room_version}"
	const val room_compiler = "androidx.room:room-compiler:${Versions.room_version}"

	/* Apollo */
	const val apollo_runtime = "com.apollographql.apollo:apollo-runtime:${Versions.apollo_version}"
	const val apollo_coroutines = "com.apollographql.apollo:apollo-coroutines-support:${Versions.apollo_version}"

	/* OkHttp */
	const val okhttp = "com.squareup.okhttp3:okhttp:${Versions.okhttp_version}"
	const val okhttp_logging_interceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.okhttp_version}"

	/* Dagger */
	const val dagger = "com.google.dagger:dagger:${Versions.dagger_version}"
	const val dagger_compiler = "com.google.dagger:dagger-compiler:${Versions.dagger_version}"

	/* Coil */
	const val glide = "com.github.bumptech.glide:glide:${Versions.glide_verion}"
	const val glide_compiler = "com.github.bumptech.glide:compiler:${Versions.glide_verion}"

	/* Timberkt */
	const val timberkt = "com.github.ajalt:timberkt:${Versions.timberkt_version}"

	/* Unit Tests */
	const val jacoco_version = "0.8.6"
	const val junit = "junit:junit:${Versions.junit_version}"
	const val assertj = "org.assertj:assertj-core:${Versions.assertj_version}"
	const val mockk = "io.mockk:mockk:${Versions.mockk_version}"
	const val coroutines_test = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.kotlin_coroutines_version}"
	const val turbine = "app.cash.turbine:turbine:${Versions.turbine_version}"

	/* Instrumentation Tests */
	const val android_junit = "androidx.test.ext:junit:${Versions.android_junit_version}"
	const val android_espresso = "androidx.test.espresso:espresso-core:${Versions.android_espresso_version}"
}