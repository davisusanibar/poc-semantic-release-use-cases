plugins {
    java
    `maven-publish`
    signing
    id("io.github.gradle-nexus.publish-plugin") version "1.1.0"
}

group = "io.github.davisusanibar"
version = "${version}"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.7.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.7.0")
}

allprojects {
    publishing {
        publications {
            create<MavenPublication>("maven-publish") {
                from(components["java"])

                pom {
                    name.set("Java Gradle Semantic Release")
                    description.set("Implement semantic release for Java projects")
                    url.set("https://github.com/davisusanibar/poc-semantic-release-use-cases")
                    properties.set(mapOf(
                        "country" to "PE",
                        "dsusanibar.type.of" to "Java"
                    ))
                    licenses {
                        license {
                            name.set("The Apache License, Version 2.0")
                            url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
                        }
                    }
                    developers {
                        developer {
                            id.set("davisusanibar")
                            name.set("david dali susanibar arce")
                            email.set("dsusanibara@uni.pe")
                        }
                    }
                    scm {
                        connection.set("scm:git:git://github.com:davisusanibar/poc-semantic-release-use-cases.git")
                        developerConnection.set("scm:git:ssh://github.com:davisusanibar/poc-semantic-release-use-cases")
                        url.set("https://github.com/davisusanibar/poc-semantic-release-use-cases/")
                    }
                }
            }
        }
        repositories {
            maven {
                name = "local"
                val releasesRepoUrl = "$buildDir/repos/releases"
                val snapshotsRepoUrl = "$buildDir/repos/snapshots"
                url = uri(if (version.toString().endsWith("SNAPSHOT")) snapshotsRepoUrl else releasesRepoUrl)
            }
        }
    }
    nexusPublishing {
        repositories {
            create("sonatype") {
                val sonatypeUser = System.getenv("SONATYPE_USER").takeUnless { it.isNullOrEmpty() } ?: extra["SONATYPE_USER"].toString()
                val sonatypePassword = System.getenv("SONATYPE_PASSWORD").takeUnless { it.isNullOrEmpty() } ?: extra["SONATYPE_PASSWORD"].toString()
                nexusUrl.set(uri("https://s01.oss.sonatype.org/service/local/"))
                snapshotRepositoryUrl.set(uri("https://s01.oss.sonatype.org/content/repositories/snapshots/"))
                username.set(sonatypeUser)
                password.set(sonatypePassword)
            }
        }
    }
    java {
        toolchain {
            languageVersion.set(JavaLanguageVersion.of(17))
        }
        withJavadocJar()
        withSourcesJar()
    }

    signing {
        val signingKeyId = System.getenv("SIGNING_KEY_ID").takeUnless { it.isNullOrEmpty() } ?: extra["SIGNING_KEY_ID"].toString()
        val signingPassword = System.getenv("SIGNING_PASSWORD").takeUnless { it.isNullOrEmpty() } ?: extra["SIGNING_PASSWORD"].toString()
        val signingKey = System.getenv("SIGNING_KEY").takeUnless { it.isNullOrEmpty() } ?: extra["SIGNING_KEY"].toString()
        useInMemoryPgpKeys(signingKeyId, signingKey, signingPassword)
        sign(publishing.publications["maven-publish"])
    }
}
