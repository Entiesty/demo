package com.example.demo.service;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.demo.mapper.StudentMapper;
import com.example.demo.model.Student;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    @Resource
    private StudentMapper studentMapper;
    @Override
    public List<Student> getStudents() {
        return studentMapper.selectList(null);
    }
    @Override
    public void saveStudent(Student student) {
        String email = student.getEmail();
        if(studentMapper.selectByEmail(email) != null) {
            throw new IllegalStateException("邮箱已有人使用！");
        }
        studentMapper.insert(student);
    }

    @Override
    public void deleteStudentById(Long studentId) {
        studentMapper.deleteById(studentId);
    }

    @Override
    @Transactional
    public void updateStudentById(Long studentId, String name, String email) {
        Student student = studentMapper.selectById(studentId);
        if (student == null) {
            throw new IllegalStateException("该学生ID不存在！");
        }
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("姓名不能为空！");
        }
        if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException("电子邮件不能为空！");
        }
        LambdaUpdateWrapper<Student> studentLambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        studentLambdaUpdateWrapper.set(Student::getName, name)
                .set(Student::getEmail, email);
        studentMapper.update(student, studentLambdaUpdateWrapper);
    }
}
