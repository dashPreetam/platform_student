package dash.swastik.student_platform.repositories;

import dash.swastik.student_platform.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface studentRepository extends JpaRepository<Student,Long> {

    @Query(value = "SELECT * FROM student s WHERE s.Id = ?1", nativeQuery=true)
    public Student findById(@Param("Id") long id);


}