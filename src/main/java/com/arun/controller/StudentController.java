package com.arun.controller;

import com.arun.model.Student;
import com.arun.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Adwiti on 7/22/2018.
 */
@RestController
public class StudentController {
    @Autowired
    private StudentService studentService;

    @GetMapping("students/v1/student/{id}")
    public ResponseEntity<Student> getStudentBasedOnId(@PathVariable String id) throws Exception {
        Student studentBasedOnId = studentService.getStudentBasedOnId(id);
        return new ResponseEntity<>(studentBasedOnId, HttpStatus.OK);
    }


}

