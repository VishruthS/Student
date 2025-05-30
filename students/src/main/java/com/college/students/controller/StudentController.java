package com.college.students.controller;

import com.college.students.dto.StudentDTO;
import com.college.students.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService service;

    @PostMapping
    public ResponseEntity<StudentDTO> create(@RequestBody StudentDTO dto) {
        return ResponseEntity.ok(service.save(dto));
    }

    @GetMapping
    public ResponseEntity<List<StudentDTO>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @PutMapping("/{id}/calculate-age")
    public ResponseEntity<StudentDTO> calculateAge(@PathVariable Long id) {
        return ResponseEntity.ok(service.calculateAndUpdateAge(id));
    }

    @GetMapping("/age-range")
    public ResponseEntity<List<StudentDTO>> getByAgeRange() {
        return ResponseEntity.ok(service.getBetween18And25());
    }
}
