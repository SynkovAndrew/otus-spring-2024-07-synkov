plugins {
    id("java")
}

dependencies {
    implementation("org.springframework:spring-context:6.1.11")
    implementation("org.aspectj:aspectjweaver:1.9.19")
    implementation("org.aspectj:aspectjrt:1.9.19")
    testImplementation(platform("org.junit:junit-bom:5.10.3"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}
