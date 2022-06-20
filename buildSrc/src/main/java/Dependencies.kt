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

    // Hilt
    val hiltAndroid by lazy { "com.google.dagger:hilt-android:${Versions.hilt}" }
    val hiltAndroidCompiler by lazy { "com.google.dagger:hilt-android-compiler:${Versions.hilt}" }

    // Coroutine
    val workRuntime by lazy { "androidx.work:work-runtime-ktx:${Versions.workRuntime}" }
}
