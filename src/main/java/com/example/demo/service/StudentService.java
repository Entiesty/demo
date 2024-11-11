package com.example.demo.service;

import com.example.demo.model.Student;

import java.util.List;

public interface StudentService {
    public List<Student> getStudents();
    public void saveStudent(Student student);
    public void deleteStudentById(Long studentId);
    public void updateStudentById(Long studentId, String name, String email);
}
