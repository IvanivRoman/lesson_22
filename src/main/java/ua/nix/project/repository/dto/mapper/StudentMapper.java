package ua.nix.project.repository.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ua.nix.project.repository.dto.StudentDto;
import ua.nix.project.repository.entity.StudentEntity;

import java.util.List;

@Mapper
public interface StudentMapper {
    StudentMapper INSTANCE = Mappers.getMapper(StudentMapper.class);

    StudentDto toDto(StudentEntity student);

    StudentEntity toEntity(StudentDto studentDto);

    List<StudentDto> toDtoList(List<StudentEntity> studentsList);

    List<StudentEntity> toEntityList(List<StudentDto> studentsDtoList);
}
