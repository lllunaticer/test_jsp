package testDate;

import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;

import com.kuaishou.framework.util.ObjectMapperUtils;

import testDate.PartnerVideoTaskModel.BucketTask;

/**
 * @author longxingjian <longxingjian@kuaishou.com>
 * Created on 2021-05-10
 */
public class testDate {
    public static void main(String[] args) {
        String mockKconf = "[\n"
                + "  {\n"
                + "    \"date\": 10,\n"
                + "    \"tasks\": [\n"
                + "      {\n"
                + "        \"bucket\": \"pak\",\n"
                + "        \"mediaTypes\": [\n"
                + "          \"Editor\"\n"
                + "        ]\n"
                + "      },\n"
                + "      {\n"
                + "        \"bucket\": \"th\",\n"
                + "        \"mediaTypes\": [\n"
                + "          \"TH-Edit\"\n"
                + "        ]\n"
                + "      },\n"
                + "      {\n"
                + "        \"bucket\": \"bgd\",\n"
                + "        \"mediaTypes\": [\n"
                + "          \"Editor\"\n"
                + "        ]\n"
                + "      }\n"
                + "    ]\n"
                + "  },\n"
                + "  {\n"
                + "    \"date\": 6,\n"
                + "    \"tasks\": [\n"
                + "      {\n"
                + "        \"bucket\": \"ind\",\n"
                + "        \"mediaTypes\": [\n"
                + "          \"IN-Talent\",\n"
                + "          \"IN-TTTalent\",\n"
                + "          \"IN-YouTube\"\n"
                + "        ]\n"
                + "      }\n"
                + "    ]\n"
                + "  }\n"
                + "]";

        Collection<PartnerVideoTaskModel> partnerVideoTaskModels =
                ObjectMapperUtils.fromJSON(mockKconf, List.class, PartnerVideoTaskModel.class);

        int date = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        System.out.println(date);
        List<PartnerVideoTaskModel> taskModels =
                partnerVideoTaskModels.stream().filter(task -> task.getDate() == date).collect(Collectors.toList());
        if (CollectionUtils.isEmpty(taskModels)) {
            System.out.println("Not Today !!!");
            return;
        }

        taskModels.forEach(
                task -> {
                    List<BucketTask> tasks = task.getTasks();
                    Map<String, List<String>> taskDetails = new HashMap<>();
                    tasks.forEach(
                            bucketTask -> taskDetails.put(bucketTask.getBucket(), bucketTask.getMediaTypes())
                    );
                    System.out.println(taskDetails);
                }
        );
    }
}
