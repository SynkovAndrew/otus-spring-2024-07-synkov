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
    dependsOn(subprojects.map { it.tasks.named("test")})
}

tasks.register("clean-root") {
    group = "build"
    description = "Run all child project clean tasks"
    dependsOn(subprojects.map { it.tasks.named("clean")})
}

tasks.register("build-root") {
    group = "build"
    description = "Run all child project jar tasks"
    dependsOn(subprojects.map { it.tasks.named("classes")})
    dependsOn(subprojects.map { it.tasks.named("testClasses")})
}