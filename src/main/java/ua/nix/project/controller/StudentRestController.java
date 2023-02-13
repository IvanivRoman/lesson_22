package ua.nix.project.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.nix.project.repository.dto.StudentDto;
import ua.nix.project.repository.dto.mapper.StudentMapper;
import ua.nix.project.repository.entity.StudentEntity;
import ua.nix.project.service.StudentService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class StudentRestController {
    @Autowired
    private final StudentService studentService;

    @PostMapping("/add")
    public ResponseEntity<StudentDto> createStudent(@RequestBody StudentDto studentDto) {

        StudentEntity student = StudentMapper.INSTANCE.toEntity(studentDto);

        StudentDto responseDto = StudentMapper.INSTANCE.toDto(
                studentService.createStudent(student));

        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<StudentDto> updateStudent(@RequestBody StudentDto studentDto) {

        StudentEntity student = StudentMapper.INSTANCE.toEntity(studentDto);

        StudentDto responseDto = StudentMapper.INSTANCE.toDto(studentService.updateStudent(student));

        return ResponseEntity.ok(responseDto);
    }

    @DeleteMapping("/delete/{Id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long Id) {

        studentService.deleteStudent(Id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{Id}")
    public ResponseEntity<StudentDto> getStudent(@PathVariable Long Id) {

        StudentDto responseDto = StudentMapper.INSTANCE.toDto(studentService.getStudent(Id));
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @GetMapping("/students")
    public ResponseEntity<List<StudentDto>> getStudents() {

        List<StudentDto> responseDto = StudentMapper.INSTANCE.toDtoList(studentService.getStudents());
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }
}
