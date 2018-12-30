package dash.swastik.student_platform.controllers;

import dash.swastik.student_platform.models.Student;
import dash.swastik.student_platform.repositories.studentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
//import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@RestController
public class studentController {

    @Autowired
    studentRepository repo;

    @Autowired
    private EntityManager em;

    @RequestMapping(path = "/students", method = RequestMethod.GET)
    public List<Student> viewSome(@RequestParam(value = "class", defaultValue = "-1") int cls,
                                  @Param(value = "active") Boolean active,
                                  @RequestParam(value = "admissionYearAfter", defaultValue = "0000") String yearA,
                                  @RequestParam(value = "admissionYearBefore", defaultValue = "9999") String yearB,
                                  @RequestParam(value = "pageSize", defaultValue = "20") int size,
                                  @RequestParam(value = "page", defaultValue = "1") int number){

        String s = "SELECT * FROM student s ";


        if(cls != -1) {
            s += "WHERE s.Class = :class ";
        }

        if(active != null){
            if(s.equals("SELECT * FROM student s "))
                s += "WHERE s.Active = :active ";
            else
                s += "AND s.Active = :active ";
        }

        if(yearA != "0000"){
            if(s.equals("SELECT * FROM student s "))
                s += "WHERE s.Admission_Year >= :yearAfter ";
            else
                s += "AND s.Admission_Year >= :yearAfter ";
        }

        if(yearB != "9999"){
            if(s.equals("SELECT * FROM student s "))
                s += "WHERE s.Admission_Year < :yearBefore ";
            else
                s += "AND s.Admission_Year < :yearBefore ";
        }

        Query nativeQuery = em.createNativeQuery(s);

        if(cls != -1) nativeQuery.setParameter("class", cls);
        if(active != null) nativeQuery.setParameter("active", active);
        if(yearA != "0000") nativeQuery.setParameter("yearAfter", yearA);
        if(yearB != "9999") nativeQuery.setParameter("yearBefore", yearB);

        int sizeList = nativeQuery.getResultList().size();

        if(number<=0)
            throw new IllegalArgumentException();

        number--;

        if(sizeList<=size+(number*size))
            return nativeQuery.getResultList().subList(number,sizeList);
        else
            return nativeQuery.getResultList().subList(number*size,size+(number*size));
    }


    @RequestMapping(path = "/students/{id}", method = RequestMethod.GET)
    public Student viewOne(@PathVariable("id") long id){

        Student student = repo.findById(id);

        if(student==null)
            throw new IllegalArgumentException();

        return student;

    }

    @RequestMapping(path = "/students", method = RequestMethod.POST)
    public Student addStudent(@RequestBody Student student){
        student.setActive(true);
        return repo.save(student);
    }

    @RequestMapping(path = "/students/{id}", method = RequestMethod.PATCH)
    public Student update(@PathVariable("id") long id, @RequestParam(value = "Class") int cls ){

        Student student = repo.findById(id);

        if (student==null)
            throw new IllegalArgumentException();

        student.setStudentClass(cls);
        return repo.save(student);
    }

    @RequestMapping(path = "/students/{id}", method = RequestMethod.DELETE)
    public Student setInactive(@PathVariable("id") long id){

        Student student = repo.findById(id);

        if (student==null)
            throw new IllegalArgumentException();

        student.setActive(false);
        return repo.save(student);
    }


    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(IllegalArgumentException.class)
    public void handleIllegalError(){//TODO
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IndexOutOfBoundsException.class)
    public void handleOutOfBoundError(){//TODO
    }


}

