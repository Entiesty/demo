package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.model.Student;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface StudentMapper extends BaseMapper<Student> {
    @Select("SELECT * FROM student.student WHERE email = #{email}")
    public Student selectByEmail(@Param("email") String email);
}
