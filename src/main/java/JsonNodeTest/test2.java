package JsonNodeTest;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.kuaishou.framework.util.ObjectMapperUtils;

/**
 * @author longxingjian <longxingjian@kuaishou.com>
 * Created on 2021-04-29
 */
public class test2 {
    public static void main(String[] args) throws IOException {
        String content = "{\n"
                + "  \"config\": {\n"
                + "    \"deps\": [],\n"
                + "    \"uuid\": \"2b8b0010-2167-11eb-8123-27820a775376\",\n"
                + "    \"label\": \"添加官方主播\",\n"
                + "    \"props\": {\n"
                + "      \"hide\": false,\n"
                + "      \"hideTitle\": false,\n"
                + "      \"hint\": \"\",\n"
                + "      \"children\": [\n"
                + "        {\n"
                + "          \"deps\": [\n"
                + "            {\n"
                + "              \"event\": \"change\",\n"
                + "              \"listen\": \"b44ef910-218a-11eb-886c-93daa953c945\",\n"
                + "              \"source\": \"value\",\n"
                + "              \"formatter\": {\n"
                + "                \"name\": [\n"
                + "                  \"object\",\n"
                + "                  \"arrayNormalizer\"\n"
                + "                ],\n"
                + "                \"extra\": \"[{\\\"value\\\":\\\"bucketValue\\\","
                + "\\\"label\\\":\\\"bucketName\\\"}]\"\n"
                + "              },\n"
                + "              \"destination\": \"options\"\n"
                + "            }\n"
                + "          ],\n"
                + "          \"uuid\": \"8ace5c20-2167-11eb-8123-27820a775376\",\n"
                + "          \"label\": \"桶\",\n"
                + "          \"props\": {\n"
                + "            \"uniqueKey\": false,\n"
                + "            \"defaultValue\": null,\n"
                + "            \"emptyValue\": null,\n"
                + "            \"hide\": false,\n"
                + "            \"disabled\": false,\n"
                + "            \"clearable\": false,\n"
                + "            \"hint\": \"\",\n"
                + "            \"placeholder\": \"\",\n"
                + "            \"required\": true,\n"
                + "            \"validator\": null,\n"
                + "            \"multiple\": false,\n"
                + "            \"multipleLimit\": 0,\n"
                + "            \"options\": [],\n"
                + "            \"allowCreate\": false,\n"
                + "            \"remoteApi\": \"\"\n"
                + "          },\n"
                + "          \"output\": \"bucket\",\n"
                + "          \"component\": \"select\"\n"
                + "        },\n"
                + "        {\n"
                + "          \"deps\": [],\n"
                + "          \"uuid\": \"b4c72430-2167-11eb-8123-27820a775376\",\n"
                + "          \"label\": \"用户Id\",\n"
                + "          \"props\": {\n"
                + "            \"uniqueKey\": true,\n"
                + "            \"defaultValue\": 0,\n"
                + "            \"hide\": false,\n"
                + "            \"disabled\": false,\n"
                + "            \"hint\": \"\",\n"
                + "            \"placeholder\": \"\",\n"
                + "            \"controls\": false,\n"
                + "            \"nonZero\": true,\n"
                + "            \"step\": 1,\n"
                + "            \"precision\": 0,\n"
                + "            \"pattern\": \"\",\n"
                + "            \"patternMsg\": \"\",\n"
                + "            \"validator\": \"b49aa840-21af-11eb-8514-ffce075d5a1e\"\n"
                + "          },\n"
                + "          \"output\": \"userId\",\n"
                + "          \"component\": \"input-number\"\n"
                + "        },\n"
                + "        {\n"
                + "          \"deps\": [\n"
                + "            {\n"
                + "              \"event\": \"change\",\n"
                + "              \"listen\": \"91b91a10-218b-11eb-886c-93daa953c945\",\n"
                + "              \"source\": \"value\",\n"
                + "              \"formatter\": {\n"
                + "                \"name\": [\n"
                + "                  \"object\",\n"
                + "                  \"arrayNormalizer\"\n"
                + "                ],\n"
                + "                \"extra\": \"[{\\\"value\\\":\\\"category\\\",\\\"label\\\":\\\"category\\\"}]\"\n"
                + "              },\n"
                + "              \"destination\": \"options\"\n"
                + "            }\n"
                + "          ],\n"
                + "          \"uuid\": \"da81cd60-2167-11eb-8123-27820a775376\",\n"
                + "          \"label\": \"Category\",\n"
                + "          \"props\": {\n"
                + "            \"uniqueKey\": false,\n"
                + "            \"defaultValue\": null,\n"
                + "            \"emptyValue\": null,\n"
                + "            \"hide\": false,\n"
                + "            \"disabled\": false,\n"
                + "            \"clearable\": false,\n"
                + "            \"hint\": \"\",\n"
                + "            \"placeholder\": \"\",\n"
                + "            \"required\": true,\n"
                + "            \"validator\": null,\n"
                + "            \"multiple\": false,\n"
                + "            \"multipleLimit\": 0,\n"
                + "            \"options\": [],\n"
                + "            \"allowCreate\": false\n"
                + "          },\n"
                + "          \"output\": \"category\",\n"
                + "          \"component\": \"select\"\n"
                + "        },\n"
                + "        {\n"
                + "          \"deps\": [\n"
                + "            {\n"
                + "              \"event\": \"change\",\n"
                + "              \"listen\": \"b4e3a280-218b-11eb-886c-93daa953c945\",\n"
                + "              \"source\": \"value\",\n"
                + "              \"formatter\": {\n"
                + "                \"name\": [\n"
                + "                  \"object\",\n"
                + "                  \"arrayNormalizer\"\n"
                + "                ],\n"
                + "                \"extra\": \"[{\\\"value\\\":\\\"agency\\\",\\\"label\\\":\\\"agency\\\"}]\"\n"
                + "              },\n"
                + "              \"destination\": \"options\"\n"
                + "            }\n"
                + "          ],\n"
                + "          \"uuid\": \"f297e100-2167-11eb-8123-27820a775376\",\n"
                + "          \"label\": \"Agency\",\n"
                + "          \"props\": {\n"
                + "            \"uniqueKey\": false,\n"
                + "            \"defaultValue\": null,\n"
                + "            \"emptyValue\": null,\n"
                + "            \"hide\": false,\n"
                + "            \"disabled\": false,\n"
                + "            \"clearable\": false,\n"
                + "            \"hint\": \"\",\n"
                + "            \"placeholder\": \"\",\n"
                + "            \"required\": true,\n"
                + "            \"validator\": null,\n"
                + "            \"multiple\": false,\n"
                + "            \"multipleLimit\": 0,\n"
                + "            \"options\": [],\n"
                + "            \"allowCreate\": false\n"
                + "          },\n"
                + "          \"output\": \"agency\",\n"
                + "          \"component\": \"select\"\n"
                + "        },\n"
                + "        {\n"
                + "          \"deps\": [],\n"
                + "          \"uuid\": \"15e16320-2168-11eb-8123-27820a775376\",\n"
                + "          \"label\": \"面试评级\",\n"
                + "          \"props\": {\n"
                + "            \"uniqueKey\": false,\n"
                + "            \"defaultValue\": null,\n"
                + "            \"emptyValue\": null,\n"
                + "            \"hide\": false,\n"
                + "            \"disabled\": true,\n"
                + "            \"clearable\": false,\n"
                + "            \"hint\": \"\",\n"
                + "            \"placeholder\": \"\",\n"
                + "            \"required\": true,\n"
                + "            \"validator\": null,\n"
                + "            \"multiple\": false,\n"
                + "            \"multipleLimit\": 0,\n"
                + "            \"options\": [\n"
                + "              {\n"
                + "                \"text\": \"S\",\n"
                + "                \"value\": \"S\"\n"
                + "              },\n"
                + "              {\n"
                + "                \"text\": \"A\",\n"
                + "                \"value\": \"A\"\n"
                + "              },\n"
                + "              {\n"
                + "                \"text\": \"B\",\n"
                + "                \"value\": \"B\"\n"
                + "              },\n"
                + "              {\n"
                + "                \"text\": \"C\",\n"
                + "                \"value\": \"C\"\n"
                + "              }\n"
                + "            ],\n"
                + "            \"allowCreate\": false\n"
                + "          },\n"
                + "          \"output\": \"level\",\n"
                + "          \"component\": \"select\"\n"
                + "        },\n"
                + "        {\n"
                + "          \"deps\": [],\n"
                + "          \"uuid\": \"2d0fd5e0-2168-11eb-8123-27820a775376\",\n"
                + "          \"label\": \"开始时间\",\n"
                + "          \"props\": {\n"
                + "            \"defaultValue\": null,\n"
                + "            \"hide\": false,\n"
                + "            \"disabled\": false,\n"
                + "            \"hint\": \"\",\n"
                + "            \"placeholder\": \"\",\n"
                + "            \"required\": true,\n"
                + "            \"validator\": null\n"
                + "          },\n"
                + "          \"output\": \"startTime\",\n"
                + "          \"component\": \"datetime\"\n"
                + "        },\n"
                + "        {\n"
                + "          \"deps\": [],\n"
                + "          \"uuid\": \"421415a0-2168-11eb-8123-27820a775376\",\n"
                + "          \"label\": \"结束时间\",\n"
                + "          \"props\": {\n"
                + "            \"defaultValue\": 32503651200000,\n"
                + "            \"hide\": false,\n"
                + "            \"disabled\": false,\n"
                + "            \"hint\": \"\",\n"
                + "            \"placeholder\": \"\",\n"
                + "            \"required\": true,\n"
                + "            \"validator\": null\n"
                + "          },\n"
                + "          \"output\": \"endTime\",\n"
                + "          \"component\": \"datetime\"\n"
                + "        },\n"
                + "        {\n"
                + "          \"deps\": [],\n"
                + "          \"uuid\": \"4b4105c0-2168-11eb-8123-27820a775376\",\n"
                + "          \"label\": \"备注\",\n"
                + "          \"props\": {\n"
                + "            \"uniqueKey\": false,\n"
                + "            \"defaultValue\": \"\",\n"
                + "            \"hide\": false,\n"
                + "            \"disabled\": false,\n"
                + "            \"type\": \"textarea\",\n"
                + "            \"hint\": \"\",\n"
                + "            \"placeholder\": \"\",\n"
                + "            \"required\": false,\n"
                + "            \"autosize\": true,\n"
                + "            \"min\": 0,\n"
                + "            \"max\": 0,\n"
                + "            \"showWordLimit\": false,\n"
                + "            \"pattern\": \"\",\n"
                + "            \"validator\": null\n"
                + "          },\n"
                + "          \"output\": \"remark\",\n"
                + "          \"component\": \"input\"\n"
                + "        },\n"
                + "        {\n"
                + "          \"deps\": [],\n"
                + "          \"uuid\": \"f9db1270-217b-11eb-9995-23228d32a3e6\",\n"
                + "          \"label\": \"关联admin邮箱前缀\",\n"
                + "          \"props\": {\n"
                + "            \"uniqueKey\": false,\n"
                + "            \"defaultValue\": \"\",\n"
                + "            \"hide\": false,\n"
                + "            \"disabled\": false,\n"
                + "            \"hint\": \"\",\n"
                + "            \"placeholder\": \"\",\n"
                + "            \"required\": true,\n"
                + "            \"autosize\": false,\n"
                + "            \"trimType\": \"trim\",\n"
                + "            \"minRows\": 1,\n"
                + "            \"maxRows\": 1,\n"
                + "            \"min\": 0,\n"
                + "            \"max\": 0,\n"
                + "            \"showWordLimit\": false,\n"
                + "            \"pattern\": \"\",\n"
                + "            \"validator\": null\n"
                + "          },\n"
                + "          \"output\": \"linkedAdminEmail\",\n"
                + "          \"component\": \"input\"\n"
                + "        },\n"
                + "        {\n"
                + "          \"deps\": [\n"
                + "            {\n"
                + "              \"event\": \"ready\",\n"
                + "              \"listen\": \"b44ef910-218a-11eb-886c-93daa953c945\",\n"
                + "              \"source\": \"\",\n"
                + "              \"formatter\": {\n"
                + "                \"name\": [],\n"
                + "                \"extra\": \"\"\n"
                + "              },\n"
                + "              \"destination\": \"\"\n"
                + "            }\n"
                + "          ],\n"
                + "          \"uuid\": \"b44ef910-218a-11eb-886c-93daa953c945\",\n"
                + "          \"label\": \"bucketAPI\",\n"
                + "          \"props\": {\n"
                + "            \"url\": \"/v3/oversea/api/block/common/form/getMineTreasure?bizName=oversea-snack"
                + "-bucketDropdownComponent&status=2\",\n"
                + "            \"method\": \"GET\",\n"
                + "            \"payload\": [],\n"
                + "            \"preProcessCode\": \"\",\n"
                + "            \"postProcessCode\": \"\",\n"
                + "            \"pickKey\": \"data.options\"\n"
                + "          },\n"
                + "          \"output\": \"_bucket_API\",\n"
                + "          \"component\": \"api\"\n"
                + "        },\n"
                + "        {\n"
                + "          \"deps\": [\n"
                + "            {\n"
                + "              \"event\": \"ready\",\n"
                + "              \"listen\": \"91b91a10-218b-11eb-886c-93daa953c945\",\n"
                + "              \"source\": \"\",\n"
                + "              \"formatter\": {\n"
                + "                \"name\": [],\n"
                + "                \"extra\": \"\"\n"
                + "              },\n"
                + "              \"destination\": \"\"\n"
                + "            }\n"
                + "          ],\n"
                + "          \"uuid\": \"91b91a10-218b-11eb-886c-93daa953c945\",\n"
                + "          \"label\": \"categoryAPI\",\n"
                + "          \"props\": {\n"
                + "            \"url\": \"/v3/oversea/api/block/common/form/getMineTreasure?bizName=oversea-snack"
                + "-officialAnchorCategory&status=2\",\n"
                + "            \"method\": \"GET\",\n"
                + "            \"payload\": [],\n"
                + "            \"preProcessCode\": \"\",\n"
                + "            \"postProcessCode\": \"\",\n"
                + "            \"pickKey\": \"data.options\"\n"
                + "          },\n"
                + "          \"output\": \"_category_API\",\n"
                + "          \"component\": \"api\"\n"
                + "        },\n"
                + "        {\n"
                + "          \"deps\": [\n"
                + "            {\n"
                + "              \"event\": \"ready\",\n"
                + "              \"listen\": \"b4e3a280-218b-11eb-886c-93daa953c945\",\n"
                + "              \"source\": \"\",\n"
                + "              \"formatter\": {\n"
                + "                \"name\": [],\n"
                + "                \"extra\": \"\"\n"
                + "              },\n"
                + "              \"destination\": \"\"\n"
                + "            }\n"
                + "          ],\n"
                + "          \"uuid\": \"b4e3a280-218b-11eb-886c-93daa953c945\",\n"
                + "          \"label\": \"agencyAPI\",\n"
                + "          \"props\": {\n"
                + "            \"url\": \"/v3/oversea/api/block/common/form/getMineTreasure?bizName=oversea-snack"
                + "-officialAnchorAgency&status=2\",\n"
                + "            \"method\": \"GET\",\n"
                + "            \"payload\": [],\n"
                + "            \"preProcessCode\": \"\",\n"
                + "            \"postProcessCode\": \"\",\n"
                + "            \"pickKey\": \"data.options\"\n"
                + "          },\n"
                + "          \"output\": \"_agency_API\",\n"
                + "          \"component\": \"api\"\n"
                + "        },\n"
                + "        {\n"
                + "          \"deps\": [],\n"
                + "          \"uuid\": \"b49aa840-21af-11eb-8514-ffce075d5a1e\",\n"
                + "          \"label\": \"userValidAnchorAPI\",\n"
                + "          \"props\": {\n"
                + "            \"url\": \"/doraemon/validApi/userValidAnchor?userId={userId}&bucket={bucket}\",\n"
                + "            \"method\": \"GET\",\n"
                + "            \"payload\": [\n"
                + "              \"b4c72430-2167-11eb-8123-27820a775376\",\n"
                + "              \"8ace5c20-2167-11eb-8123-27820a775376\"\n"
                + "            ],\n"
                + "            \"preProcessCode\": \"\",\n"
                + "            \"postProcessCode\": \"\",\n"
                + "            \"pickKey\": \"data\"\n"
                + "          },\n"
                + "          \"output\": \"_userValidAnchor_API\",\n"
                + "          \"component\": \"api\"\n"
                + "        },\n"
                + "        {\n"
                + "          \"deps\": [],\n"
                + "          \"uuid\": \"b74f5600-50d8-11eb-a510-8992c0e15caa\",\n"
                + "          \"label\": \"状态\",\n"
                + "          \"props\": {\n"
                + "            \"uniqueKey\": false,\n"
                + "            \"defaultValue\": 2,\n"
                + "            \"emptyValue\": 0,\n"
                + "            \"hide\": false,\n"
                + "            \"disabled\": false,\n"
                + "            \"clearable\": false,\n"
                + "            \"hint\": \"\",\n"
                + "            \"placeholder\": \"\",\n"
                + "            \"required\": true,\n"
                + "            \"validator\": null,\n"
                + "            \"multiple\": false,\n"
                + "            \"multipleLimit\": 0,\n"
                + "            \"options\": [\n"
                + "              {\n"
                + "                \"text\": \"上线\",\n"
                + "                \"value\": 2\n"
                + "              },\n"
                + "              {\n"
                + "                \"text\": \"下线\",\n"
                + "                \"value\": 0\n"
                + "              }\n"
                + "            ],\n"
                + "            \"allowCreate\": false\n"
                + "          },\n"
                + "          \"output\": \"status\",\n"
                + "          \"component\": \"select\"\n"
                + "        }\n"
                + "      ]\n"
                + "    },\n"
                + "    \"output\": \"\",\n"
                + "    \"component\": \"tree\"\n"
                + "  }\n"
                + "}";

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readValue(content, JsonNode.class);
        JsonNode children = jsonNode.at("/config/props/children");
        ArrayNode childArray = (ArrayNode) children;
        for (JsonNode child : childArray) {
            String widgetName = child.get("output").asText();
            if (widgetName.equals("bucket")) {
                System.out.println(widgetName);
                JsonNode depsNode = child.at("/deps");
                ArrayNode deps = (ArrayNode) depsNode;
                JsonNode firstDepsNode = deps.get(0);
                JsonNode formatter = firstDepsNode.at("/formatter");
                System.out.println(formatter.get("extra").asText());
                String extraJson = formatter.get("extra").asText();
                List<Map> optionsInExtra = ObjectMapperUtils.fromJSON(extraJson, List.class, Map.class);
                System.out.println(optionsInExtra);
            }
        }
    }
}