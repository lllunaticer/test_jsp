import java.util.Collections;
import java.util.Map;

import com.kuaishou.kconf.client.Kconf;
import com.kuaishou.kconf.client.Kconfs;
import com.snack.explore.model.ExploreLocale;

import lombok.Data;

/**
 * @author Xingjian LONG <longxingjian@kuaishou.com>
 * Created on 2022-04-27
 */
@Data
public class TaskConfig {
    private static final Kconf<Map<ExploreLocale, TaskConfig>> TASK_CONFIG =
            Kconfs.ofJsonMap("overseaServer.creator.scoutTaskConfig", Collections.emptyMap(), ExploreLocale.class,
                            TaskConfig.class)
                    .build();


    private GroupInfo group;



    //task的元信息



    @Data
    static class RewardConfig {
        private Map<CreatorLevel, Long> rewardAmount;
        private long headWearId;
    }

    public static void main(String[] args) {
        Map<ExploreLocale, TaskConfig> exploreLocaleTaskConfigMap = TASK_CONFIG.get();
        System.out.println(exploreLocaleTaskConfigMap);
    }

    enum CreatorLevel {
        A,
        B;
    }

    enum GroupInfo {
        JOIN(1, "creator_scout_join_group_head", "creator_scout_join_group_desc"),
        ACTIVE(2, "creator_scout_active_group_head", "creator_scout_active_group_desc"),
        GROW(3, "creator_scout_grow_group_head", "creator_scout_grow_group_desc"),
        FANS(4, "creator_scout_fans_group_head", "creator_scout_fans_group_desc");
        private final int groupId;
        private final String groupName;
        private final String groupDesc;

        GroupInfo(int groupId, String groupName, String groupDesc) {
            this.groupId = groupId;
            this.groupName = groupName;
            this.groupDesc = groupDesc;
        }

        public int getGroupId() {
            return groupId;
        }

        public String getGroupName() {
            return groupName;
        }

        public String getGroupDesc() {
            return groupDesc;
        }
    }

    enum TaskInfo {
        JOIN(1, "creator_scout_join_task_head", "creator_scout_join_task_desc"), //入驻任务
        ACTIVE_1(2, "creator_scout_active1_task_head", "creator_scout_active1_task_desc"), //活跃任务1
        ACTIVE_2(3, "creator_scout_active2_task_head", "creator_scout_active2_task_desc"), //活跃任务2
        GROW_1(4, "creator_scout_grow1_task_head", "creator_scout_grow1_task_desc"), //成长任务1
        GROW_2(5, "creator_scout_grow2_task_head", "creator_scout_grow2_task_desc"), //成长任务2
        GROW_3(6, "creator_scout_grow3_task_head", "creator_scout_grow3_task_desc"), //成长任务3
        FAN_1(7, "creator_scout_fan1_task_head", "creator_scout_fan1_task_desc") //涨粉任务
        ;
        private final int taskId;
        private final String taskName; // 任务标题多语言key

        private final String taskDesc; // 任务内容多语言key

        TaskInfo(int taskId, String taskName, String taskDesc) {
            this.taskId = taskId;
            this.taskName = taskName;
            this.taskDesc = taskDesc;
        }

        public int getTaskId() {
            return taskId;
        }

        public String getTaskName() {
            return taskName;
        }

        public String getTaskDesc() {
            return taskDesc;
        }
    }

}
