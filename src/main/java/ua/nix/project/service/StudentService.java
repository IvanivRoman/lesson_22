package ua.nix.project.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.nix.project.repository.StudentRepository;
import ua.nix.project.repository.entity.StudentEntity;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;

    public void createStudent(String name, String email) {

        StudentEntity studentEntity = new StudentEntity();
        studentEntity.setName(name);
        studentEntity.setEmail(email);

        studentRepository.save(studentEntity);
    }

    public StudentEntity createStudent(StudentEntity student) {
        return studentRepository.save(student);
    }

    public StudentEntity updateStudent(StudentEntity student) {
        StudentEntity entity = studentRepository.findById(student.getId()).orElseThrow();

        if (student.getName() != null) {
            entity.setName(student.getName());
        }

        if (student.getEmail() != null) {
            entity.setEmail(student.getEmail());
        }

        return studentRepository.save(entity);
    }

    public List<StudentEntity> getStudents() {
        return studentRepository.findAll();
    }

    public StudentEntity getStudent(Long id) {
        return studentRepository.findById(id).orElseThrow();
    }

    public void deleteStudent(Long id) {
        StudentEntity entity = studentRepository.findById(id).orElseThrow();
        studentRepository.delete(entity);
    }

}
