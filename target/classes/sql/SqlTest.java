package sql;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.kuaishou.framework.util.ObjectMapperUtils;

/**
 * @author longxingjian <longxingjian@kuaishou.com>
 * Created on 2021-03-01
 */
public class SqlTest {
    private static String fieldsStr = "[\n"
            + "      \"id\",\n"
            + "      \"create_time\",\n"
            + "      \"update_time\",\n"
            + "      \"status\",\n"
            + "      \"anchor_id\",\n"
            + "      \"anchor_name\",\n"
            + "      \"authority\",\n"
            + "      \"tag\",\n"
            + "      \"bucket\",\n"
            + "      \"level\",\n"
            + "      \"pre_level\",\n"
            + "      \"admin_id\"\n"
            + "    ]";

    public static void main(String[] args) {
        String table = "table";
        List<String> fields = ObjectMapperUtils.fromJSON(fieldsStr, List.class, String.class);
        List<String> columnNamesWithoutId = fields.stream()
                .filter(item -> !item.equals("id"))
                .filter(item -> !item.equals("update_time"))
                .collect(Collectors.toList());
        StringBuilder sqlBuilder = new StringBuilder().append("insert into ").append(table)
                .append(" (");

        List<String> columnNames = columnNamesWithoutId.stream()
                .collect(Collectors.toList());

        if (columnNames.size() == 0) {
            throw new IllegalArgumentException("数据库表错误");
        }
        StringBuilder valuesBuilder = new StringBuilder().append("values(");
        Map<String, Object> values = new HashMap<>();
        for (int i = 0; i < columnNames.size(); i++) {
            String columnName = columnNames.get(i);
            values.put(columnName, "1");
            sqlBuilder.append(columnName);
            valuesBuilder.append(":").append(columnName);
            if (i < columnNames.size() - 1) {
                valuesBuilder.append(",");
                sqlBuilder.append(",");
            }
        }
        valuesBuilder.append(")");
        String sql = sqlBuilder.append(") ")
                .append(valuesBuilder).toString();
        System.out.println(sql);
        System.out.println(values);
    }
}
