package com.college.students.service;

import com.college.students.dto.StudentDTO;
import com.college.students.entity.Student;
import com.college.students.exception.StudentNotFoundException;
import com.college.students.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {

    @Autowired
    private StudentRepository repo;

    public StudentDTO save(StudentDTO dto) {
        Student student = mapToEntity(dto);
        Student saved = repo.save(student);
        return mapToDTO(saved);
    }

    public List<StudentDTO> getAll() {
        return repo.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public StudentDTO calculateAndUpdateAge(Long id) {
        Student student = repo.findById(id)
                .orElseThrow(() -> new StudentNotFoundException(id));
        LocalDate dob = LocalDate.of(student.getYear(), student.getMonth(), student.getDay());
        student.setAge(Period.between(dob, LocalDate.now()).getYears());
        Student updated = repo.save(student);
        return mapToDTO(updated);
    }

    public List<StudentDTO> getBetween18And25() {
        return repo.findByAgeBetween(18, 25)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    private StudentDTO mapToDTO(Student s) {
        StudentDTO dto = new StudentDTO();
        dto.setId(s.getId());
        dto.setName(s.getName());
        dto.setDay(s.getDay());
        dto.setMonth(s.getMonth());
        dto.setYear(s.getYear());
        dto.setAge(s.getAge());
        return dto;
    }

    private Student mapToEntity(StudentDTO dto) {
        Student s = new Student();
        s.setName(dto.getName());
        s.setDay(dto.getDay());
        s.setMonth(dto.getMonth());
        s.setYear(dto.getYear());
        s.setAge(dto.getAge());
        return s;
    }
}
