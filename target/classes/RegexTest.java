import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author longxingjian <longxingjian@kuaishou.com>
 * Created on 2021-01-26
 */
public class RegexTest {
    private static final String CREATE_TABLE = "CREATE TABLE `doraemon_livestream_anchor_manager` (\n"
            + "       `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',\n"
            + "       `biz_name` varchar(100) COLLATE utf8mb4_bin DEFAULT '' COMMENT '业务bizName',\n"
            + "       `create_time` bigint(13) DEFAULT '0' COMMENT '创建时间',\n"
            + "       `update_time` bigint(13) DEFAULT '0' COMMENT '修改时间',\n"
            + "      `status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '0表示下线 1表示预上线 2表示上线 10表示未知',\n"
            + "       `anchor_id` bigint(20) unsigned DEFAULT '0' COMMENT '主播用户id',\n"
            + "       `anchor_name` varchar(100) COLLATE utf8mb4_bin DEFAULT '' COMMENT '主播名称',\n"
            + "       `authority` tinyint(1)  DEFAULT '0' COMMENT '主播权限',\n"
            + "      `tag` varchar(100) COLLATE utf8mb4_bin DEFAULT '' COMMENT '主播标签',\n"
            + "      `bucket` tinyint(1)  DEFAULT '0' COMMENT '桶',\n"
            + "      `level` varchar(100) COLLATE utf8mb4_bin DEFAULT '' COMMENT '直播等级',\n"
            + "      `pre_level` varchar(100) COLLATE utf8mb4_bin DEFAULT '' COMMENT '直播预设等级',\n"
            + "       PRIMARY KEY (`id`),\n" + "       KEY `idx_biz_name` (`biz_name`),\n"
            + "       KEY `idx_anchor_id` (`anchor_id`),\n"
            + "       KEY `idx_anchor_name` (`anchor_name`),\n"
            + "       KEY `idx_authority` (`authority`),\n" + "       KEY `idx_tag` (`tag`),\n"
            + "       KEY `idx_bucket` (`bucket`),\n" + "       KEY `idx_level` (`level`),\n"
            + "       KEY `idx_pre_level` (`pre_level`)\n"
            + "       ) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin ROW_FORMAT=DYNAMIC COMMENT='哆啦A梦主播管理页'";

    public static void main(String[] args) {
        Pattern pattern = Pattern.compile("^CREATE TABLE(.*)\\($");
        Matcher matcher = pattern.matcher(CREATE_TABLE);
        String group = matcher.group(1);
        System.out.println("match:" + group);
    }
}
