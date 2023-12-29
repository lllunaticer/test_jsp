package MapStruct;

import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-12-11T11:32:24+0800",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 1.8.0_202 (Oracle Corporation)"
)
public class StudentMapperImpl implements StudentMapper {

    @Override
    public Student getModelFromEntity(StudentEntity studentEntity) {
        if ( studentEntity == null ) {
            return null;
        }

        Student student = new Student();

        student.setClassName( studentEntity.getClassVal() );
        student.setSubject( studentEntitySubjectName( studentEntity ) );
        student.setId( studentEntity.getId() );
        student.setName( studentEntity.getName() );

        student.setSex( studentEntity.getSex().name() );

        return student;
    }

    @Override
    public StudentEntity getEntityFromModel(Student student) {
        if ( student == null ) {
            return null;
        }

        StudentEntity studentEntity = new StudentEntity();

        studentEntity.setSubject( studentToSubjectEntity( student ) );
        studentEntity.setClassVal( student.getClassName() );
        studentEntity.setId( student.getId() );
        studentEntity.setName( student.getName() );

        studentEntity.setSex( Sex.valueOf(student.getSex()) );

        return studentEntity;
    }

    private String studentEntitySubjectName(StudentEntity studentEntity) {
        if ( studentEntity == null ) {
            return null;
        }
        SubjectEntity subject = studentEntity.getSubject();
        if ( subject == null ) {
            return null;
        }
        String name = subject.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }

    protected SubjectEntity studentToSubjectEntity(Student student) {
        if ( student == null ) {
            return null;
        }

        SubjectEntity subjectEntity = new SubjectEntity();

        subjectEntity.setName( student.getSubject() );

        return subjectEntity;
    }
}
