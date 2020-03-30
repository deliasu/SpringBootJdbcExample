package net.csdcodes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JdbcStudentRepositoryImpl implements StudentRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int save(Student student) {
        return jdbcTemplate.update("INSERT INTO students (studentname, email, dob) VALUES (?, ?, ?)", student.getName(), student.getEmail(), student.getDob());
    }

    @Override
    public int update(Student student) {
        return jdbcTemplate.update("UPDATE students SET studentname = ?, email= ?, dob = ? WHERE id = ?", student.getName(), student.getEmail(), student.getDob(), student.getId());
    }

    @Override
    public int deleteById(Long id) {
        return jdbcTemplate.update("DELETE FROM students WHERE id = ?", id);
    }

    @Override
    public List<Student> findAll() {
        return jdbcTemplate.query("SELECT * FROM students", (rs, rowNum) ->
                new Student(rs.getLong("id"), rs.getString("studentname"),
                        rs.getString("email"),
                        rs.getString("dob")));
    }

    @Override
    public Student findById(Long id) {
        return jdbcTemplate.queryForObject("SELECT * FROM students WHERE id = ?", new Object[]{id}, (rs, rowNum) ->
                new Student(rs.getLong("id"), rs.getString("studentname"),
                        rs.getString("email"),
                        rs.getString("dob")) );
    }
}
