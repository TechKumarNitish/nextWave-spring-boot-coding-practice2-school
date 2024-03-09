/*
 *
 * You can use the following import statemets
 *
 * import org.springframework.web.bind.annotation.*;
 * import java.util.*;
 *
 */
package com.example.school.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;

import com.example.school.service.StudentH2Service;
import com.example.school.model.Student;
import com.example.school.service.StudentH2Service;

/**
 * StudentController
 */
@RestController
public class StudentController {

    @Autowired
    public StudentH2Service studentH2Service;

    @GetMapping("/")
    public String welcome() {
        return "Welcome";
    }

    @GetMapping("/students")
    public ArrayList<Student> getStudents() {
        return studentH2Service.getStudents();
    }

    @PostMapping("/students")
    public Student addStudent(@RequestBody Student stduent) {
        return studentH2Service.addStudent(stduent);
    }

    @PostMapping("/students/bulk")
    public String addManyStudents(@RequestBody ArrayList<Student> students){
        return studentH2Service.addManyStudents(students);
    }

    @GetMapping("/students/{studentId}")
    public Student getStudentById(@PathVariable("studentId") int studentId) {
        return studentH2Service.getStudentById(studentId);
    }

    @PutMapping("/students/{studentId}")
    public Student updatStudent(@PathVariable("studentId") int studentId, @RequestBody Student student) {
        return studentH2Service.updateStudent(studentId, student);
    }

    @DeleteMapping("/students/{studentId}")
    public void deleteStudent(@PathVariable("studentId") int studentId) {
        studentH2Service.deleteStudent(studentId);
    }

}
