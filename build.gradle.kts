import com.gradle.scan.plugin.BuildScanExtension
import com.jfrog.bintray.gradle.BintrayExtension
import org.gradle.testing.jacoco.plugins.JacocoPluginExtension
import org.gradle.api.tasks.wrapper.Wrapper.DistributionType
import org.jetbrains.kotlin.gradle.plugin.KotlinPluginWrapper
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

project.group = "io.micronaut"
project.version = "1.0.0-SNAPSHOT"
val artifacId = project.name

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
    val versions = object {
        val kotlin = "1.2.50-eap-62"
        val bintray = "1.8.0"
        val ktlint = "4.0.0"
        val buildScan = "1.13.4"
        val detekt = "1.0.0.RC7"
    }
    kotlin("jvm").version(versions.kotlin)
    kotlin("kapt").version(versions.kotlin)
    `maven-publish`
    id("com.jfrog.bintray").version(versions.bintray)
    id("org.jlleitschuh.gradle.ktlint").version(versions.ktlint)
    id("com.gradle.build-scan").version(versions.buildScan)
    id("io.gitlab.arturbosch.detekt").version(versions.detekt)
    id("jacoco")
}

repositories {
    jcenter()
    maven("https://dl.bintray.com/kotlin/kotlin-eap/")
    maven("https://oss.sonatype.org/content/groups/public/")
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib-jdk8", kotlinVersion))
    implementation(kotlin("reflect", kotlinVersion))
    implementation("io.micronaut:http-server-netty:${Versions.micronaut}")
    implementation("io.micronaut:runtime:${Versions.micronaut}")
    kapt("io.micronaut:inject-java:${Versions.micronaut}")
    testImplementation("org.junit.jupiter:junit-jupiter-api:${Versions.junit}")
    testImplementation("org.mockito:mockito-core:${Versions.mockito}")
    testImplementation("org.mockito:mockito-junit-jupiter:${Versions.mockito}")
    testImplementation("org.assertj:assertj-core:${Versions.assertj}")
    testImplementation("com.nhaarman:mockito-kotlin:${Versions.mockitoKtx}")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:${Versions.junit}")
    kaptTest("io.micronaut:inject-java:${Versions.micronaut}")
}

configure<BuildScanExtension> {
    setTermsOfServiceUrl("https://gradle.com/terms-of-service")
    setTermsOfServiceAgree("yes")
    value("Version", project.version.toString())
    link("VCS", "https://github.com/ideaplugins/micronaut-ktx")
}

configure<JacocoPluginExtension> {
    toolVersion = Versions.jacoco
}

fun Project.findStringProperty(key: String) = findProperty(key) as String?

bintray {
    user = project.findStringProperty("bintrayUser")
    key = project.findStringProperty("bintrayApiKey")
    publish = true
    //setPublications(publicationName)
    pkg(delegateClosureOf<BintrayExtension.PackageConfig> {
        repo = "micronautktx"
        name = "micronautktx"
        userOrg = "ideaplugins"
        websiteUrl = "https://github.com/ideaplugins/micronaut-ktx"
        githubRepo = "ideaplugins/micronaut-ktx"
        vcsUrl = "https://github.com/ideaplugins/micronaut-ktx"
        description = "Extensions for using Micronaut from Kotlin"
        setLabels("kotlin", "micronaut")
        setLicenses("Apache 2")
        desc = description
    })
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
        val archiveName = project.tasks["jar"].property("archiveName").toString().substringBeforeLast(".")
        destination = file("$buildDir/libs/$archiveName.pom")
    }
    withType<Wrapper> {
        gradleVersion = "4.7"
        distributionType = DistributionType.ALL
    }
}
