package JsonTest;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.collections4.MapUtils;
import org.json.JSONObject;

import com.github.phantomthief.tuple.TwoTuple;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.kuaishou.framework.util.ObjectMapperUtils;

/**
 * @author yanglan <yanglan05@kuaishou.com>
 * Created on 2021-05-13
 */
public class ViewDiffResult {

    private Multimap<String, TwoTuple<String, String>> normalDiffInfo = ArrayListMultimap.create();

    private Map<String, Object> srcUniqueInfo = new HashMap<>();

    private Map<String, Object> targetUniqueInfo = new HashMap<>();

    private Multimap<String, TwoTuple<String, String>> shareDiffInfo = ArrayListMultimap.create();

    public Multimap<String, TwoTuple<String, String>> getNormalDiffInfo() {
        return normalDiffInfo;
    }

    public void setNormalDiffInfo(
            Multimap<String, TwoTuple<String, String>> normalDiffInfo) {
        this.normalDiffInfo = normalDiffInfo;
    }

    public Map<String, Object> getSrcUniqueInfo() {
        return srcUniqueInfo;
    }

    public void setSrcUniqueInfo(Map<String, Object> srcUniqueInfo) {
        this.srcUniqueInfo = srcUniqueInfo;
    }

    public Map<String, Object> getTargetUniqueInfo() {
        return targetUniqueInfo;
    }

    public void setTargetUniqueInfo(Map<String, Object> targetUniqueInfo) {
        this.targetUniqueInfo = targetUniqueInfo;
    }

    public Multimap<String, TwoTuple<String, String>> getShareDiffInfo() {
        return shareDiffInfo;
    }

    public void setShareDiffInfo(
            Multimap<String, TwoTuple<String, String>> shareDiffInfo) {
        this.shareDiffInfo = shareDiffInfo;
    }

    @Override
    public String toString() {
        String result = "";
        if (MapUtils.isNotEmpty(srcUniqueInfo)) {
            result += String.format("response1独有的：\n%s", new JSONObject(srcUniqueInfo));
            result += "\n\n";
        }
        if (MapUtils.isNotEmpty(targetUniqueInfo)) {
            result += String.format("response2独有的：\n%s", new JSONObject(targetUniqueInfo));
            result += "\n\n";
        }
        if (!shareDiffInfo.isEmpty()) {
            result += String.format("key共有但value不同：\n%s", ObjectMapperUtils.toJSON(shareDiffInfo));
        }
        return result;
    }
}
