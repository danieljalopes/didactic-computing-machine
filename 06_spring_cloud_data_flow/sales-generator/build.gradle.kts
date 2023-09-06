plugins {
	java
	id("org.springframework.boot") version "3.1.3"
	id("io.spring.dependency-management") version "1.1.2"

	id("com.palantir.docker") version "0.35.0"
}

group = "scdf"
version = "0.0.1-SNAPSHOT"


java {
	sourceCompatibility = JavaVersion.VERSION_17
	targetCompatibility = JavaVersion.VERSION_17
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-webflux")
	compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")
    
  	testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("io.projectreactor:reactor-test")
	
}



tasks.withType<Test> {
	useJUnitPlatform()
}


docker {
	name = "${project.name}:${project.version}"
	tag("--latest", "scdf.sales-generator:latest")
	tag("--${project.version}", "scdf.sales-generator:${project.version}")
	setDockerfile(file("src/main/docker/Dockerfile"))
	//buildArgs(mapOf("JAR_FILE" to "${project.name}-${project.version}.jar"))
	copySpec.from(layout.buildDirectory.file("libs/${project.name}-${project.version}.jar"))
}



