package com.patagoniait.students.service;

import com.patagoniait.students.exception.NotFoundException;
import com.patagoniait.students.model.Student;
import com.patagoniait.students.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.stream.Collectors;


@Service
@Transactional
public class StudentService {

    @Autowired
    StudentRepository studentRepository;
    public Student createStudent(Student student){
    studentRepository.save(student);
        return student;
    }
    public Student findById(Long id) throws NotFoundException{
        return studentRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }
    public List<Student> findAll() {
        return studentRepository.findAll();
    }
    public Student update(Student student) throws  NotFoundException {
        Student stud = studentRepository.findById(student.getId()).orElseThrow(() -> new NotFoundException(student.getId()));
        stud.setName(student.getName());
        stud.setSurname(student.getSurname());
        stud.setBirthday(student.getBirthday());
        studentRepository.save(stud);
        return stud;
    }
    public void delete(Long id) throws NotFoundException {
        Student student = studentRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
        studentRepository.delete(student);
    }
    public Double calcAverageAge() {
        return studentRepository.findAll()
                .stream()
                .mapToDouble(student -> Period.between(student.getBirthday(), LocalDate.now()).getYears())
                .average()
                .orElse(0);
    }
    public int getOlderAge() {
        return studentRepository.findAll()
                .stream()
                .mapToInt(student -> Period.between(student.getBirthday(), LocalDate.now()).getYears())
                .max()
                .orElse(0);
    }
    public int getMinAge() {
        return studentRepository.findAll()
                .stream()
                .mapToInt(student -> Period.between(student.getBirthday(), LocalDate.now()).getYears())
                .min()
                .orElse(0);
    }
    public List<String> threeAttributes(){

        return studentRepository.findAll()
                .stream()
                .map(student -> String.join(", ",
                        student.getId().toString(), student.getSurname(), student.getName()))
                .collect(Collectors.toList());
    }
    public Double calcOldestAverage() {
        return studentRepository.findAll()
                .stream()
                .filter(student -> Period.between(student.getBirthday(), LocalDate.now()).getYears() >= 18)
                .mapToDouble(student -> Period.between(student.getBirthday(), LocalDate.now()).getYears())
                .average()
                .orElse(0);
    }
    public Double calcYoungestAverage() {
        return studentRepository.findAll()
                .stream()
                .filter(student -> Period.between(student.getBirthday(), LocalDate.now()).getYears() < 18)
                .mapToDouble(student -> Period.between(student.getBirthday(), LocalDate.now()).getYears())
                .average()
                .orElse(0);
    }
}
