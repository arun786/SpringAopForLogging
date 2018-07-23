package com.arun.service;

import com.arun.model.Student;

/**
 * Created by Adwiti on 7/22/2018.
 */
public interface StudentService {
    Student getStudentBasedOnId(String id) throws Exception;
}
