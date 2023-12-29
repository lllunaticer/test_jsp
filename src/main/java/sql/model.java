package sql;

/**
 * @author longxingjian <longxingjian@kuaishou.com>
 * Created on 2021-03-16
 */
public class model {
    public static void main(String[] args) {
        String ss = "        {\n"
                + "          \"deps\": [],\n"
                + "          \"uuid\": \"b1d19a30-6592-4f34-a253-87b711fbe334\",\n"
                + "          \"label\": \"Video Data Quota\",\n"
                + "          \"props\": {\n"
                + "            \"hide\": false,\n"
                + "            \"hint\": \"data for each video\",\n"
                + "            \"step\": 1,\n"
                + "            \"nonZero\": false,\n"
                + "            \"pattern\": \"\",\n"
                + "            \"controls\": false,\n"
                + "            \"disabled\": false,\n"
                + "            \"precision\": 0,\n"
                + "            \"uniqueKey\": false,\n"
                + "            \"validator\": null,\n"
                + "            \"placeholder\": \"\",\n"
                + "            \"defaultValue\": 0\n"
                + "          },\n"
                + "          \"output\": \"videoDataQuota\",\n"
                + "          \"component\": \"input-number\"\n"
                + "        },";
        System.out.println(ss.contains("\"uniqueKey\": true"));
    }
}
