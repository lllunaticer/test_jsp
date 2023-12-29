package JsonNodeTest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kuaishou.framework.util.ObjectMapperUtils;

/**
 * @author longxingjian <longxingjian@kuaishou.com>
 * Created on 2021-03-08
 */
public class JsonNodeTest {
    final String data = "{\"config\": {\"deps\": [], \"uuid\": \"885f1c1c-151a-4998-8b1a-81444153cceb\", \"label\": "
            + "\"Potential Users\", \"props\": {\"hide\": false, \"hint\": \"\", \"children\": [{\"deps\": [], "
            + "\"uuid\": \"4471f6b1-9f13-4800-877f-8c86444865f8\", \"label\": \"User ID\", \"props\": {\"max\": 0, "
            + "\"min\": 0, \"hide\": false, \"hint\": \"\", \"pattern\": \"\", \"autosize\": true, \"disabled\": "
            + "false, \"required\": true, \"placeholder\": \"\", \"defaultValue\": \"\", \"showWordLimit\": false}, "
            + "\"output\": \"userId\", \"component\": \"input\"}, {\"deps\": [{\"event\": \"ready\", \"listen\": "
            + "\"8d9a4358-7b2e-4f33-9777-0ef622937825\", \"source\": \"\", \"formatter\": {\"name\": [], \"extra\": "
            + "\"\"}, \"destination\": \"\"}], \"uuid\": \"8d9a4358-7b2e-4f33-9777-0ef622937825\", \"label\": "
            + "\"_potentialCategory_API\", \"props\": {\"url\": "
            + "\"/v3/oversea/api/block/common/form/getMineTreasure?bizName=potentialCategory&status=2\", \"method\": "
            + "\"GET\", \"payload\": [], \"pickKey\": \"data.options\", \"preProcessCode\": \"\", "
            + "\"postProcessCode\": \"\"}, \"output\": \"_potentialCategory_API\", \"component\": \"api\"}, "
            + "{\"deps\": [{\"event\": \"change\", \"listen\": \"8d9a4358-7b2e-4f33-9777-0ef622937825\", \"source\": "
            + "\"value\", \"formatter\": {\"name\": [\"object\", \"arrayNormalizer\"], \"extra\": "
            + "\"[{\\\"value\\\":\\\"id\\\",\\\"label\\\":\\\"potentialCategory\\\"}]\"}, \"destination\": "
            + "\"options\"}], \"uuid\": \"559a2a3a-e341-4391-8e17-c7786f9711ce\", \"label\": \"Potential Category\", "
            + "\"props\": {\"hide\": false, \"hint\": \"\", \"options\": [], \"disabled\": false, \"multiple\": "
            + "false, \"required\": true, \"clearable\": false, \"remoteApi\": \"\", \"allowCreate\": false, "
            + "\"placeholder\": \"\", \"multipleLimit\": 0}, \"output\": "
            + "\"potentialCategoryremoteDropDownComponent_potentialCategory\", \"component\": \"select\"}, {\"deps\":"
            + " [], \"uuid\": \"1d7880df-d81e-4709-925d-676d588f2631\", \"label\": \"Contact Info\", \"props\": "
            + "{\"max\": 0, \"min\": 0, \"hide\": false, \"hint\": \"\", \"pattern\": \"\", \"autosize\": true, "
            + "\"disabled\": false, \"required\": true, \"placeholder\": \"\", \"defaultValue\": \"None\", "
            + "\"showWordLimit\": false}, \"output\": \"contactInfo\", \"component\": \"input\"}, {\"deps\": [], "
            + "\"uuid\": \"55638682-67e6-4a28-adfd-8ebc0e08934f\", \"label\": \"Bucket\", \"props\": {\"hide\": "
            + "false, \"hint\": \"\", \"options\": [{\"text\": \"印度\", \"value\": 14}, {\"text\": \"印尼\", \"value\": "
            + "8}, {\"text\": \"巴西\", \"value\": 11}, {\"text\": \"巴基斯坦\", \"value\": 26}, {\"text\": \"哥伦比亚\", "
            + "\"value\": 34}, {\"text\": \"尼日利亚\", \"value\": 28}], \"disabled\": false, \"multiple\": false, "
            + "\"required\": true, \"clearable\": false, \"allowCreate\": false, \"placeholder\": \"\", "
            + "\"multipleLimit\": 0}, \"output\": \"bucketComponent_bucket\", \"component\": \"select\"}, {\"deps\": "
            + "[], \"uuid\": \"80b51ee7-eacc-4af9-b1bb-e34c6fee1f54\", \"label\": \"状态\", \"props\": {\"hide\": "
            + "false, \"hint\": \"\", \"options\": [{\"text\": \"上线\", \"value\": 2}, {\"text\": \"下线\", \"value\": "
            + "0}], \"disabled\": false, \"multiple\": false, \"required\": true, \"clearable\": false, "
            + "\"allowCreate\": false, \"placeholder\": \"\", \"multipleLimit\": 0}, \"output\": \"status\", "
            + "\"component\": \"select\"}], \"hideTitle\": false}, \"output\": \"\", \"component\": \"tree\"}, "
            + "\"_submitUrl\": \"/v3/oversea/api/block/form/potentialUsers/submit\"}\n";

    @Test
    public void testJsonNode() throws JsonProcessingException {
        getRequiredField(data);
    }

    private static void getRequiredField(String info) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode;
        try {
            rootNode = objectMapper.readValue(info, JsonNode.class);
        } catch (IOException e) {
            throw new IllegalArgumentException("模板JSON解析错误: " + info);
        }
        JsonNode node = rootNode.at("/config/props/children");
        if (node == null || !node.isArray()) {
            throw new IllegalArgumentException("模板JSON解析错误: " + info);
        }
        List<Map> list = ObjectMapperUtils.fromJSON(objectMapper.writeValueAsString(node), List.class);
        List<Pair<String, String>> requiredField = new ArrayList<>();
        for (Map map : list) {
            Map props = MapUtils.getMap(map, "props");
            if (MapUtils.getBoolean(props, "required", false)) {
                requiredField.add(Pair.of(MapUtils.getString(map, "label"), MapUtils.getString(map, "output")));
            }
        }
    }
}
