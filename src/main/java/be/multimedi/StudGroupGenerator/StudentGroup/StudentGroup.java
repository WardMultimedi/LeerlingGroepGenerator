package be.multimedi.StudGroupGenerator.StudentGroup;

import be.multimedi.StudGroupGenerator.student.Student;

import java.util.List;

public class StudentGroup {
    private Integer id;
    private List<Student> students;

    public StudentGroup(List<Student> students) {
        this.students = students;
    }

    public StudentGroup(Integer id, List<Student> students) {
        this(students);
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public List<Student> getStudents() {
        return students;
    }

    @Override
    public String toString() {
        return "StudentGroup{" +
                "id=" + id +
                ", students=" + students +
                '}';
    }
}
