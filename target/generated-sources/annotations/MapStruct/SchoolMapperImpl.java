package MapStruct;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.mapstruct.factory.Mappers;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-12-11T11:32:24+0800",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 1.8.0_202 (Oracle Corporation)"
)
public class SchoolMapperImpl implements SchoolMapper {

    private final CollectionMapper collectionMapper = Mappers.getMapper( CollectionMapper.class );

    @Override
    public School map(SchoolDTO schoolDTO) {
        if ( schoolDTO == null ) {
            return null;
        }

        School school = new School();

        school.setName( schoolDTO.getNickName() );
        school.setStudentMap( collectionMapper.map( schoolDTO.getStudentEntityMap() ) );

        return school;
    }

    @Override
    public List<School> map(List<SchoolDTO> list) {
        if ( list == null ) {
            return null;
        }

        List<School> list1 = new ArrayList<School>( list.size() );
        for ( SchoolDTO schoolDTO : list ) {
            list1.add( map( schoolDTO ) );
        }

        return list1;
    }

    @Override
    public SchoolList map(SchoolDTOList list) {
        if ( list == null ) {
            return null;
        }

        SchoolList schoolList = new SchoolList();

        schoolList.setSchoolList( map( list.getSchoolDTOList() ) );

        return schoolList;
    }
}
