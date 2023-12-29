package JsonMapperTest;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.MapUtils;
import org.springframework.util.Assert;

import com.kuaishou.framework.util.ObjectMapperUtils;

/**
 * @author longxingjian <longxingjian@kuaishou.com>
 * Created on 2021-04-28
 */
public class JsonMapperTest {
    public static void main(String[] args) {
        String content = "{\"deps\": [\n"
                + "                            {\n"
                + "                                \"event\": \"change\",\n"
                + "                                \"listen\": \"b44ef910-218a-11eb-886c-93daa953c945\",\n"
                + "                                \"source\": \"value\",\n"
                + "                                \"formatter\": {\n"
                + "                                    \"name\": [\n"
                + "                                        \"object\",\n"
                + "                                        \"arrayNormalizer\"\n"
                + "                                    ],\n"
                + "                                    \"extra\": \"[{\\\"value\\\":\\\"bucketValue\\\","
                + "\\\"label\\\":\\\"bucketName\\\"}]\"\n"
                + "                                },\n"
                + "                                \"destination\": \"options\"\n"
                + "                            }\n"
                + "                        ]}";

        Map map = ObjectMapperUtils.fromJSON(content, Map.class);
        List<Map<String, Object>> deps = (List<Map<String, Object>>) MapUtils.getObject(map, "deps");
        Assert.isTrue(deps.size() > 0, "该表单的远程下拉选项配置错误，请联系开发配置。业务名:");
        Map<String, Object> depsMap = deps.get(0);
        Map formatter = MapUtils.getMap(depsMap, "formatter");
        List<Map> optionsInExtra =
                ObjectMapperUtils.fromJSON((String) formatter.get("extra"), List.class, Map.class);
        Assert.isTrue(optionsInExtra.size() > 0,
                "该表单的远程下拉选项配置错误, 未配置value/label，请联系开发配置。业务名:");
        Map<String, String> valueLabelMap = (Map<String, String>) optionsInExtra.get(0);
        String valueKeyName = valueLabelMap.get("value");
        String labelKeyName = valueLabelMap.get("label");
        System.out.println(valueKeyName);
        System.out.println(labelKeyName);
    }
}
