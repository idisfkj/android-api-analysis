import org.gradle.api.artifacts.dsl.RepositoryHandler
import java.net.URI

/**
 * Created by idisfkj on 2019/5/29.
 * Email : idisfkj@gmail.com.
 */
object Versions {
    const val support = "28.0.0"
    const val kotlin = "1.3.50"
    const val constraint_layout = "1.1.0-beta6"
    const val runner = "1.0.1"
    const val espresso_core = "3.0.1"
    const val junit = "4.12"
    const val gradle = "3.4.1"
    const val target_sdk = 28
    const val min_sdk = 16
    const val build_tools = "28.0.3"
    const val arch_version = "2.2.0-alpha01"
    const val arch_room_version = "2.1.0-rc01"
    const val paging_version = "2.1.0"
    const val anko_version = "0.10.8"
    const val glide = "4.8.0"
    const val retrofit = "2.6.0"
    const val okhttp_logging_interceptor = "3.9.0"
    const val rx_android = "2.0.1"
    const val rxjava2 = "2.1.3"
    const val work_version = "2.1.0"
    const val nav_version = "2.1.0-beta02"
    const val core_ktx = "1.0.0"
    const val data_store_version = "1.0.0-alpha01"
}

object Dependencies {
    const val app_compat = "com.android.support:appcompat-v7:${Versions.support}"
    const val recyclerView = "com.android.support:recyclerview-v7:${Versions.support}"
    const val kotlin_stdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"
    const val kotlin_plugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
    const val junit = "junit:junit:${Versions.junit}"
    const val constraint_layout = "com.android.support.constraint:constraint-layout:${Versions.constraint_layout}"
    const val runner = "com.android.support.test:runner:${Versions.runner}"
    const val espresso_core = "com.android.support.test.espresso:espresso-core:${Versions.espresso_core}"
    const val gradle_plugin = "com.android.tools.build:gradle:${Versions.gradle}"
    const val arch_lifecycle = "androidx.lifecycle:lifecycle-extensions:${Versions.arch_version}"
    const val arch_viewmodel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.arch_version}"
    const val arch_livedata = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.arch_version}"
    const val arch_runtime = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.arch_version}"
    const val arch_room_runtime = "androidx.room:room-runtime:${Versions.arch_room_version}"
    const val arch_room_compiler = "androidx.room:room-compiler:${Versions.arch_room_version}"
    const val arch_room = "androidx.room:room-ktx:${Versions.arch_room_version}"
    const val paging_runtime = "androidx.paging:paging-runtime:${Versions.paging_version}"
    const val anko = "org.jetbrains.anko:anko:${Versions.anko_version}"
    const val glide_runtime = "com.github.bumptech.glide:glide:${Versions.glide}"
    const val glide_compiler = "com.github.bumptech.glide:compiler:${Versions.glide}"
    const val retrofit_runtime = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val retrofit_gson = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
    const val retrofit_adapter_rxjava2 = "com.squareup.retrofit2:adapter-rxjava2:${Versions.retrofit}"
    const val okhttp_logging_interceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.okhttp_logging_interceptor}"
    const val rxjava2 = "io.reactivex.rxjava2:rxjava:${Versions.rxjava2}"
    const val rx_android = "io.reactivex.rxjava2:rxandroid:${Versions.rx_android}"
    const val work_runtime = "androidx.work:work-runtime-ktx:${Versions.work_version}"
    const val nav_fragment = "androidx.navigation:navigation-fragment-ktx:${Versions.nav_version}"
    const val nav_ui = "androidx.navigation:navigation-ui-ktx:${Versions.nav_version}"
    const val nav_safe_args_plugin = "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.nav_version}"
    const val core_ktx = "androidx.core:core-ktx:${Versions.core_ktx}"
    const val data_store = "androidx.datastore:datastore-preferences:${Versions.data_store_version}"

    val addRepos: (handler: RepositoryHandler) -> Unit = {
        it.google()
        it.jcenter()
        it.maven { url = URI("https://oss.sonatype.org/content/repositories/snapshots") }
    }
}

