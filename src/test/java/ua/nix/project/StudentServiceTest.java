package ua.nix.project;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ua.nix.project.repository.StudentRepository;
import ua.nix.project.repository.entity.StudentEntity;
import ua.nix.project.service.StudentService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class StudentServiceTest {
    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentService studentService;

    private static StudentEntity student;

    @BeforeAll
    static void createStudent() {
        student = new StudentEntity();
        student.setName("Prophet");
        student.setEmail("test@mail.com");
    }

    @Test
    void shouldCreateNewStudent() {
        when(studentRepository.save(any(StudentEntity.class))).thenReturn(student);

        StudentEntity savedStudent = studentRepository.save(student);
        assertThat(savedStudent.getName()).isNotNull();
    }

    @Test
    void shouldStudentExistsInDB() {
        List<StudentEntity> studentList = new ArrayList<>();
        studentList.add(student);

        when(studentRepository.findAll()).thenReturn(studentList);

        List<StudentEntity> fetchedStudents = studentService.getStudents();
        assertThat(fetchedStudents.size()).isGreaterThan(0);
    }

    @Test
    void shouldUpdateStudent() {
        student.setEmail("new@mail.com");

        when(studentRepository.save(any(StudentEntity.class))).thenReturn(student);

        StudentEntity updatedStudent = studentRepository.save(student);

        assertThat(updatedStudent.getEmail()).isEqualTo("new@mail.com");

    }

    @Test
    void shouldDeleteStudent() {
        studentRepository.delete(student);
        verify(studentRepository, times(1)).delete(student);

    }
}