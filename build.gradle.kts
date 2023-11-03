plugins {
    id("java")
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