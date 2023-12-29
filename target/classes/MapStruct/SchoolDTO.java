package MapStruct;

import lombok.Data;

import java.util.Map;

/**
 * Anything that can go wrong will go wrong
 *
 * @author Xingjian LONG <longxingjian@kuaishou.com>
 * @date 2023-06-27
 */
@Data
public class SchoolDTO {
    private Map<String, StudentEntity> studentEntityMap;
    private String nickName;
}
