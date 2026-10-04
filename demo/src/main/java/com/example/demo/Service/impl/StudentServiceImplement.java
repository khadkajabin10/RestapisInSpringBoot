package com.example.demo.Service.impl;

import com.example.demo.Dot.AddstudentDto;
import com.example.demo.Dot.StudentDto;
import com.example.demo.Entity.Student;
import com.example.demo.Repository.StudentRepository;
import com.example.demo.Service.StudentService;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.modelmapper.ModelMapper;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;

@Service
@RequiredArgsConstructor
public class StudentServiceImplement implements StudentService {
private final StudentRepository studentRepository;
private final ModelMapper modelMapper;
private final String CACHE_NAME="Student";
    @Override
    public List<StudentDto> allstudent() {
        List<Student> students=studentRepository.findAll();
       List<StudentDto> allstudentdto= students.stream()
               .map(Student->modelMapper.map(Student,StudentDto.class))
               .toList();
       return allstudentdto;
    }

    @Override
    @Cacheable(cacheNames=CACHE_NAME,key="#id")
    public StudentDto getStudentById(long id) {
       Student student=studentRepository.findById(id)
               .orElseThrow(()-> new IllegalArgumentException("Student not found with this "+id));
       StudentDto studentDto=modelMapper.map(student,StudentDto.class);
       return  studentDto;
    }

    @Override
    @CachePut(cacheNames = CACHE_NAME,key = "{#result.id}")
    public StudentDto cretenewstudent(AddstudentDto addstudentDto) {
        Student newstudent=modelMapper.map(addstudentDto,Student.class);
      Student student=  studentRepository.save(newstudent);
     return modelMapper.map(student,StudentDto.class);


    }

    @Override
    @CacheEvict(cacheNames = CACHE_NAME,key ="#id")
    public Void deletebyid(long id) {
        if(studentRepository.existsById(id)){
            studentRepository.deleteById(id);
        }
        else{
            throw new  IllegalArgumentException("Id didnt found.");}
        return null;
    }

    @Override
    @CachePut(cacheNames = CACHE_NAME,key = "#id")
//here you need to update all field
    public StudentDto updateallEntity(long id, AddstudentDto addstudentDto) {
        Student student=studentRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("Student not found with this "+id));
        modelMapper.map(addstudentDto,student);
      Student newstudent= studentRepository.save(student);

       return modelMapper.map(newstudent,StudentDto.class);

    }

    @Override
    @CachePut(cacheNames = CACHE_NAME,key = "#id")
//here you update parital things
    public StudentDto updatePartialEntity(long id, Map<String, Object> updates) {
        Student student=studentRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("Student not found with this "+id));
       updates.forEach((field,value)->{
           switch (field){
               case "name":
                   if(!(((String) value).length() >= 2 || ((String) value).length() <= 15)){
                       throw new IllegalArgumentException("invalid name size");
                   }
                   student.setName((String) value);
               break;
               case "email":
                   if(!((String)value).contains("@")){
                       throw new IllegalArgumentException("Invalid email ");
                   }
                   student.setEmail((String) value);
               break;
               default:
                   throw new IllegalArgumentException("Enter correct field:");
           }
       });
        Student newstudent= studentRepository.save(student);

        return modelMapper.map(newstudent,StudentDto.class);

    }

}
