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
@Mapper(uses = {StudentMapper.class})
public interface CollectionMapper {
    Map<String, Student> map(Map<String, StudentEntity> map);
}
