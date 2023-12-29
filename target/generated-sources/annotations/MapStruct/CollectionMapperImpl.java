package MapStruct;

import java.util.LinkedHashMap;
import java.util.Map;
import javax.annotation.Generated;
import org.mapstruct.factory.Mappers;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-12-11T11:32:24+0800",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 1.8.0_202 (Oracle Corporation)"
)
public class CollectionMapperImpl implements CollectionMapper {

    private final StudentMapper studentMapper = Mappers.getMapper( StudentMapper.class );

    @Override
    public Map<String, Student> map(Map<String, StudentEntity> map) {
        if ( map == null ) {
            return null;
        }

        Map<String, Student> map1 = new LinkedHashMap<String, Student>( Math.max( (int) ( map.size() / .75f ) + 1, 16 ) );

        for ( java.util.Map.Entry<String, StudentEntity> entry : map.entrySet() ) {
            String key = entry.getKey();
            Student value = studentMapper.getModelFromEntity( entry.getValue() );
            map1.put( key, value );
        }

        return map1;
    }
}
