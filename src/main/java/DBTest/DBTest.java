package DBTest;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.junit.Test;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.google.common.collect.ImmutableMap;
import com.kuaishou.framework.spring.RichPropertyRowMapper;
import com.kuaishou.framework.util.ObjectMapperUtils;

import DBTest.DO.PartnerDO;
import DBTest.DO.PartnerDOV2;
import DBTest.DO.PhotoFilter;

/**
 * @author longxingjian <longxingjian@kuaishou.com>
 * Created on 2021-03-21
 */
public class DBTest {
    public static final String TABLE_SUBSTITUTION = "#table_name";
    public static final String MUSIC_PHOTO_INDEX = "photo_music_tag_index_0";

    private static final int BUCKET_HIGH_BITS = 55; //高64-55=9位，足够了

    private static final int MUSIC_TYPE_MIDDLE_BITS = 49; //BUCKET_HIGH_BITS-49 = 6，应该够了

    private static final int LONG_BIT = 64;

    public static void main(String[] args) {
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        NamedParameterJdbcTemplate namedParameterJdbcTemplate = dataSourceConfig.namedParameterJdbcTemplateLocalhost();
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        List<String> stringObjects =
                namedParameterJdbcTemplate
                        .query("select table_name from information_schema.TABLES where TABLE_SCHEMA = 'gifshow' and "
                                        + "TABLE_NAME like 'mv_%'",
                                mapSqlParameterSource, (resultSet, i) -> resultSet.getString("table_name"));
        StringBuilder dropBuilder = new StringBuilder().append("drop table ");
        for (int i = 0; i < stringObjects.size(); i++) {
            dropBuilder.append(stringObjects.get(i));
            if (i < stringObjects.size() - 1) {
                dropBuilder.append(", ");
            }
        }
        namedParameterJdbcTemplate.update(dropBuilder.toString(), new HashMap<>());
        //        System.out.println(dropBuilder.toString());
    }

    @Test
    public void testWriteDataToMusic() {
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        NamedParameterJdbcTemplate kwaiMusicV3Staging =
                dataSourceConfig.namedParameterJdbcTemplateMusicStagingV3();

        String sql =
                "insert into #table_name(merged_id, music_id, music_type, photo_id,bucket,create_time) values"
                        + "(:mergedId,:m, :t, :p,:b, :c) on duplicate key update create_time = :c";

        long photoId = 150050029703149L;
        long musicId = 789923639;
        int type = 10;
        int bucket = 11;
        for (int i = 1; i < 21; i++) {
            String generatedSql = StringUtils.replace(sql, TABLE_SUBSTITUTION, MUSIC_PHOTO_INDEX);
            kwaiMusicV3Staging.update(generatedSql, new MapSqlParameterSource("m", musicId) //
                    .addValue("t", type) //
                    .addValue("p", photoId + i) //
                    .addValue("mergedId", genMergeId(bucket, musicId, type)) //
                    .addValue("b", bucket) //
                    .addValue("c", System.currentTimeMillis()));
        }
    }

    public static long genMergeId(long bucket, long comboId, int type) {
        return (bucket << BUCKET_HIGH_BITS)
                + ((long) type << MUSIC_TYPE_MIDDLE_BITS)
                + comboId;
    }

    @Test
    public void testDateTime() {
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        NamedParameterJdbcTemplate namedParameterJdbcTemplate = dataSourceConfig.namedParameterJdbcTemplateLocalhost();
        String sql =
                "insert into oversea_partner_info_test (user_id, bucket, base_price, currency_code, begin_time) VALUE"
                        + " (:userId, :bucket, :price, :currency,:time)";

        String querySql = "select * from oversea_partner_info_test where user_id = 101";

        Date time = new Date(1000);

        MapSqlParameterSource mapSqlParameterSource =
                new MapSqlParameterSource("userId", 101).addValue("bucket", "pak").addValue("price", 200)
                        .addValue("currency", "Rp")
                        .addValue("time", time);

        namedParameterJdbcTemplate.update(sql, mapSqlParameterSource);
        PartnerDOV2 partnerDOV2 = namedParameterJdbcTemplate
                .queryForObject(querySql, new MapSqlParameterSource(), new RichPropertyRowMapper<>(PartnerDOV2.class));
        System.out.println(ObjectMapperUtils.toJSON(partnerDOV2));
    }

    @Test
    public void testTimeStamp() {
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        NamedParameterJdbcTemplate namedParameterJdbcTemplate = dataSourceConfig.namedParameterJdbcTemplateLocalhost();
        Date time = new Date(1640401550000L);
        String querySql = "select * from photo_filter where time >= :time order by time desc limit 1 ";
        PhotoFilter time1 = namedParameterJdbcTemplate.queryForObject(querySql, new MapSqlParameterSource("time", 1640401550000L),
                new RichPropertyRowMapper<>(PhotoFilter.class));
        System.out.println(ObjectMapperUtils.toJSON(time1));
    }

    @Test
    public void testBatchUpdate() {
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        //        String sql = "insert into test_table (user_id,name) values(:userId,:name)";
        ImmutableMap<Integer, String> userTable = ImmutableMap.of(1, "Xiaoming", 3, "GOOD");
        NamedParameterJdbcTemplate namedParameterJdbcTemplate = dataSourceConfig.namedParameterJdbcTemplateLocalhost();
        //        int[] results = namedParameterJdbcTemplate.batchUpdate(sql, userTable.entrySet().stream()
        //                .map(e -> new MapSqlParameterSource("userId", e.getKey())
        //                        .addValue("name", e.getValue())).toArray(MapSqlParameterSource[]::new));
        //        System.out.println(Arrays.toString(results));

        String updateSql = "update test_table set name=:name where user_id=:userId";
        int[] updateResults = namedParameterJdbcTemplate.batchUpdate(updateSql, userTable.entrySet().stream()
                .map(e -> new MapSqlParameterSource("userId", e.getKey())
                        .addValue("name", e.getValue())).toArray(MapSqlParameterSource[]::new));
        System.out.println(Arrays.toString(updateResults));
    }

    @Test
    public void testRead() {
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        NamedParameterJdbcTemplate namedParameterJdbcTemplate = dataSourceConfig.namedParameterJdbcTemplateLocalhost();
        String readSql = "select * from oversea_partner_info";
        List<PartnerDO> partnerDOList = new ArrayList<>();

        String writeSql =
                "INSERT INTO `oversea_partner_info` ( `user_id`, `bucket`, `base_price`, `currency_code`, "
                        + "`begin_time` )"
                        + "VALUES"
                        + "(:user_id, :bucket, :base_price, :currency, :begin_time)";

        namedParameterJdbcTemplate.update(writeSql,
                new MapSqlParameterSource()
                        .addValue("user_id", 3333)
                        .addValue("bucket", "in")
                        .addValue("base_price", 100)
                        .addValue("currency", "$")
                        .addValue("begin_time", new Date(1619107200000L))
        );

        namedParameterJdbcTemplate.query(readSql,
                new MapSqlParameterSource(),
                resultSet -> {partnerDOList.add(trans(resultSet));});

        for (PartnerDO partnerDO : partnerDOList) {
            System.out.println(partnerDO.getUserId());
            System.out.println(partnerDO.getBeginTime());
        }

    }

    private static PartnerDO trans(ResultSet resultSet) throws SQLException {
        PartnerDO partnerDO = new PartnerDO();
        partnerDO.setUserId(resultSet.getLong("user_id"));
        partnerDO.setBucket(resultSet.getString("bucket"));
        partnerDO.setBasePrice(100);
        partnerDO.setBeginTime(resultSet.getDate("begin_time"));
        partnerDO.setCurrency("dollar");
        return partnerDO;
    }

    @Test
    public void testDigits() {
        String digits = "1234234";
        //        Long.parseLong("12345L");
        System.out.println(NumberUtils.isDigits(digits));
    }

    @Test
    public void testLocale() {
        Locale locale = new Locale("en", "CN");
        System.out.println(locale.getLanguage());
        System.out.println(locale.getCountry());
        System.out.println(locale);
    }

}
