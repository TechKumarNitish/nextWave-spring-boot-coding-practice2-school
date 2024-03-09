// Write your code here
package com.example.school.repository;

import com.example.school.model.Student;
import java.util.ArrayList;

/**
 * StudentRepository
 */

public interface StudentRepository {

    ArrayList<Student> getStudents();

    Student getStudentById(int studentId);

    Student updateStudent(int studentId, Student student);

    Student addStudent(Student student);

    String addManyStudents(ArrayList<Student> students);

    void deleteStudent(int studentId);
}