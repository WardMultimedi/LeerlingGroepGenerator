package be.multimedi;

public class Student {
    private Integer id;
    private String firstName;
    private String lastName;
    //private String street;
    //private String zipcode;
    //private String city;


    public Student(String firstName, String lastName) {
        //TODO Validation
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Integer getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public String toString() {
        return "Student{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}