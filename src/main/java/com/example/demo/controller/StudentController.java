package com.example.demo.controller;

import com.example.demo.model.Student;
import com.example.demo.service.StudentService;
import jakarta.annotation.Resource;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("controller/v1/student")
public class StudentController {
    @Resource
    private StudentService studentService;

    @GetMapping
    public List<Student> getStudent() {
        return studentService.getStudents();
    }

    @PostMapping
    public void saveStudent(@RequestBody Student student) {
        studentService.saveStudent(student);
    }

    @DeleteMapping(path = "{studentId}")
    public void deleteByStudentId(@PathVariable Long studentId) {
        studentService.deleteStudentById(studentId);
    }

    @PutMapping(path = "{studentId}")
    @Transactional
    public void updateByStudentId(@PathVariable Long studentId,
                                  @RequestParam String name, @RequestParam String email) {
        studentService.updateStudentById(studentId, name, email);
    }
}
