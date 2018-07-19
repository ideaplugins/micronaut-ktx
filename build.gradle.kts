import com.gradle.scan.plugin.BuildScanExtension
import com.jfrog.bintray.gradle.BintrayExtension
import org.gradle.testing.jacoco.plugins.JacocoPluginExtension
import org.gradle.api.tasks.wrapper.Wrapper.DistributionType
import org.jetbrains.kotlin.gradle.plugin.KotlinPluginWrapper
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

project.group = "ar.com.agomez"
val artifacId = project.name

fun versionFor(name: String) = project.ext["${name}.version"].toString()

fun dependency(group: String, name: String) = "$group:$name:${versionFor(group)}"

val kotlinVersion = plugins.getPlugin(KotlinPluginWrapper::class.java).kotlinPluginVersion

plugins {
    val versions = object {
        val kotlin = "1.2.51"
        val bintray = "1.8.1"
        val ktlint = "4.0.0"
        val buildScan = "1.14"
        val detekt = "1.0.0.RC7-2"
        val dokka = "0.9.17"
        val coveralls = "2.8.2"
        val springRelease = "0.20.1"
    }
    kotlin("jvm").version(versions.kotlin)
    kotlin("kapt").version(versions.kotlin)
    `maven-publish`
    id("com.jfrog.bintray").version(versions.bintray)
    id("org.jlleitschuh.gradle.ktlint").version(versions.ktlint)
    id("com.gradle.build-scan").version(versions.buildScan)
    id("io.gitlab.arturbosch.detekt").version(versions.detekt)
    id("org.jetbrains.dokka").version(versions.dokka)
    id("com.github.kt3k.coveralls").version(versions.coveralls)
    id("io.spring.release").version(versions.springRelease)
    id("jacoco")
}

repositories {
    jcenter()
    maven("https://oss.sonatype.org/content/groups/public/")
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib-jdk8", kotlinVersion))
    implementation(kotlin("reflect", kotlinVersion))
    implementation(dependency("io.micronaut", "runtime"))
    kapt(dependency("io.micronaut", "inject-java"))
    testImplementation(dependency("org.junit.jupiter", "junit-jupiter-api"))
    testImplementation(dependency("org.mockito", "mockito-core"))
    testImplementation(dependency("org.mockito", "mockito-junit-jupiter"))
    testImplementation(dependency("org.assertj", "assertj-core"))
    testImplementation(dependency("com.nhaarman", "mockito-kotlin"))
    testRuntimeOnly(dependency("org.junit.jupiter", "junit-jupiter-engine"))
    kaptTest(dependency("io.micronaut", "inject-java"))
}

configure<BuildScanExtension> {
    setTermsOfServiceUrl("https://gradle.com/terms-of-service")
    setTermsOfServiceAgree("yes")
    value("Version", project.version.toString())
    link("VCS", "https://github.com/ideaplugins/micronaut-ktx")
}

configure<JacocoPluginExtension> {
    toolVersion = versionFor("org.jacoco")
}

fun Project.findStringProperty(key: String) = System.getenv(key) ?: findProperty(key) as String?

val sourcesJar by tasks.creating(Jar::class) {
    classifier = "sources"
    from(java.sourceSets["main"].allSource)
}

val dokka by tasks.getting(org.jetbrains.dokka.gradle.DokkaTask::class)

val dokkaJar by tasks.creating(Jar::class) {
    group = JavaBasePlugin.DOCUMENTATION_GROUP
    description = "Assembles Kotlin docs with Dokka"
    classifier = "javadoc"
    from(dokka)
}

publishing {
    publications.invoke {
        "mavenJava"(MavenPublication::class) {
            pom {
                licenses {
                    license {
                        name.set("The Apache License, Version 2.0")
                        url.set("https://www.apache.org/licenses/LICENSE-2.0.txt")
                    }
                }
                developers {
                    developer {
                        name.set("Alejandro Gomez")
                    }
                }
                scm {
                    url.set("https://github.com/ideaplugins/micronaut-ktx")
                }
            }
            from(components["java"])
            artifact(sourcesJar)
            artifact(dokkaJar)
        }
    }
}

bintray {
    user = project.findStringProperty("BINTRAY_USER")
    key = project.findStringProperty("BINTRAY_KEY")
    publish = true
    setPublications("mavenJava")
    pkg(delegateClosureOf<BintrayExtension.PackageConfig> {
        repo = "maven"
        name = "micronaut-ktx"
        userOrg = "agomez"
        websiteUrl = "https://github.com/ideaplugins/micronaut-ktx"
        description = "Extensions for using Micronaut from Kotlin"
        setLabels("kotlin", "micronaut")
    })
}

tasks {
    withType<KotlinCompile> {
        kotlinOptions {
            jvmTarget = "1.8"
            freeCompilerArgs = listOf("-Xjsr305=strict", "-Xprogressive", "-Xdisable-default-scripting-plugin")
        }
    }
    withType<Test> {
        useJUnitPlatform()
    }
    withType<JacocoReport> {
        reports {
            html.isEnabled = true
            xml.isEnabled = true
        }
    }
    withType<GenerateMavenPom> {
        val archiveName = project.tasks["jar"].property("archiveName").toString().substringBeforeLast(".")
        destination = file("$buildDir/libs/$archiveName.pom")
    }
    withType<Wrapper> {
        gradleVersion = "4.9"
        distributionType = DistributionType.ALL
    }
}
