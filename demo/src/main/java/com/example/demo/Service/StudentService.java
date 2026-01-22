package com.example.demo.Service;

import com.example.demo.Dot.AddstudentDto;
import com.example.demo.Dot.StudentDto;
import org.jspecify.annotations.Nullable;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface StudentService {
    List<StudentDto> allstudent();

    StudentDto getStudentById(long id);

     StudentDto cretenewstudent(AddstudentDto addstudentDto);
      Void  deletebyid(long id);

    StudentDto updateallEntity(long id, AddstudentDto addstudentDto);

     StudentDto updatePartialEntity(long id, Map<String, Object> updates);
}
