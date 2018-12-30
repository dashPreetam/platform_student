package dash.swastik.student_platform.models;

import javax.persistence.*;

@Entity
@Table(name="Student")
public class Student {

    @Id
    @GeneratedValue
    @Column(name="ID")
    Long Id;

    @Column(name="Name")
    String Name;

    @Column(name = "Class")
    int StudentClass;

    @Column(name="Active")
    boolean Active;

    @Column(name = "AdmissionYear")
    String AddmissionYear;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getStudentClass() {
        return StudentClass;
    }

    public void setStudentClass(int studentClass) {
        StudentClass = studentClass;
    }

    public boolean isActive() {
        return Active;
    }

    public void setActive(boolean active) {
        Active = active;
    }

    public String getAddmissionYear() {
        return AddmissionYear;
    }

    public void setAddmissionYear(String addmissionYear) {
        AddmissionYear = addmissionYear;
    }
}
