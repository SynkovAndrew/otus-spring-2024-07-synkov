package otus.spring.homework3springboot.domain;

public record Student(String firstName, String lastName) {

    public String getFullName() {
        return firstName + " " + lastName;
    }
}
