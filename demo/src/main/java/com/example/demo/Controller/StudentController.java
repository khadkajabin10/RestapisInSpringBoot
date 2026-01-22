package com.example.demo.Controller;

import com.example.demo.Dot.AddstudentDto;
import com.example.demo.Dot.StudentDto;
import com.example.demo.Service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequestMapping("/Students")

@RestController
@RequiredArgsConstructor
public class StudentController {

   private final StudentService studentService;
    @GetMapping("/Students")
    public ResponseEntity<List<StudentDto>> allstudentDto(){
        return ResponseEntity.ok(studentService.allstudent());
    }
    @GetMapping("/Students/{id}")
    public ResponseEntity<StudentDto> getstudentbyid(@PathVariable long id){
        return ResponseEntity.ok(studentService.getStudentById(id));
    }
    @PostMapping
    public ResponseEntity<StudentDto> createnewStudent(@RequestBody @Valid AddstudentDto addstudentDto){//addstudentDto has json now it need to sent to implement into database

        return ResponseEntity.status(HttpStatus.CREATED).body(studentService.cretenewstudent(addstudentDto));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletebyId(@PathVariable long id){
     studentService.deletebyid(id);
     return ResponseEntity.noContent().build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<StudentDto> updateallentity(@PathVariable long id,@RequestBody @Valid AddstudentDto addstudentDto){
        return ResponseEntity.ok(studentService.updateallEntity(id,addstudentDto));
    }
    @PatchMapping("/{id}")
    public ResponseEntity<StudentDto>updatepartiallyentity(@PathVariable long id,
                                                           @RequestBody Map<String,Object>updates){
        return ResponseEntity.ok(studentService.updatePartialEntity(id,updates));

    }
}
