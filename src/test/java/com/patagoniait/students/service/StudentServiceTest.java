package com.patagoniait.students.service;

import com.patagoniait.students.model.Student;
import com.patagoniait.students.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest {

    @Mock
    StudentRepository studentRepository;

    @InjectMocks
    StudentService studentService;
    private Student student;
    private Student student2;
    private Student student3;

    @BeforeEach
    void setUp() {
        student = Student.builder()
                .id(1L)
                .name("Martin")
                .surname("Marquez")
                .birthday(LocalDate.of(2000,03,03))
                .build();
        student2 = Student.builder()
                .id(2L)
                .name("Marcos")
                .surname("Perez")
                .birthday(LocalDate.of(2015,10,01))
                .build();
        student3 = Student.builder()
                .id(3L)
                .name("Carla")
                .surname("Martinez")
                .birthday(LocalDate.of(1995,12,01))
                .build();

    }

    @Test
    void createStudent() {
        given(studentRepository.save(student)).willReturn(student);

        Student savedStudent = studentService.createStudent(student);

        assertThat(savedStudent).isNotNull();
    }

    @Test
    void findById() {
        given(studentRepository.findById(1L)).willReturn(Optional.ofNullable(student));

        Student foundStudent = studentService.findById(1L);

        assertThat(foundStudent).isNotNull();
    }

    @Test
    void findAll() {
        given(studentRepository.findAll()).willReturn(List.of(student,student2,student3));

        List <Student> studentList = studentService.findAll();

        assertEquals(3, studentList.size());
    }

    @Test
    void update() {
        given(studentRepository.findById(1L)).willReturn(Optional.of(student));
        given(studentRepository.save(student)).willReturn(student);
        student.setName("Maria");
        student.setSurname("Carreras");
        student.setBirthday(LocalDate.ofEpochDay(2006-02-20));

        Student updatedStudent = studentService.update(student);

        assertThat(updatedStudent.getName()).isEqualTo("Maria");
        assertThat(updatedStudent.getSurname()).isEqualTo("Carreras");
        assertThat(updatedStudent.getBirthday()).isEqualTo(LocalDate.ofEpochDay(2006-02-20));
    }

    @Test
    void delete() {
        given(studentRepository.findById(1L)).willReturn(Optional.empty());
        willDoNothing().given(studentRepository).delete(student);

        studentRepository.delete(student);

        assertFalse(studentRepository.findById(student.getId()).isPresent());
    }

    @Test
    void calcAverageAge() {
        given(studentRepository.findAll()).willReturn(List.of(student,student2,student3));

        Double result = studentService.calcAverageAge();

        System.out.println(result);
        assertTrue(result == 18);
    }

    @Test
    void getOlderAge() {
        given(studentRepository.findAll()).willReturn(List.of(student,student2,student3));
    }

    @Test
    void getMinAge() {
        given(studentRepository.findAll()).willReturn(List.of(student,student2,student3));
    }

    @Test
    void threeAttributes() {
        given(studentRepository.findAll()).willReturn(List.of(student,student2,student3));
    }

    @Test
    void calcOldestAverage() {
        given(studentRepository.findAll()).willReturn(List.of(student,student2,student3));
    }

    @Test
    void calcYoungestAverage() {
        given(studentRepository.findAll()).willReturn(List.of(student,student2,student3));
    }
}