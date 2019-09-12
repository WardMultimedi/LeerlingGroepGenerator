package be.multimedi.StudGroupGenerator.TaskGroup;

import be.multimedi.StudGroupGenerator.StudentGroup.StudentGroup;

import java.util.List;

public class TaskGroup {
    private Integer id;
    private String Name;
    private int minStudentsPerGroup;
    private List<StudentGroup> studGroups;

    public TaskGroup(String name, int minStudentsPerGroup, List<StudentGroup> studGroups) {
        Name = name;
        this.minStudentsPerGroup = minStudentsPerGroup;
        this.studGroups = studGroups;
    }

    public TaskGroup(Integer id, String name, int minStudentsPerGroup, List<StudentGroup> studGroups) {
        this(name, minStudentsPerGroup, studGroups);
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return Name;
    }

    public int getMinStudentsPerGroup() {
        return minStudentsPerGroup;
    }

    public List<StudentGroup> getStudGroups() {
        return studGroups;
    }

    @Override
    public String toString() {
        return "TaskGroup{" +
                "id=" + id +
                ", Name='" + Name + '\'' +
                ", minStudentsPerGroup=" + minStudentsPerGroup +
                ", studGroups=" + studGroups +
                '}';
    }
}
