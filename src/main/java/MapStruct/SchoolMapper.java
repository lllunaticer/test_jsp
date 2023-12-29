package MapStruct;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Anything that can go wrong will go wrong
 *
 * @author Xingjian LONG <longxingjian@kuaishou.com>
 * @date 2023-06-27
 */
@Mapper(uses = CollectionMapper.class)
public interface SchoolMapper {
    @Mapping(target = "name", source = "nickName")
    @Mapping(target = "studentMap", source = "studentEntityMap")
    School map(SchoolDTO schoolDTO);

    List<School> map(List<SchoolDTO> list);

    @Mapping(target = "schoolList", source = "schoolDTOList")
    SchoolList map(SchoolDTOList list);
}
