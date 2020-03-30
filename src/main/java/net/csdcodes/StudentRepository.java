package net.csdcodes;

import java.util.List;

public interface StudentRepository {
    int save(Student student);
    int update(Student student);
    int deleteById(Long id);
    List<Student> findAll();
    Student findById(Long id);
}
