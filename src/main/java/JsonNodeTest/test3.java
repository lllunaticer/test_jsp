package JsonNodeTest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;

/**
 * @author longxingjian <longxingjian@kuaishou.com>
 * Created on 2021-05-24
 */
public class test3 {
    public static void main(String[] args) {
        String jsonData = "[]";
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode;
        try {
            rootNode = objectMapper.readValue(jsonData, JsonNode.class);
        } catch (IOException e) {
            throw new UnsupportedOperationException("kconf 内容解析错误");
        }
        List<String> treasuresStr = new ArrayList<>();
        if (rootNode.isObject()) {
            treasuresStr.add(rootNode.toString());
        } else if (rootNode.isArray()) {
            ArrayNode arrayNode = (ArrayNode) rootNode;
            System.out.println(rootNode.asText());
            JsonNode arrayChild = arrayNode.get(0);
            if (!arrayChild.isObject()) {
                throw new UnsupportedOperationException("非数组字典类型, json:" + jsonData);
            }
            arrayNode.forEach(node -> treasuresStr.add(node.toString()));
            System.out.println(treasuresStr);
        }
    }
}
