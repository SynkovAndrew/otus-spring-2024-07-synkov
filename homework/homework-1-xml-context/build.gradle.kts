import com.github.jengelman.gradle.plugins.shadow.transformers.ServiceFileTransformer

plugins {
    id("java")
    id("checkstyle")
    id("io.freefair.lombok") version "8.6"
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

dependencies {
    implementation("org.springframework:spring-context:6.1.11")
    implementation("com.opencsv:opencsv:5.9")
    testImplementation(platform("org.junit:junit-bom:5.10.3"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("org.assertj:assertj-core:3.26.3")
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

        transform(ServiceFileTransformer::class.java) {
            setPath("META-INF")
            include("spring.*")
        }
        duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    }

/*    jar {
        manifest {
            attributes["Main-Class"] = "otus.spring.Application"
        }
        from(
            configurations.runtimeClasspath.get()
            .map { file -> file.takeIf { it.isDirectory } ?: zipTree(file) }
        )
        duplicatesStrategy = DuplicatesStrategy.EXCLUDE
        archiveBaseName = "homework-1-xml-context"
    }*/
}

