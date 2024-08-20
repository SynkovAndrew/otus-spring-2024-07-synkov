plugins {
    id("java")
    id("checkstyle")
    id("io.freefair.lombok") version "8.6"
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

dependencies {
    implementation("org.springframework:spring-context:6.1.11")
    implementation("org.springframework:spring-core:6.1.11")
    implementation("org.springframework:spring-beans:6.1.11")

    implementation("org.aspectj:aspectjweaver:1.9.19")
    implementation("org.aspectj:aspectjrt:1.9.19")

    implementation("com.opencsv:opencsv:5.9")

    testImplementation(platform("org.junit:junit-bom:5.10.3"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("org.assertj:assertj-core:3.26.3")
    testImplementation("org.mockito:mockito-core:5.12.0")
}

checkstyle {
    config = project.resources.text
        .fromUri("https://raw.githubusercontent.com/OtusTeam/Spring/master/checkstyle.xml")
}

tasks {
    test {
        useJUnitPlatform()
    }
    shadowJar {
        manifest {
            attributes["Main-Class"] = "otus.spring.Application"
        }
        archiveClassifier = "fat"
    }
}

