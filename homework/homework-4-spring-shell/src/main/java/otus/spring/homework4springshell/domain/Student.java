package otus.spring.homework4springshell.domain;

public record Student(String firstName, String lastName) {

    public String getFullName() {
        return firstName + " " + lastName;
    }
}
