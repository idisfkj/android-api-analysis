import org.gradle.api.artifacts.dsl.RepositoryHandler
import java.net.URI

/**
 * Created by idisfkj on 2019/5/29.
 * Email : idisfkj@gmail.com.
 */
object Versions {
    const val kotlin = "1.5.31"
    const val constraint_layout = "2.1.3"
    const val runner = "1.1.0"
    const val espresso_core = "3.1.0"
    const val junit = "4.12"
    const val gradle = "7.0.0"
    const val target_sdk = 31
    const val min_sdk = 21
    const val build_tools = "30.0.2"
    const val arch_version = "2.4.1"
    const val arch_room_version = "2.4.1"
    const val paging_version = "2.1.0"
    const val anko_version = "0.10.8"
    const val glide = "4.8.0"
    const val retrofit = "2.6.0"
    const val okhttp_logging_interceptor = "3.9.0"
    const val rx_android = "2.0.1"
    const val rxjava2 = "2.1.3"
    const val work_version = "2.1.0"
    const val nav_version = "2.3.5"
    const val core_ktx = "1.7.0"
    const val activity_compose = "1.3.1"
    const val common_compose = "1.0.5"
    const val lifecycle_viewmodel_compose = "2.4.1"
    const val constraint_layout_compose = "1.0.0"
}

object Dependencies {
    const val kotlin_stdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"
    const val kotlin_plugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
    const val junit = "junit:junit:${Versions.junit}"
    const val constraint_layout = "com.android.support.constraint:constraint-layout:${Versions.constraint_layout}"
    const val runner = "com.android.support.test:runner:${Versions.runner}"
    const val espresso_core = "com.android.support.test.espresso:espresso-core:${Versions.espresso_core}"
    const val gradle_plugin = "com.android.tools.build:gradle:${Versions.gradle}"
    const val arch_viewmodel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.arch_version}"
    const val arch_livedata = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.arch_version}"
    const val arch_runtime = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.arch_version}"
    const val lifecycle_compiler = "androidx.lifecycle:lifecycle-compiler:${Versions.arch_version}"
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

    // compose
    const val activity_compose = "androidx.activity:activity-compose:${Versions.activity_compose}"
    const val material = "androidx.compose.material:material:${Versions.common_compose}"
    const val animation = "androidx.compose.animation:animation:${Versions.common_compose}"
    const val ui_tooling = "androidx.compose.ui:ui-tooling:${Versions.common_compose}"
    const val lifecycle_viewmode_compose = "androidx.lifecycle:lifecycle-viewmodel-compose:${Versions.lifecycle_viewmodel_compose}"
    const val ui_test = "androidx.compose.ui:ui-test-junit4:${Versions.common_compose}"
    const val compose_runtime = "androidx.compose.runtime:runtime:${Versions.common_compose}"
    const val constraint_layout_compose = "androidx.constraintlayout:constraintlayout-compose:${Versions.constraint_layout_compose}"

    val addRepos: (handler: RepositoryHandler) -> Unit = {
        it.google()
        it.jcenter()
        it.maven { url = URI("https://oss.sonatype.org/content/repositories/snapshots") }
    }
}

