object Releases {
    const val versionCode = 10000
    const val versionName = "1.0.0"
}

object Versions {
    const val compileSdk = 30
    const val targetSdk = 30
    const val minSdk = 21

    const val kotlin = "1.5.21"
    const val material = "1.4.0"
    const val constraintLayout = "2.0.4"
    const val ktx = "1.0.2"
    const val room = "2.3.0"
    const val appCompat = "1.3.1"

    const val navigation = "2.3.5"
    const val coroutines = "1.4.0"

    const val hilt = "2.35"
    const val hiltGradle = "2.28-alpha"
    const val hiltCompiler = "1.0.0"
    const val hiltViewModel = "1.0.0-alpha03"

    const val testJunit = "4.12"
    const val testRunner = "1.1.1"
    const val testCore = "1.3.0"
    const val testMockk = "1.12.0"
    const val testJunitExt = "1.1.0"
    const val testJunitKtx = "1.1.3"
    const val testRoom = "2.1.0"

    const val buildGradle = "7.1.0-alpha05"

    const val retrofit = "2.9.0"
    const val okhttp = "5.0.0-alpha.2"

    const val glide = "4.12.0"

    const val swipeToRefresh = "1.1.0"
    const val archCore = "2.1.0"

    const val legacy = "1.0.0"
    const val lifecycle = "2.3.1"
    const val viewPager = "1.1.0-alpha01"
    const val viewPagerTransform = "1.0.0"

    const val truth = "1.0.1"
}

object Deps {
    val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"
    val retrofit = RetrofitDeps
    val android = AndroidDeps
    val coroutines = CoroutinesDeps
    val test = TestDeps
    val gradle = GradleDeps
    val hilt = HiltDeps
    val glide = GlideDeps
    val nav = NavDeps
    val room = RoomDeps
}

object AndroidDeps {
    val material = "com.google.android.material:material:${Versions.material}"
    val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    val ktx = "androidx.core:core-ktx:${Versions.ktx}"
    val appCompat = "androidx.appcompat:appcompat:${Versions.appCompat}"
    val swipeToRefresh = "androidx.swiperefreshlayout:swiperefreshlayout:${Versions.swipeToRefresh}"
    val legacySupport = "androidx.legacy:legacy-support-v4:${Versions.legacy}"
    val lifecycleLiveData = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycle}"
    val lifecycleViewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"
    val viewPager = "androidx.viewpager2:viewpager2:${Versions.viewPager}"
    val viewPagerTransform =
        "com.github.CodeBoy722:ViewPager2_Transformers:${Versions.viewPagerTransform}"
}

object HiltDeps {
    val core = "com.google.dagger:hilt-core:${Versions.hilt}"
    val coreAndroid = "com.google.dagger:hilt-android:${Versions.hilt}"
    val kapt = "com.google.dagger:hilt-android-compiler:${Versions.hilt}"
    val gradle = "com.google.dagger:hilt-android-gradle-plugin:${Versions.hiltGradle}"
    val compiler = "androidx.hilt:hilt-compiler:${Versions.hiltCompiler}"
    val viewModel = "androidx.hilt:hilt-lifecycle-viewmodel:${Versions.hiltViewModel}"
}

object GlideDeps {
    val glide = "com.github.bumptech.glide:glide:${Versions.glide}"
    val processor = "com.github.bumptech.glide:compiler:${Versions.glide}"
}

object CoroutinesDeps {
    val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
    val test = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutines}"
}

object RetrofitDeps {
    val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    val converter = "com.squareup.retrofit2:converter-moshi:${Versions.retrofit}"
    val okHttp = "com.squareup.okhttp3:okhttp:${Versions.okhttp}"
    val interceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.okhttp}"
}

object RoomDeps {
    val runtime = "androidx.room:room-runtime:${Versions.room}"
    val compiler = "androidx.room:room-compiler:${Versions.room}"
    val ktx = "androidx.room:room-ktx:${Versions.room}"
}

object NavDeps {
    val fragment = "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
    val ktx = "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"
    val safeArgs = "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.navigation}"
}

object TestDeps {
    val junit = "junit:junit:${Versions.testJunit}"
    val runner = "androidx.test:runner:${Versions.testRunner}"
    val core = "androidx.test:core:${Versions.testCore}"
    val coreExecutor = "androidx.arch.core:core-testing:${Versions.archCore}"
    val coreKtx = "androidx.test:core-ktx:${Versions.testCore}"
    val junitExt = "androidx.test.ext:junit:${Versions.testJunitExt}"
    val junitKtx = "androidx.test.ext:junit-ktx:${Versions.testJunitKtx}"
    val mockk = "io.mockk:mockk:${Versions.testMockk}"
    val room = "androidx.room:room-testing:${Versions.testRoom}"
    val hiltCore = "com.google.dagger:hilt-android-testing:${Versions.hilt}"
    val hiltCompiler = "com.google.dagger:hilt-android-compiler:${Versions.hilt}"
    val truth = "com.google.truth:truth:${Versions.truth}"
}

object GradleDeps {
    val buildGradle = "com.android.tools.build:gradle:${Versions.buildGradle}"
    val kotlinGradle = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
}
