package DBTest;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import DBTest.DO.UserNotActiveModel;

/**
 * @author Xingjian LONG
 * Created on 2021-07-09
 */
public class StagingDbTest {

    private NamedParameterJdbcTemplate DATA_SOURCE;

    private static final DateTimeFormatter YYYY_MM_DD_WITHOUT_HYPHEN_FORMATTER = DateTimeFormat
            .forPattern("yyyyMMdd");

    public static void main(String[] args) {
        StagingDbTest stagingDbTest = new StagingDbTest();
        stagingDbTest.insertAward();
    }

    public StagingDbTest() {
        this.DATA_SOURCE = new DataSourceConfig().getNamedParameterJdbcTemplateStaging();
    }

    private static final String NOT_ACTIVE_TABLE_NAME_STAGING = "invitation_activity_not_active_user";
    private static final String QUERY_PLACE_HOLDER =
            "select `id`, `user_id`, `date`, `push_status` from " + NOT_ACTIVE_TABLE_NAME_STAGING
                    + " where `date` = :dt"
                    + " AND `user_id` = 999999999";

    private static final BeanPropertyRowMapper<UserNotActiveModel> NOT_ACTIVE_USER_ROW_MAPPER =
            new BeanPropertyRowMapper<>(UserNotActiveModel.class);

    public void insertAward(){

    }

    public UserNotActiveModel getNotActiveUserForWalletBalanceRemovePlaceHolder(String date) {
        List<UserNotActiveModel> notActiveModelList = DATA_SOURCE
                .query(QUERY_PLACE_HOLDER, new MapSqlParameterSource("dt", date), NOT_ACTIVE_USER_ROW_MAPPER);
        if (CollectionUtils.isEmpty(notActiveModelList)) {
            return null;
        } else {
            return notActiveModelList.get(0);
        }
    }

    private static final String QUERY_NOT_ACTIVE_USER_LIST =
            "select `id`, `user_id`, `date`, `push_status` from " + NOT_ACTIVE_TABLE_NAME_STAGING
                    + " where `date` = :dt"
                    + " AND `push_status` = 0"
                    + " AND `id` >= :cursor"
                    + " ORDER BY id asc LIMIT :limit";

    public List<UserNotActiveModel> getNotActiveUserForWalletByCursor(String date, long cursor, int limit) {
        return DATA_SOURCE.query(QUERY_NOT_ACTIVE_USER_LIST,
                new MapSqlParameterSource("dt", date)
                        .addValue("cursor", cursor)
                        .addValue("limit", limit),
                NOT_ACTIVE_USER_ROW_MAPPER
        );
    }

    private static final String UPDATE_PUSH_STATUS =
            "update " + NOT_ACTIVE_TABLE_NAME_STAGING
                    + " set push_status = 1"
                    + " where `date` = :dt"
                    + " and user_id in (:uids)";

    public void markPushStatusAsPushed(Collection<Long> userIds, String date) {
        DATA_SOURCE.update(UPDATE_PUSH_STATUS,
                new MapSqlParameterSource("dt", date)
                        .addValue("uids", userIds));
    }

}
