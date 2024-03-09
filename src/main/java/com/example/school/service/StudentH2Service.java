/*
 * You can use the following import statements
 * import org.springframework.beans.factory.annotation.Autowired;
 * import org.springframework.http.HttpStatus;
 * import org.springframework.jdbc.core.JdbcTemplate;
 * import org.springframework.stereotype.Service;
 * import org.springframework.web.server.ResponseStatusException;
 * import java.util.ArrayList;
 *
 */

// Write your code here

package com.example.school.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import com.example.school.model.StudentRowMapper;
import com.example.school.repository.StudentRepository;
import com.example.school.model.Student;
import java.util.ArrayList;
import java.util.List;

/**
 * StudentH2Service
 */
@Service
public class StudentH2Service implements StudentRepository {

    @Autowired
    private JdbcTemplate db;

    @Override
    public ArrayList<Student> getStudents() {
        List<Student> studentList = db.query("select * from student", new StudentRowMapper());
        ArrayList<Student> students = new ArrayList<>(studentList);
        return students;
    }

    @Override
    public Student getStudentById(int studentId) {
        try {
            Student student = db.queryForObject("select * from student where studentid = ?", new StudentRowMapper(),
                    studentId);
            return student;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public Student updateStudent(int studentId, Student student) {
        if (student.getStudentName() != null) {
            db.update("update student set studentname = ? where studentid = ?", student.getStudentName(), studentId);
        }
        if (student.getGender() != null) {
            db.update("update student set gender = ? where studentid = ?", student.getGender(), studentId);
        }
        if (student.getStandard() != 0) {
            db.update("update student set standard = ? where studentid = ?", student.getStandard(), studentId);
        }
        return getStudentById(studentId);
    }

    @Override
    public Student addStudent(Student student) {
        db.update("insert into student(studentname, gender, standard) values (?, ?, ?)", student.getStudentName(),
                student.getGender(), student.getStandard());

        Student savedStudent = db.queryForObject(
                "select * from student where studentname = ? and gender = ? and standard = ?",
                new StudentRowMapper(), student.getStudentName(), student.getGender(), student.getStandard());

        return savedStudent;
    }

    @Override
    public String addManyStudents(ArrayList<Student> students) {
        for (Student student : students) {
            db.update("insert into student(studentname, gender, standard) values (?, ?, ?)", student.getStudentName(),
                    student.getGender(), student.getStandard());
        }
        int n = students.size();
        String res = "Successfully added " + n + " students";
        return res;
    }

    @Override
    public void deleteStudent(int studentId) {
        db.update("delete from student where studentid = ?", studentId);
    }
}