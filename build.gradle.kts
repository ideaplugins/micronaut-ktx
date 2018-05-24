import com.gradle.scan.plugin.BuildScanExtension
import org.gradle.testing.jacoco.plugins.JacocoPluginExtension
import org.gradle.api.tasks.wrapper.Wrapper.DistributionType
import org.jetbrains.kotlin.gradle.plugin.KotlinPluginWrapper
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val kotlinVersion = plugins.getPlugin(KotlinPluginWrapper::class.java).kotlinPluginVersion

object Versions {
    const val micronaut = "1.0.0-SNAPSHOT"
    const val junit = "5.2.0"
    const val mockito = "2.18.3"
    const val assertj = "3.10.0"
    const val mockitoKtx = "1.5.0"
    const val jacoco = "0.8.1"
}

plugins {
    val kotlinVersion = "1.2.50-eap-17"
    kotlin("jvm").version(kotlinVersion)
    kotlin("kapt").version(kotlinVersion)
    `maven-publish`
    id("com.jfrog.bintray").version("1.8.0")
    id("org.jlleitschuh.gradle.ktlint").version("4.0.0")
    id("com.gradle.build-scan").version("1.13.4")
    id("jacoco")
}

repositories {
    jcenter()
    maven("https://dl.bintray.com/kotlin/kotlin-eap/")
    mavenCentral()
    mavenLocal()
}

dependencies {
    compile(kotlin("stdlib-jdk8", kotlinVersion))
    compile(kotlin("reflect", kotlinVersion))
    compile("io.micronaut:http-server-netty:${Versions.micronaut}")
    compile("io.micronaut:runtime:${Versions.micronaut}")
    kapt("io.micronaut:inject-java:${Versions.micronaut}")
    testCompile("org.junit.jupiter:junit-jupiter-api:${Versions.junit}")
    testCompile("org.mockito:mockito-core:${Versions.mockito}")
    testCompile("org.mockito:mockito-junit-jupiter:${Versions.mockito}")
    testCompile("org.assertj:assertj-core:${Versions.assertj}")
    testCompile("com.nhaarman:mockito-kotlin:${Versions.mockitoKtx}")
    testRuntime("org.junit.jupiter:junit-jupiter-engine:${Versions.junit}")
    kaptTest("io.micronaut:inject-java:${Versions.micronaut}")
}

configure<BuildScanExtension> {
    setLicenseAgreementUrl("https://gradle.com/terms-of-service")
    setLicenseAgree("yes")
    value("Version", project.version.toString())
    link("VCS", "https://github.com/ideaplugins/micronaut-ktx")
}

configure<JacocoPluginExtension> {
    toolVersion = Versions.jacoco
}

tasks {
    withType<KotlinCompile> {
        kotlinOptions {
            jvmTarget = "1.8"
            freeCompilerArgs = listOf("-Xjsr305=strict")
        }
    }
    withType<Test> {
        useJUnitPlatform()
    }
    withType<GenerateMavenPom> {
        //destination = file("$buildDir/libs/${jar.archiveName}.pom")
    }
    withType<Wrapper> {
        gradleVersion = "4.7"
        distributionType = DistributionType.ALL
    }
}
