plugins {
    id("java")
    application
}

group = "com.github.novotnyr.akka"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.typesafe.akka:akka-actor-typed_2.13:2.6.21")
    implementation("com.typesafe.akka:akka-cluster-typed_2.13:2.6.21")
    implementation("org.scala-lang:scala-library:2.13.12")
    runtimeOnly("ch.qos.logback:logback-classic:1.4.11")

    testImplementation(kotlin("test"))
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter:5.9.2")
}

tasks.test {
    useJUnitPlatform()
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(17)
    }
}

application {
    mainClass = "com.github.novotnyr.akka.Runner"
    val host: String by project
    val port: String by project
    val seedNode: String by project
    applicationDefaultJvmArgs = listOf("-Dakka.remote.artery.canonical.hostname=$host", "-Dakka.remote.artery.canonical.port=$port", "-Dakka.cluster.seed-nodes.0=akka://system@$seedNode")
}