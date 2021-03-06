package com.arun.service;

import com.arun.config.RsLogging;
import com.arun.model.Student;
import org.springframework.stereotype.Service;

/**
 * Created by Adwiti on 7/22/2018.
 */
@Service
public class StudentServiceImpl implements StudentService {

    private Student student;

    @Override
    @RsLogging
    public Student getStudentBasedOnId(String id) throws Exception {
        Student student = new Student("1", "Arun", "23", "scottsdale");
        if (id.equals("2")) {
            throw new Exception("Test Exception");
        }
        return student;
    }
}
