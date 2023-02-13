package ua.nix.project.repository.dto.mapper;

import ua.nix.project.repository.dto.StudentDto;
import ua.nix.project.repository.entity.StudentEntity;

import java.util.ArrayList;
import java.util.List;

public class StudentMapperImpl implements StudentMapper {
    @Override
    public StudentDto toDto(StudentEntity student) {
        if (student == null)
            return null;
        StudentDto studentDto = new StudentDto();
        studentDto.setId(student.getId());
        studentDto.setName(student.getName());
        studentDto.setEmail(student.getEmail());
        return studentDto;
    }

    @Override
    public StudentEntity toEntity(StudentDto studentDto) {
        if(studentDto == null)
            return null;

        StudentEntity entity = new StudentEntity();
        entity.setName(studentDto.getName());
        entity.setEmail(studentDto.getEmail());
        return entity;
    }

    @Override
    public List<StudentDto> toDtoList(List<StudentEntity> studentsList) {
        List<StudentDto> dtoList = new ArrayList<>();
        for (int i = 0; i < studentsList.size(); i++) {
            dtoList.add(toDto(studentsList.get(i)));
        }
        return dtoList;
    }

    @Override
    public List<StudentEntity> toEntityList(List<StudentDto> studentsDtoList) {
        List<StudentEntity> entityList = new ArrayList<>();
        for (int i = 0; i < studentsDtoList.size(); i++) {
            entityList.add(toEntity(studentsDtoList.get(i)));
        }
        return entityList;
    }
}
