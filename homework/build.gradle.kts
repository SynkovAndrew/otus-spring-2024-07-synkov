plugins {
    id("java")
    id("checkstyle")
}

allprojects {
    group = "otus.spring"
    version = "1.0-SNAPSHOT"
}

subprojects {
    repositories {
        mavenCentral()
    }

    apply {
        plugin("java")
        plugin("checkstyle")
    }

    checkstyle {
        config = project.resources.text
            .fromUri("https://raw.githubusercontent.com/OtusTeam/Spring/master/checkstyle.xml")
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }
}