plugins {
    id("java")
    id("checkstyle")
}

dependencies {
    implementation("org.springframework:spring-context:6.1.11")
    testImplementation(platform("org.junit:junit-bom:5.10.3"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

checkstyle {
    config = project.resources.text
        .fromUri("https://raw.githubusercontent.com/OtusTeam/Spring/master/checkstyle.xml")
}

tasks.withType<Test> {
    useJUnitPlatform()
}

