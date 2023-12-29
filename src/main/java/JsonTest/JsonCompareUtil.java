package JsonTest;

import static com.github.phantomthief.util.MoreFunctions.catching;
import static java.util.Optional.ofNullable;
import static java.util.stream.Collectors.toSet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.phantomthief.tuple.Tuple;
import com.github.phantomthief.tuple.TwoTuple;
import com.kuaishou.kconf.client.Kconf;
import com.kuaishou.kconf.client.Kconfs;

/**
 * @author yanglan <yanglan05@kuaishou.com>
 * Created on 2021-05-13
 */
public class JsonCompareUtil {

    private static final Logger logger = LoggerFactory.getLogger(JsonCompareUtil.class);

    // 该 key 的 value 不作比较
    public static final Kconf<Set<String>> JSON_KEY_WHITELIST =
            Kconfs.ofStringSet("snackServer.framework.jsonKeyWhiteList", Collections.emptySet()).build();
    // 将 该 path 下的 key 替换为 MockKey， 但是比较 value
    public static final Kconf<Map<String, String>> JSON_KEYPATH_MOCKLIST =
            Kconfs.ofStringMap("snackServer.framework.jsonKeyPathMockList", Collections.emptyMap()).build();
    // 当 某个key的子节点超过 limit ，则停止生成与比较
    @SuppressWarnings("checkstyle:MagicNumber")
    public static final Kconf<Integer> JSON_KEYSET_LIMIT =
            Kconfs.ofInteger("snackServer.framework.jsonKeySetLimit", 200).build();
    // 当 JSONArray 的长度超过 40 个，停止元素的生成
    @SuppressWarnings("checkstyle:MagicNumber")
    public static final Kconf<Integer> JSON_ARRAY_LENGTH_LIMIT =
            Kconfs.ofInteger("snackServer.framework.jsonArrayLengthLimit", 40).build();

    public static JsonDiffScope compareJson(String srcJson, String targetJson, boolean ignoreValue) {
        return catching(() -> {
            JSONObject src = new JSONObject(srcJson);
            JSONObject target = new JSONObject(targetJson);
            JsonDiffScope diffScope = new JsonDiffScope(true);
            compareJsonObject(src, target, "", diffScope, ignoreValue);
            return diffScope;
        });
    }

    private static void compareJsonObject(JSONObject src, JSONObject target, String parentKey, JsonDiffScope scope,
            boolean igoreValue) {
        Set<String> allKeys =
                Stream.concat(src.keySet().stream(), target.keySet().stream()).collect(toSet());
        allKeys.forEach(itemKey -> {
            Object srcObject = null;
            Object targetObject = null;
            try {
                srcObject = src.get(itemKey);
            } catch (Exception e) {
                //ignore
            }
            try {
                targetObject = target.get(itemKey);
            } catch (Exception e) {
                //ignore
            }
            compareObject(srcObject, targetObject, itemKey, scope, igoreValue);
        });
    }

    // parentKey: show, src: { "conditions": ["REPEAT_PLAY", "LIKE", "SHARE", "COMMENT", "DOWNLOAD"],"repeat_count": 3}
    //            conditions                 ["REPEAT_PLAY", "LIKE", "SHARE", "COMMENT", "DOWNLOAD"]
    private static void compareObject(Object src, Object target, String parentKey, JsonDiffScope scope,
            boolean igoreValue) {
        if (JSON_KEY_WHITELIST.get().contains(parentKey)) {
            return;
        }
        if ((src != null && target == null) || (src == null && target != null)) {
            if (src != null) {
                scope.getViewDiffResult().getSrcUniqueInfo()
                        .put(parentKey, src);
            } else {
                scope.getViewDiffResult().getTargetUniqueInfo()
                        .put(parentKey, target);
            }
            TwoTuple<String, String> diff = Tuple.tuple(ofNullable(src).map(Object::toString).orElse(null),
                    ofNullable(target).map(Object::toString).orElse(null));
            scope.getViewDiffResult().getNormalDiffInfo().put(parentKey, diff);
            scope.setSame(false);
            return;
        }
        if (src instanceof JSONObject && target instanceof JSONObject) {
            compareJsonObject((JSONObject) src, (JSONObject) target, parentKey, scope, igoreValue);
        } else if (src instanceof JSONArray && target instanceof JSONArray) {
            compareJsonArray((JSONArray) src, (JSONArray) target, parentKey, scope, igoreValue);
        } else {
            if (!igoreValue) {
                compareJsonString(ofNullable(src).map(Object::toString).orElse(""),
                        ofNullable(target).map(Object::toString).orElse(""), parentKey, scope);
            }
        }
    }

    private static void compareJsonString(String src, String target, String parentKey, JsonDiffScope scope) {
        boolean same;
        same = Objects.equals(src, target);
        if (!same) {
            scope.setSame(false);
            TwoTuple<String, String> diff = Tuple.tuple(src, target);
            scope.getViewDiffResult().getNormalDiffInfo().put(parentKey, diff);
            scope.getViewDiffResult().getShareDiffInfo().put(parentKey, diff);
        }
    }

    private static void compareJsonArray(JSONArray src, JSONArray target, String parentKey, JsonDiffScope scope,
            boolean igoreValue) {
        if (src.length() != target.length()) {
            scope.setSame(false);
        }
        List<Object> srcList = new ArrayList<>();
        List<Object> targetList = new ArrayList<>();
        IntStream.range(0, src.length()).forEach(k -> srcList.add(src.get(k)));
        IntStream.range(0, target.length()).forEach(k -> targetList.add(target.get(k)));
        sortJSONObject(srcList);
        sortJSONObject(targetList);
        int itr1 = 0;
        int itr2 = 0;
        while (itr1 < srcList.size() || itr2 < targetList.size()) {
            Object obj1 = null;
            Object obj2 = null;
            if (itr1 < src.length()) {
                obj1 = src.get(itr1++);
            }
            if (itr2 < target.length()) {
                obj2 = target.get(itr2++);
            }
            compareObject(obj1, obj2, parentKey, scope, igoreValue);
        }
    }

    private static void sortJSONObject(List<Object> list) {
        Collections.sort(list, (o1, o2) -> o1.toString().hashCode() - o2.toString().hashCode());
    }

}
