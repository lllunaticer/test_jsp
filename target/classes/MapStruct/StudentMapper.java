package MapStruct;

import java.util.Map;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Anything that can go wrong will go wrong
 *
 * @author Xingjian LONG <longxingjian@kuaishou.com>
 * @date 2023-06-27
 */
@Mapper(imports = Sex.class)
public interface StudentMapper {
    @Mapping(target = "className", source = "classVal")
    @Mapping(target = "subject", source = "subject.name")
    @Mapping(target = "sex", expression = "java(studentEntity.getSex().name())")
    Student getModelFromEntity(StudentEntity studentEntity);

    @Mapping(target = "classVal", source = "className")
    @Mapping(target = "subject.name", source = "subject")
    @Mapping(target = "sex", expression = "java(Sex.valueOf(student.getSex()))")
    StudentEntity getEntityFromModel(Student student);

}
