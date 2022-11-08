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
            create<MavenPublication>("mavendavisusanibar") {
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
//            maven {
//                name = "github"
//                url = uri("https://github.com/davisusanibar/poc-semantic-release-use-cases")
//                credentials {
//                    username = System.getenv("GITHUB_ACTOR").takeUnless { it.isNullOrEmpty() } ?: extra["GITHUB_ACTOR"].toString()
//                    password = System.getenv("GITHUB_TOKEN").takeUnless { it.isNullOrEmpty() } ?: extra["GITHUB_TOKEN"].toString()
//                }
//            }
//            maven {
//                name = "sonatypeLocal"
//                val sonatypeUser = System.getenv("sonatype_user").takeUnless { it.isNullOrEmpty() } ?: extra["sonatype_user"].toString()
//                val sonatypePassword = System.getenv("sonatype_password").takeUnless { it.isNullOrEmpty() } ?: extra["sonatype_password"].toString()
//                val releasesRepoUrl = "http://localhost:8081/repository/maven-releases/"
//                val snapshotsRepoUrl = "http://localhost:8081/repository/maven-snapshots/"
//                isAllowInsecureProtocol = true
//                url = uri(if (version.toString().endsWith("SNAPSHOT")) snapshotsRepoUrl else releasesRepoUrl)
//                credentials {
//                    username = sonatypeUser
//                    password = sonatypePassword
//                }
//            }

            maven {
                name = "sonatypeCloud"
                val sonatypeUser = System.getenv("sonatype_user").takeUnless { it.isNullOrEmpty() } ?: extra["sonatype_user"].toString()
                val sonatypePassword = System.getenv("sonatype_password").takeUnless { it.isNullOrEmpty() } ?: extra["sonatype_password"].toString()
                val releasesRepoUrl = "https://s01.oss.sonatype.org/service/local/"
                val snapshotsRepoUrl = "https://s01.oss.sonatype.org/content/repositories/snapshots/"
                url = uri(if (version.toString().endsWith("SNAPSHOT")) snapshotsRepoUrl else releasesRepoUrl)
                credentials {
                    username = sonatypeUser
                    password = sonatypePassword
                }
            }
        }
    }
//    nexusPublishing {
//        repositories {
//            create("sonatype") {
//                val sonatypeUser = System.getenv("sonatype_user").takeUnless { it.isNullOrEmpty() } ?: extra["sonatype_user"].toString()
//                val sonatypePassword = System.getenv("sonatype_password").takeUnless { it.isNullOrEmpty() } ?: extra["sonatype_password"].toString()
//                nexusUrl.set(uri("https://s01.oss.sonatype.org/service/local/"))
//                snapshotRepositoryUrl.set(uri("https://s01.oss.sonatype.org/content/repositories/snapshots/"))
//                username.set(sonatypeUser)
//                password.set(sonatypePassword)
//            }
//        }
//    }
    java {
        toolchain {
            languageVersion.set(JavaLanguageVersion.of(17))
        }
        withJavadocJar()
        withSourcesJar()
    }
//    signing {
//        sign(publishing.publications["mavendavisusanibar"])
//    }
}
