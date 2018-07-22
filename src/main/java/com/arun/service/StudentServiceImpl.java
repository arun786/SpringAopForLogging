package com.arun.service;

import com.arun.model.Student;
import org.springframework.stereotype.Service;

/**
 * Created by Adwiti on 7/22/2018.
 */
@Service
public class StudentServiceImpl implements StudentService {

    private Student student;

    @Override
    public Student getStudentBasedOnId(String id) {
        return new Student("1", "Arun", "23", "scottsdale");
    }
}
