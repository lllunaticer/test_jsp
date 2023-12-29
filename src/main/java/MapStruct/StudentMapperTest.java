package MapStruct;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;

import org.junit.Test;
import org.mapstruct.factory.Mappers;

import com.kuaishou.framework.util.ObjectMapperUtils;

/**
 * Anything that can go wrong will go wrong
 *
 * @author Xingjian LONG <longxingjian@kuaishou.com>
 * @date 2023-06-27
 */
public class StudentMapperTest {
    private StudentMapper studentMapper = Mappers.getMapper(StudentMapper.class);
    private CollectionMapper collectionMapper = Mappers.getMapper(CollectionMapper.class);
    private SchoolMapper schoolMapper = Mappers.getMapper(SchoolMapper.class);

    @Test
    public void testEntityToModel() {
        StudentEntity entity = new StudentEntity();
        entity.setClassVal("X");
        entity.setName("John");
        entity.setId(1);

        SubjectEntity subject = new SubjectEntity();
        subject.setName("Computer");
        entity.setSubject(subject);

        Student model = studentMapper.getModelFromEntity(entity);

        assertEquals(entity.getClassVal(), model.getClassName());
        assertEquals(entity.getName(), model.getName());
        assertEquals(entity.getId(), model.getId());
        assertEquals(entity.getSubject().getName(), model.getSubject());
    }

    @Test
    public void testModelToEntity() {
        Student model = new Student();
        model.setId(1);
        model.setName("John");
        model.setClassName("X");
        model.setSubject("Science");
        StudentEntity entity = studentMapper.getEntityFromModel(model);
        assertEquals(entity.getClassVal(), model.getClassName());
        assertEquals(entity.getName(), model.getName());
        assertEquals(entity.getId(), model.getId());
        assertEquals(entity.getSubject().getName(), model.getSubject());
    }

    @Test
    public void testCollectionMapper() {
        StudentEntity entity = new StudentEntity();
        entity.setClassVal("X");
        entity.setName("John");
        entity.setId(1);

        SubjectEntity subject = new SubjectEntity();
        subject.setName("Computer");
        entity.setSubject(subject);

        Map<String, StudentEntity> map = new HashMap<>();
        map.put("John", entity);
        Map<String, Student> afterMap = collectionMapper.map(map);
        System.out.println(ObjectMapperUtils.toJSON(afterMap));
    }

    @Test
    public void testNestedMapper() {
        StudentEntity entity = new StudentEntity();
        entity.setClassVal("X");
        entity.setName("John");
        entity.setId(1);

        SubjectEntity subject = new SubjectEntity();
        subject.setName("Computer");
        entity.setSubject(subject);

        Map<String, StudentEntity> map = new HashMap<>();
        map.put("John", entity);
        SchoolDTO schoolDTO = new SchoolDTO();
        schoolDTO.setStudentEntityMap(map);
        schoolDTO.setNickName("longxingjian");
        School school = schoolMapper.map(schoolDTO);
        System.out.println(ObjectMapperUtils.toJSON(school));
    }

    @Test
    public void testEnum() {
        StudentEntity entity = new StudentEntity();
        entity.setClassVal("X");
        entity.setName("John");
        entity.setId(1);

        SubjectEntity subject = new SubjectEntity();
        subject.setName("Computer");
        entity.setSubject(subject);
        entity.setSex(Sex.MALE);

        Student modelFromEntity = studentMapper.getModelFromEntity(entity);
        System.out.println(ObjectMapperUtils.toJSON(modelFromEntity));
    }

    @Test
    public void testNestList() {
        SchoolDTOList schoolDTOList = new SchoolDTOList();

        StudentEntity entity = new StudentEntity();
        entity.setClassVal("X");
        entity.setName("John");
        entity.setId(1);
        entity.setSex(Sex.MALE);
        SubjectEntity subject = new SubjectEntity();
        subject.setName("Computer");
        entity.setSubject(subject);

        Map<String, StudentEntity> map = new HashMap<>();
        map.put("John", entity);
        SchoolDTO schoolDTO = new SchoolDTO();
        schoolDTO.setStudentEntityMap(map);
        schoolDTO.setNickName("longxingjian");
        schoolDTOList.setSchoolDTOList(Collections.singletonList(schoolDTO));

        SchoolList schoolList = schoolMapper.map(schoolDTOList);
        System.out.println(ObjectMapperUtils.toJSON(schoolList));
    }

    public Integer test(BiFunction<String, String, Integer> function) {
        return function.apply("1", "2");
    }

    @Test
    public void testFunction() {
        test((x, y) -> Integer.parseInt(x) + Integer.parseInt(y));
    }

    public Function<String, Integer> getFunction() {
        return Integer::valueOf;
    }
}
