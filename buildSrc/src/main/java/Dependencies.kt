/**
 * To define dependencies
 */
object Dependencies {

    // Core Android
    val coreAndroidX by lazy {"androidx.core:core-ktx:${Versions.coreAndroidX}"}
    val appCompat by lazy {"androidx.appcompat:appcompat:${Versions.appCompat}"}
    val material by lazy {"com.google.android.material:material:${Versions.material}"}
    val constraintLayout by lazy {"androidx.constraintlayout:constraintlayout:${Versions.contraintLayout}"}
    val junit by lazy {"junit:junit:${Versions.junit}"}
    val testjunit by lazy {"androidx.test.ext:junit:${Versions.testjunit}"}
    val espressoCore by lazy {"androidx.test.espresso:espresso-core:${Versions.espressoCore}"}

    // Lifecycle components
    val liveData by lazy {"androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycle}"}
    val viewModel by lazy {"androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"}

    // Hilt
    val hiltAndroid by lazy { "com.google.dagger:hilt-android:${Versions.hilt}" }
    val hiltAndroidCompiler by lazy { "com.google.dagger:hilt-android-compiler:${Versions.hilt}" }

    // Coroutine
    val workRuntime by lazy { "androidx.work:work-runtime-ktx:${Versions.workRuntime}" }

    // Room
    val roomRuntine by lazy {"androidx.room:room-runtime:${Versions.room}"}
    val roomKtx by lazy {"androidx.room:room-ktx:${Versions.room}"}
    val roomCompiler by lazy {"androidx.room:room-compiler:${Versions.room}"}

    // GSon
    val gson by lazy {"com.google.code.gson:gson:${Versions.gson}"}

    // Navigation
    val navFragment by lazy {"androidx.navigation:navigation-fragment-ktx:${Versions.navFragment}"}

    // Glide
    val glideCompiler by lazy {"com.github.bumptech.glide:compiler:${Versions.glide}"}
    val glide by lazy {"com.github.bumptech.glide:glide:${Versions.glide}"}
}
