package com.patagoniait.students.controller;

import com.patagoniait.students.model.Student;
import com.patagoniait.students.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students/")
public class StudentController {

    @Autowired
    StudentService studentService;

    @PostMapping(value = "/create")
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        return new ResponseEntity<Student>(studentService.createStudent(student), HttpStatus.CREATED);
    }

    @GetMapping("/list")
    public ResponseEntity<List<Student>> getStudents() {
        return ResponseEntity.ok(studentService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getStudentById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(studentService.findById(id));
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateStudent(@RequestBody Student student) {
        return ResponseEntity.ok(studentService.update(student));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable("id") Long id) {
        studentService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/edadpromedio")
    public ResponseEntity<?> calcAverageAge(){
        return ResponseEntity.ok(studentService.calcAverageAge());
    }

    @GetMapping("/elmayor")
    public ResponseEntity<?> oldestStudent(){
        return ResponseEntity.ok(studentService.getOlderAge());
    }

    @GetMapping("/elmenor")
    public ResponseEntity<?> youngestStudent(){
        return ResponseEntity.ok(studentService.getMinAge());
    }

    @GetMapping("/tresatributos")
    public  ResponseEntity<List<String>> getCustomAttributesStudentsList(){
        return ResponseEntity.ok(studentService.threeAttributes());
    }

    @GetMapping("/promediomayores")
    public ResponseEntity<?> oldestAverage(){
        return ResponseEntity.ok(studentService.calcOldestAverage());
    }

    @GetMapping("/promediomenores")
    public ResponseEntity<?> youngestAverage(){
        return ResponseEntity.ok(studentService.calcYoungestAverage());
    }
}
