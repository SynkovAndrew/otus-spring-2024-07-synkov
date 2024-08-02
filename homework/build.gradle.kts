allprojects {
    group = "otus.spring"
    version = "1.0-SNAPSHOT"

    repositories {
        mavenCentral()
    }
}

tasks.register("check-root") {
    group = "verification"
    description = "Run all child project check tasks"
    dependsOn(subprojects.map { it.tasks.named("check")})
}