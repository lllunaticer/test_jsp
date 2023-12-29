package JsonTest;

import static com.github.phantomthief.util.MoreFunctions.catching;
import static java.util.stream.Collectors.toSet;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import com.kuaishou.framework.util.PerfUtils;

/**
 * @author yanglan <yanglan05@kuaishou.com>
 * Created on 2021-05-16
 */
public class JsonCreateUtil {

    private static String getMockKey(String value) {
        return "MockKey_" + value;
    }

    private static final String NAMESAPCE = "snackServer.jsonCreate";

    private static final Map<String, String> JSON_KEYPATH_MOCKLIST= new HashMap<>();

    // 执行失败返回 null
    public static JSONObject createJson(String srcJson, String targetJson) {
        JSONObject result = new JSONObject();
        return catching(() -> {
            JSONObject src = new JSONObject(srcJson);
            JSONObject target = new JSONObject(targetJson);
            createJsonObject(src, target, "", "", result);
            return result;
        });
    }

    // 替换 key 有个缺陷：如果 src 或 target 有一个为 null ，提前终止递归，则不会替换
    private static void createJsonObject(JSONObject src, JSONObject target, String parentKey, String path,
            Object node) {
        if (src.keySet().size() > 200) {
            System.out.println("oversize");
            return;
        }
        Set<String> srcKeys = src.keySet();
        Set<String> targetKeys = target.keySet();
        if (JSON_KEYPATH_MOCKLIST.containsKey(path)) {
            if (srcKeys.size() > 0 || targetKeys.size() > 0) {
                Object srcObject = null;
                Object targetObject = null;
                try {
                    srcObject = src.get(srcKeys.iterator().next());
                } catch (Exception e) {
                    // pass
                }
                try {
                    targetObject = target.get(targetKeys.iterator().next());
                } catch (Exception e) {
                    // pass
                }
                String itemKey = getMockKey(JSON_KEYPATH_MOCKLIST.get(path));
                String keyPath = StringUtils.isEmpty(path) ? itemKey : path + "." + itemKey;
                createObject(srcObject, targetObject, itemKey, keyPath, node);
            }
        } else {
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
                String keyPath = StringUtils.isEmpty(path) ? itemKey : path + "." + itemKey;
                createObject(srcObject, targetObject, itemKey, keyPath, node);
            });
        }
    }

    // parentKey: show, src: { "conditions": ["REPEAT_PLAY", "LIKE", "SHARE", "COMMENT", "DOWNLOAD"],"repeat_count": 3}
    //            conditions                 ["REPEAT_PLAY", "LIKE", "SHARE", "COMMENT", "DOWNLOAD"]
    private static void createObject(Object src, Object target, String parentKey, String path, Object node) {
        if ((src != null && target == null) || (src == null && target != null)) {
            //            record(scope.getPath(), normalize(totalKey), CHANGES);
            if (src != null) {
                if (node instanceof JSONArray) {
                    ((JSONArray) node).put(src);
                } else {
                    ((JSONObject) node).put(parentKey, src);
                }
            } else {
                if (node instanceof JSONArray) {
                    ((JSONArray) node).put(target);
                } else {
                    ((JSONObject) node).put(parentKey, target);
                }
            }
            return;
        }
        if (src instanceof JSONObject && target instanceof JSONObject) {
            // 在 node (JSONArray) 中放入 src (JSONObject)
            if (node instanceof JSONArray) {
                ((JSONArray) node).put(new JSONObject());
                createJsonObject((JSONObject) src, (JSONObject) target, parentKey, path,
                        ((JSONArray) node).get(((JSONArray) node).length() - 1));
            } else {
                // 在 node (JSONObject) 中放入 src (JSONObject)
                ((JSONObject) node).put(parentKey, new JSONObject());
                createJsonObject((JSONObject) src, (JSONObject) target, parentKey, path,
                        ((JSONObject) node).getJSONObject(parentKey));
            }
        } else if (src instanceof JSONArray && target instanceof JSONArray) {
            // 在 node (JSONArray) 中放入 src(JSONArray)
            if (node instanceof JSONArray) {
                ((JSONArray) node).put(new JSONArray());
                createJsonArray((JSONArray) src, (JSONArray) target, parentKey, path,
                        ((JSONArray) node).get(((JSONArray) node).length() - 1));
            } else {
                // 在 node (JSONObject) 中放入 src(JSONArray)
                ((JSONObject) node).put(parentKey, new JSONArray());
                createJsonArray((JSONArray) src, (JSONArray) target, parentKey, path,
                        ((JSONObject) node).get(parentKey));
            }
        } else {
            if (node instanceof JSONArray) {
                if (((JSONArray) node).length() > 0) {
                    ((JSONArray) node).getJSONObject(((JSONArray) node).length() - 1).put(parentKey, src);
                } else {
                    ((JSONArray) node).put(src);
                }
            } else {
                ((JSONObject) node).put(parentKey, src);
            }
        }
    }


    private static void createJsonArray(JSONArray src, JSONArray target, String parentKey, String path, Object array) {
        if (src.length() > 40) {
            System.out.println("src length over 40");
        }
        Object obj1 = null;
        Object obj2 = null;
        if (src.length() > 0) {
            obj1 = src.get(0);
        }
        if (target.length() > 0) {
            obj2 = target.get(0);
        }
        createObject(obj1, obj2, parentKey, path, array);
    }

    public static void main(String[] args) {
        String srcJson = "{\"result\":1,\"message\":\"\",\"data\":{\"userLogs\":[{\"logType\":\"查询用户敏感数据\","
                + "\"adminName\":\"mochaojun\",\"sourcePage\":\"用户主页\",\"time\":1621933928000,"
                + "\"desc\":\"registerPhone registerEmail latitudeAndLongitude \"},{\"logType\":\"查询用户敏感数据\","
                + "\"adminName\":\"mochaojun\",\"sourcePage\":\"用户主页\",\"time\":1621933927000,"
                + "\"desc\":\"registerPhone registerEmail latitudeAndLongitude \"},{\"logType\":\"查询用户敏感数据\","
                + "\"adminName\":\"mochaojun\",\"sourcePage\":\"用户主页\",\"time\":1621582096000,"
                + "\"desc\":\"registerPhone registerEmail latitudeAndLongitude lastStartUp \"},{\"logType\":\"用户设置\","
                + "\"adminName\":\"系统(System)\",\"sourcePage\":\"简介审核\",\"time\":1608200794000,\"desc\":\"简介: my "
                + "official account Nisha guragain \\uD83D\\uDCAF\\uD83D\\uDE4F lvmusic gang..youtube channel\"},"
                + "{\"logType\":\"标记\",\"adminName\":\"湘-向紫婷\",\"sourcePage\":\"用户日志记录\",\"time\":1607744421000,"
                + "\"desc\":\"允许图片. 头像: <img class=\\\"popup-images logImg\\\" "
                + "src=\\\"/v2/download/image/head?blobStoreKey=/bs2/overseaHead"
                + "/20201212113905_BMTUwMDAwMDk1NDI1ODcz_s.jpg\\\"><a target=\\\"_blank\\\" "
                + "href=\\\"/v2/image/dup/head?url=/v2/download/image/head?blobStoreKey=/bs2/overseaHead"
                + "/20201212113905_BMTUwMDAwMDk1NDI1ODcz_s.jpg\\\">查重</a>\"},{\"logType\":\"用户设置\",\"adminName\":\"系统"
                + "(System)\",\"sourcePage\":\"简介审核\",\"time\":1605410522000,\"desc\":\"简介: my official account Nisha"
                + " guragain \\uD83D\\uDCAF\\uD83D\\uDE4F\"},{\"logType\":\"用户设置\",\"adminName\":\"系统(System)\","
                + "\"sourcePage\":\"简介审核\",\"time\":1605410519000,\"desc\":\"简介: my official account Nisha guragain "
                + "\\uD83D\\uDCAF\\uD83D\\uDE4F\"},{\"logType\":\"用户设置\",\"adminName\":\"系统(System)\","
                + "\"sourcePage\":\"简介审核\",\"time\":1605410507000,\"desc\":\"简介: my official account Nisha guragain "
                + "\\uD83D\\uDCAF\\uD83D\\uDE4Fhttps://youtu.be/rwI8Rll0ZOE\"},{\"logType\":\"用户设置\","
                + "\"adminName\":\"系统(System)\",\"sourcePage\":\"简介审核\",\"time\":1605410499000,\"desc\":\"简介: my "
                + "official account Nisha guragain \\uD83D\\uDCAF\\uD83D\\uDE4Fhttps://youtu.be/rwI8Rll0ZOE\"},"
                + "{\"logType\":\"用户设置\",\"adminName\":\"系统(System)\",\"sourcePage\":\"简介审核\",\"time\":1603872304000,"
                + "\"desc\":\"简介: my official account Nisha guragain \\uD83D\\uDCAF\\uD83D\\uDE4F\"},"
                + "{\"logType\":\"用户设置\",\"adminName\":\"系统(System)\",\"sourcePage\":\"简介审核\",\"time\":1603872296000,"
                + "\"desc\":\"简介: my official account Nisha guragain \\uD83D\\uDCAF\\uD83D\\uDE4F\"},"
                + "{\"logType\":\"用户设置\",\"adminName\":\"系统(System)\",\"sourcePage\":\"简介审核\",\"time\":1603861078000,"
                + "\"desc\":\"简介: https://www.youtube.com/channel/UCxRKLurGvMNC5X8j1YSAn4g?view_as=subscriber\"},"
                + "{\"logType\":\"用户设置\",\"adminName\":\"系统(System)\",\"sourcePage\":\"简介审核\",\"time\":1603861069000,"
                + "\"desc\":\"简介: https://www.youtube.com/channel/UCxRKLurGvMNC5X8j1YSAn4g?view_as=subscriber\"},"
                + "{\"logType\":\"用户设置\",\"adminName\":\"系统(System)\",\"sourcePage\":\"简介审核\",\"time\":1603829288000,"
                + "\"desc\":\"简介: my official account Nisha guragain\"},{\"logType\":\"用户设置\",\"adminName\":\"系统"
                + "(System)\",\"sourcePage\":\"简介审核\",\"time\":1603829278000,\"desc\":\"简介: my official account Nisha"
                + " guragain\"},{\"logType\":\"用户设置\",\"adminName\":\"系统(System)\",\"sourcePage\":\"简介审核\","
                + "\"time\":1603829264000,\"desc\":\"简介: my official account Nisha guragain https://www.youtube"
                + ".com/channel/UCxRKLurGvMNC5X8j1YSAn4g?view_as=subscriber\"},{\"logType\":\"用户设置\","
                + "\"adminName\":\"系统(System)\",\"sourcePage\":\"简介审核\",\"time\":1603829254000,\"desc\":\"简介: my "
                + "official account Nisha guragain https://www.youtube"
                + ".com/channel/UCxRKLurGvMNC5X8j1YSAn4g?view_as=subscriber\"},{\"logType\":\"用户设置\","
                + "\"adminName\":\"系统(System)\",\"sourcePage\":\"简介审核\",\"time\":1603829198000,\"desc\":\"简介: my "
                + "official account Nisha guragain\"},{\"logType\":\"用户设置\",\"adminName\":\"系统(System)\","
                + "\"sourcePage\":\"简介审核\",\"time\":1603829106000,\"desc\":\"简介: \"},{\"logType\":\"用户设置\","
                + "\"adminName\":\"系统(System)\",\"sourcePage\":\"简介审核\",\"time\":1603829101000,\"desc\":\"简介: \"},"
                + "{\"logType\":\"用户设置\",\"adminName\":\"系统(System)\",\"sourcePage\":\"简介审核\",\"time\":1603829055000,"
                + "\"desc\":\"简介: My youtube channel ://www.youtube"
                + ".com/channel/UCxRKLurGvMNC5X8j1YSAn4g?view_as=subscriber\"},{\"logType\":\"用户设置\","
                + "\"adminName\":\"系统(System)\",\"sourcePage\":\"简介审核\",\"time\":1603829053000,\"desc\":\"简介: My "
                + "youtube channel ://www.youtube.com/channel/UCxRKLurGvMNC5X8j1YSAn4g?view_as=subscriber\"},"
                + "{\"logType\":\"用户设置\",\"adminName\":\"系统(System)\",\"sourcePage\":\"简介审核\",\"time\":1603810876000,"
                + "\"desc\":\"简介: \"},{\"logType\":\"用户设置\",\"adminName\":\"系统(System)\",\"sourcePage\":\"简介审核\","
                + "\"time\":1603810867000,\"desc\":\"简介: \"},{\"logType\":\"用户设置\",\"adminName\":\"系统(System)\","
                + "\"sourcePage\":\"简介审核\",\"time\":1603810831000,\"desc\":\"简介: my insta account "
                + "nisha______guragain\"},{\"logType\":\"用户设置\",\"adminName\":\"系统(System)\",\"sourcePage\":\"简介审核\","
                + "\"time\":1603810826000,\"desc\":\"简介: my insta account nisha______guragain\"},{\"logType\":\"正常\","
                + "\"adminName\":\"彭佳聪\",\"sourcePage\":\"用户日志记录\",\"time\":1603336680000,\"desc\":\"用户分级:C\"},"
                + "{\"logType\":\"正常\",\"adminName\":\"李岳禧\",\"sourcePage\":\"用户日志记录\",\"time\":1603276420000,"
                + "\"desc\":\"用户分级:B\"},{\"logType\":\"正常\",\"adminName\":\"系统(System)\",\"sourcePage\":\"用户日志记录\","
                + "\"time\":1603161330000,\"desc\":\"用户分级搬运队列（刷数）:同人搬运\"},{\"logType\":\"正常\",\"adminName\":\"系统"
                + "(System)\",\"sourcePage\":\"用户日志记录\",\"time\":1603160654000,\"desc\":\"用户分级搬运队列（刷数）:同人搬运\"},"
                + "{\"logType\":\"正常\",\"adminName\":\"陈杰伟\",\"sourcePage\":\"用户日志记录\",\"time\":1603077269000,"
                + "\"desc\":\"用户分级:C\"},{\"logType\":\"正常\",\"adminName\":\"贾育坤\",\"sourcePage\":\"用户日志记录\","
                + "\"time\":1600930746000,\"desc\":\"用户分级搬运队列:原创搬运\"},{\"logType\":\"用户设置\",\"adminName\":\"系统"
                + "(System)\",\"sourcePage\":\"简介审核\",\"time\":1600715455000,\"desc\":\"简介: OFFICIAL ACCOUNT NISHA "
                + "GURAGAIN ❤️\\uD83D\\uDCAF\\uD83D\\uDE4F\"},{\"logType\":\"标记\",\"adminName\":\"杨焱\","
                + "\"sourcePage\":\"用户日志记录\",\"time\":1600199199000,\"desc\":\"允许图片. 头像: <img class=\\\"popup-images "
                + "logImg\\\" src=\\\"/v2/download/image/head?blobStoreKey=/bs2/overseaHead"
                + "/20200916021030_BMTUwMDAwMDk1NDI1ODcz_s.jpg\\\"><a target=\\\"_blank\\\" "
                + "href=\\\"/v2/image/dup/head?url=/v2/download/image/head?blobStoreKey=/bs2/overseaHead"
                + "/20200916021030_BMTUwMDAwMDk1NDI1ODcz_s.jpg\\\">查重</a>\"},{\"logType\":\"用户设置\",\"adminName\":\"系统"
                + "(System)\",\"sourcePage\":\"简介审核\",\"time\":1596685335000,\"desc\":\"简介: \"},"
                + "{\"logType\":\"用户设置\",\"adminName\":\"系统(System)\",\"sourcePage\":\"简介审核\",\"time\":1596685330000,"
                + "\"desc\":\"简介: https://youtu.be/b7dpR-9NOgE\"},{\"logType\":\"用户设置\",\"adminName\":\"系统(System)\","
                + "\"sourcePage\":\"用户名审核\",\"time\":1595215796000,\"desc\":\"用户名: nisha__guragain\"},"
                + "{\"logType\":\"标记\",\"adminName\":\"系统(System)\",\"sourcePage\":\"海外头像审核\",\"time\":1595215642000,"
                + "\"desc\":\"允许图片. 头像: <img class=\\\"popup-images logImg\\\" "
                + "src=\\\"/v2/download/image/head?blobStoreKey=/bs2/overseaHead"
                + "/20200720112710_BMTUwMDAwMDk1NDI1ODcz_s.jpg\\\"><a target=\\\"_blank\\\" "
                + "href=\\\"/v2/image/dup/head?url=/v2/download/image/head?blobStoreKey=/bs2/overseaHead"
                + "/20200720112710_BMTUwMDAwMDk1NDI1ODcz_s.jpg\\\">查重</a>\"},{\"logType\":\"用户设置\",\"adminName\":\"系统"
                + "(System)\",\"sourcePage\":\"用户名审核\",\"time\":1595214041000,\"desc\":\"用户名: Nisha Guragain820\"}],"
                + "\"lastLogin\":\"********\",\"lastLoginPort\":0,\"userText\":\"my official account Nisha guragain "
                + "\\uD83D\\uDCAF\\uD83D\\uDE4F\\nlvmusic gang..youtube channel\","
                + "\"lastStartUpDeviceId\":\"********\",\"isOverSeaUser\":1,\"userPhone\":\"+91******5560\","
                + "\"headUrl\":\"http://aws-ind-pic.snackvideo"
                + ".in/bs2/overseaHead/20201212113905_BMTUwMDAwMDk1NDI1ODcz_s.jpg\",\"isTrustDeviceUser\":false,"
                + "\"sysVer\":\"KWAI_BULLDOG_ANDROID_10\",\"likeCount\":7,\"allPhotoCommentClosed\":false,"
                + "\"registerEmail\":\"********\",\"isPartner\":false,\"phoneModel\":\"Xiaomi(Redmi K20 Pro)\","
                + "\"bucketLists\":{\"lka\":\"斯里兰卡\",\"ru\":\"俄罗斯桶\",\"ko\":\"韩国桶\",\"spa\":\"西班牙桶\","
                + "\"in\":\"印度尼西亚桶\",\"ms\":\"马来西亚桶\",\"en\":\"英语桶\",\"npl\":\"尼泊尔\",\"en_PH\":\"菲律宾桶\","
                + "\"tur\":\"土耳其桶\",\"br\":\"巴西桶\",\"mmr\":\"缅甸桶\",\"zh_TW\":\"台湾桶\",\"vnm\":\"越南桶\",\"th\":\"泰国桶\","
                + "\"ja\":\"日本桶\",\"pak\":\"巴基斯坦\",\"zh_CN\":\"大陆桶\",\"mix\":\"混合桶\",\"bgd\":\"孟加拉国\","
                + "\"ind\":\"印度桶\"},\"potentialCategory\":null,\"anchorApplyStatus\":1,\"userKwaiId\":\"unqvd222, "
                + "nisha__guragain\",\"blockPermission\":{\"actionBanBlock\":true,\"userHealth\":true,"
                + "\"registerPhone\":true,\"clearDesc\":true,\"allUserCounts\":true,\"thirdOriginalInfo\":true,"
                + "\"registerEmail\":true,\"languageAndCountry\":true,\"userBanTime\":true,\"promotionChannel\":true,"
                + "\"livestreamPermissions\":true,\"userBanHigh\":true,\"lastStartUp\":true,\"overSeaHomePage\":true,"
                + "\"partBan\":true,\"clearHeader\":true,\"latitudeAndLongitude\":true,\"registerExt\":true,"
                + "\"updateUserName\":true,\"updateDesc\":true,\"familyUpdate\":true,\"userReviewLog\":true,"
                + "\"actionBanBlock4Read\":true,\"userCACSat\":true,\"clearUserName\":true,\"updateUserKwaiId\":true,"
                + "\"cleanAllPhoto\":true},\"longitude\":\"********\",\"userExploreLocaleIntValue\":14,"
                + "\"detailStatus\":[{\"date\":\"2021-05-02\",\"status\":0,\"total\":0,\"photos\":[],"
                + "\"photoIdStr\":\"\",\"statusIdStr\":\"\"},{\"date\":\"2021-05-03\",\"status\":0,\"total\":0,"
                + "\"photos\":[],\"photoIdStr\":\"\",\"statusIdStr\":\"\"},{\"date\":\"2021-05-04\",\"status\":0,"
                + "\"total\":0,\"photos\":[],\"photoIdStr\":\"\",\"statusIdStr\":\"\"},{\"date\":\"2021-05-05\","
                + "\"status\":0,\"total\":0,\"photos\":[],\"photoIdStr\":\"\",\"statusIdStr\":\"\"},"
                + "{\"date\":\"2021-05-06\",\"status\":0,\"total\":0,\"photos\":[],\"photoIdStr\":\"\","
                + "\"statusIdStr\":\"\"},{\"date\":\"2021-05-07\",\"status\":0,\"total\":0,\"photos\":[],"
                + "\"photoIdStr\":\"\",\"statusIdStr\":\"\"},{\"date\":\"2021-05-08\",\"status\":0,\"total\":0,"
                + "\"photos\":[],\"photoIdStr\":\"\",\"statusIdStr\":\"\"},{\"date\":\"2021-05-09\",\"status\":0,"
                + "\"total\":0,\"photos\":[],\"photoIdStr\":\"\",\"statusIdStr\":\"\"},{\"date\":\"2021-05-10\","
                + "\"status\":0,\"total\":0,\"photos\":[],\"photoIdStr\":\"\",\"statusIdStr\":\"\"},"
                + "{\"date\":\"2021-05-11\",\"status\":0,\"total\":0,\"photos\":[],\"photoIdStr\":\"\","
                + "\"statusIdStr\":\"\"},{\"date\":\"2021-05-12\",\"status\":0,\"total\":0,\"photos\":[],"
                + "\"photoIdStr\":\"\",\"statusIdStr\":\"\"},{\"date\":\"2021-05-13\",\"status\":0,\"total\":0,"
                + "\"photos\":[],\"photoIdStr\":\"\",\"statusIdStr\":\"\"},{\"date\":\"2021-05-14\",\"status\":0,"
                + "\"total\":0,\"photos\":[],\"photoIdStr\":\"\",\"statusIdStr\":\"\"},{\"date\":\"2021-05-15\","
                + "\"status\":0,\"total\":0,\"photos\":[],\"photoIdStr\":\"\",\"statusIdStr\":\"\"},"
                + "{\"date\":\"2021-05-16\",\"status\":0,\"total\":0,\"photos\":[],\"photoIdStr\":\"\","
                + "\"statusIdStr\":\"\"},{\"date\":\"2021-05-17\",\"status\":0,\"total\":0,\"photos\":[],"
                + "\"photoIdStr\":\"\",\"statusIdStr\":\"\"},{\"date\":\"2021-05-18\",\"status\":0,\"total\":0,"
                + "\"photos\":[],\"photoIdStr\":\"\",\"statusIdStr\":\"\"},{\"date\":\"2021-05-19\",\"status\":0,"
                + "\"total\":0,\"photos\":[],\"photoIdStr\":\"\",\"statusIdStr\":\"\"},{\"date\":\"2021-05-20\","
                + "\"status\":0,\"total\":0,\"photos\":[],\"photoIdStr\":\"\",\"statusIdStr\":\"\"},"
                + "{\"date\":\"2021-05-21\",\"status\":0,\"total\":0,\"photos\":[],\"photoIdStr\":\"\","
                + "\"statusIdStr\":\"\"},{\"date\":\"2021-05-22\",\"status\":0,\"total\":0,\"photos\":[],"
                + "\"photoIdStr\":\"\",\"statusIdStr\":\"\"},{\"date\":\"2021-05-23\",\"status\":0,\"total\":0,"
                + "\"photos\":[],\"photoIdStr\":\"\",\"statusIdStr\":\"\"},{\"date\":\"2021-05-24\",\"status\":0,"
                + "\"total\":0,\"photos\":[],\"photoIdStr\":\"\",\"statusIdStr\":\"\"},{\"date\":\"2021-05-25\","
                + "\"status\":0,\"total\":0,\"photos\":[],\"photoIdStr\":\"\",\"statusIdStr\":\"\"},"
                + "{\"date\":\"2021-05-26\",\"status\":0,\"total\":0,\"photos\":[],\"photoIdStr\":\"\","
                + "\"statusIdStr\":\"\"},{\"date\":\"2021-05-27\",\"status\":0,\"total\":0,\"photos\":[],"
                + "\"photoIdStr\":\"\",\"statusIdStr\":\"\"},{\"date\":\"2021-05-28\",\"status\":0,\"total\":0,"
                + "\"photos\":[],\"photoIdStr\":\"\",\"statusIdStr\":\"\"},{\"date\":\"2021-05-29\",\"status\":0,"
                + "\"total\":0,\"photos\":[],\"photoIdStr\":\"\",\"statusIdStr\":\"\"},{\"date\":\"2021-05-30\","
                + "\"status\":0,\"total\":0,\"photos\":[],\"photoIdStr\":\"\",\"statusIdStr\":\"\"},"
                + "{\"date\":\"2021-05-31\",\"status\":0,\"total\":0,\"photos\":[],\"photoIdStr\":\"\","
                + "\"statusIdStr\":\"\"}],\"registerIp\":\"********\",\"userExploreLocale\":\"印度桶\","
                + "\"postTotalDays\":0,\"healthyPostDays\":0,\"shadowStatusDesc\":\"否\",\"selectOperator\":0,"
                + "\"hasRegisterLocation\":true,\"userMotherLanguage\":\"en_IN\","
                + "\"registerDevice\":\"ANDROID_c5897eaf73385cc9\",\"userChannel\":\"null-null--\","
                + "\"loginLocation\":\"********\",\"userCounts\":{\"photo\":0,\"fan\":3180410,"
                + "\"admView\":\"用户-粉丝数:3180410<br />用户-关注数:3<br />提醒-新关注:8250<br />\",\"pureFan\":3180410,"
                + "\"fanOffset\":0,\"notify\":0,\"black\":1,\"fansCountRENREN\":0,\"fansCountQQWEIBO\":0,"
                + "\"fansCountSINA\":0,\"newNotifyComment\":0,\"newNotifyLike\":0,\"newNotifyMessage\":0,"
                + "\"newNotifyReply\":0,\"newNotifyFollow\":8250,\"newMayfriend\":0,\"newCommentLike\":0,"
                + "\"newFollowfeed\":0,\"newFollowrequest\":0,\"newAt\":0,\"newPhotoSameFrame\":0,\"newMusicUsed\":0,"
                + "\"newIntownComment\":0,\"newIntownCommentReply\":0,\"newMissU\":0,\"newNotifyMomentLike\":0,"
                + "\"newNotifyMomentComment\":0,\"newNotifyMomentCommentReply\":0,\"newNotifyMomentAt\":0,"
                + "\"newNotifyMomentCommentAt\":0,\"acceptFollowRequest\":0,\"newShareTokenOpened\":0,"
                + "\"newRewardCount\":0,\"newFollowingMomentCount\":0,\"newMissUAuthorNewPhoto\":0,"
                + "\"profileVisit\":0,\"follow\":3},\"flowSimpleInfo\":{\"idType\":null,\"businessId\":null,"
                + "\"taskStatus\":null,\"realView\":null,\"consumed\":null,\"flowQuota\":null},\"isVerified\":false,"
                + "\"latitude\":\"********\",\"registerTypeDesc\":\"********\",\"registerType\":\"********\","
                + "\"commentPartBan\":false,\"isPotentialUser\":false,\"photoCount\":617,\"userBanTimes\":0,"
                + "\"unhealthyPostDays\":0,\"clientVer\":\"2.11.2.220\",\"startTime\":1610040902051,"
                + "\"userCache\":{\"userId\":150000095425873,\"userName\":\"nisha__guragain\","
                + "\"userHead\":\"http://aws-ind-pic.snackvideo"
                + ".in/bs2/overseaHead/20201212113905_BMTUwMDAwMDk1NDI1ODcz_s.jpg\",\"userSex\":\"F\","
                + "\"userStatus\":0,\"userSettings\":48,\"userText\":\"my official account Nisha guragain "
                + "\\uD83D\\uDCAF\\uD83D\\uDE4F\\nlvmusic gang..youtube channel\",\"birthday\":\"\","
                + "\"extParams\":{\"head\":15},\"rawUserHead\":\"http://aws-ind-pic.snackvideo"
                + ".in/bs2/overseaHead/20201212113905_BMTUwMDAwMDk1NDI1ODcz_s.jpg\",\"banned\":false,"
                + "\"userDenyDownloadFlag\":true,\"userDenyPhotoDownloadFlag\":false,\"userDenyCommentFlag\":false,"
                + "\"userDenyMessageFlag\":false,\"userNotRecommendToContacts\":false,"
                + "\"userNotRecommendToQqFriends\":false,\"userPrivacyNewsFlag\":false,"
                + "\"downloadMyWorkNotAllowedFlag\":false,\"hideVideoYouLikeFlag\":false,"
                + "\"hideMyDiamondLevelFlag\":false,\"rawUserName\":\"nisha__guragain\","
                + "\"userHeadAdmin\":\"http://aws-ind-pic.snackvideo"
                + ".in/bs2/overseaHead/20201212113905_BMTUwMDAwMDk1NDI1ODcz_s.jpg\",\"male\":false,\"female\":true,"
                + "\"userLocationPrivacyFlag\":false,\"userPrivacyFlag\":false,\"unknown\":false,"
                + "\"bigHead\":\"http://aws-ind-pic.snackvideo"
                + ".in/bs2/overseaHead/20201212113905_BMTUwMDAwMDk1NDI1ODcz_b.jpg\",\"tinyHead\":\"http://aws-ind-pic"
                + ".snackvideo.in/bs2/overseaHead/20201212113905_BMTUwMDAwMDk1NDI1ODcz_t.jpg\","
                + "\"smallWebp\":\"http://aws-ind-pic.snackvideo"
                + ".in/bs2/overseaHead/20201212113905_BMTUwMDAwMDk1NDI1ODcz_sw.webp\","
                + "\"tinyWebp\":\"http://aws-ind-pic.snackvideo"
                + ".in/bs2/overseaHead/20201212113905_BMTUwMDAwMDk1NDI1ODcz_tw.webp\"},\"registerDate\":\"********\","
                + "\"clientId\":2,\"socialBanned\":false,\"officialFamilyCategory\":null,"
                + "\"userKwaiIds\":[\"unqvd222\",\"nisha__guragain\"],\"userName\":\"nisha__guragain\","
                + "\"exploreLocales\":[\"zh_CN\",\"zh_TW\",\"en\",\"ko\",\"ja\",\"th\",\"ms\",\"in\",\"ru\","
                + "\"en_PH\",\"br\",\"vnm\",\"mmr\",\"ind\",\"tur\",\"spa\",\"mix\",\"pak\",\"bgd\",\"npl\",\"lka\"],"
                + "\"userId\":150000095425873,\"localeList\":[\"zh-TW\",\"en\",\"ko\",\"ja\",\"th\",\"ms\",\"id\","
                + "\"ru\",\"fil\",\"pt\",\"vi\",\"my\",\"hi\",\"de\",\"tr\",\"fr\",\"es\",\"spa\",\"ar\",\"ps\","
                + "\"ur\",\"pa\",\"sd\",\"bn\",\"ne\",\"si\"],\"actionBanMap\":{\"types\":[\"USER_NAME\","
                + "\"USER_TEXT\",\"USER_AVATAR\",\"USER_BG\",\"COMMENT\",\"MESSAGE\",\"MESSAGE_GROUP\","
                + "\"POST_PHOTO\",\"INTOWN_POST_PUBLISH\",\"INTOWN_POST_TOUCH\",\"INTOWN_COMMENT\","
                + "\"MOMENT_PUBLISH\"],\"time_mg\":\"\",\"time_ic\":\"\",\"time_ipp\":\"\",\"time_upb\":\"\","
                + "\"time_ipt\":\"\",\"time_cmt\":\"\",\"time_uun\":\"\",\"time_uut\":\"\",\"time_mp\":\"\","
                + "\"time_uua\":\"\",\"time_msg\":\"\",\"time_pp\":\"\"},\"registerLocation\":\"********\","
                + "\"anchorCategory\":null,\"moreLogToShow\":false,\"banComment\":\"永久封禁\","
                + "\"messageFlag\":\"所有人可私信我\",\"selectLogType\":0}}";

        String tarJson = "{\"result\":1,\"message\":\"\",\"data\":{\"userLogs\":[{\"logType\":\"正常\","
                + "\"adminName\":\"胡俊峰\",\"sourcePage\":\"用户日志记录\",\"time\":1619335952000,"
                + "\"desc\":\"用户分级-用户垂类添加垂类标签:[GOOD_LOOKING, TRENDING_STYLES]\"},{\"logType\":\"正常\","
                + "\"adminName\":\"胡俊峰\",\"sourcePage\":\"用户日志记录\",\"time\":1619335952000,\"desc\":\"用户分级:S\"},"
                + "{\"logType\":\"查询用户敏感数据\",\"adminName\":\"梁丽珠\",\"sourcePage\":\"用户主页\",\"time\":1619174938000,"
                + "\"desc\":\"registerPhone registerEmail latitudeAndLongitude lastStartUp \"},{\"logType\":\"正常\","
                + "\"adminName\":\"夏兴璞\",\"sourcePage\":\"用户日志记录\",\"time\":1618278822000,\"desc\":\"用户分级:S\"},"
                + "{\"logType\":\"正常\",\"adminName\":\"夏兴璞\",\"sourcePage\":\"用户日志记录\",\"time\":1618278822000,"
                + "\"desc\":\"用户分级-用户垂类添加垂类标签:[GOOD_LOOKING, TRENDING_STYLES]\"},{\"logType\":\"用户设置\","
                + "\"adminName\":\"系统(System)\",\"sourcePage\":\"简介审核\",\"time\":1618206806000,\"desc\":\"简介: Hi"
                + ".!\\uD83D\\uDC4Bit's me kanaya CEO of Lipsync don't forget To follow my IG: kanayalecy\"},"
                + "{\"logType\":\"用户设置\",\"adminName\":\"系统(System)\",\"sourcePage\":\"简介审核\",\"time\":1618167841000,"
                + "\"desc\":\"简介: Hi.!\\uD83D\\uDC4Bit's me kanaya CEO of Lipsync and random dance don't forget To "
                + "follow my IG: kanayalecy\"},{\"logType\":\"用户设置\",\"adminName\":\"系统(System)\","
                + "\"sourcePage\":\"简介审核\",\"time\":1617768400000,\"desc\":\"简介: Hi.!\\uD83D\\uDC4Bit's me kanaya CEO"
                + " of Lipsync and don't forget To follow my IG: kanayalecy\"},{\"logType\":\"正常\","
                + "\"adminName\":\"曹方方\",\"sourcePage\":\"用户日志记录\",\"time\":1617506256000,"
                + "\"desc\":\"用户分级-用户垂类添加垂类标签:[GOOD_LOOKING, TRENDING_STYLES]\"},{\"logType\":\"正常\","
                + "\"adminName\":\"曹方方\",\"sourcePage\":\"用户日志记录\",\"time\":1617506256000,\"desc\":\"用户分级:S\"},"
                + "{\"logType\":\"标记\",\"adminName\":\"系统(System)\",\"sourcePage\":\"用户日志记录\",\"time\":1617366997000,"
                + "\"desc\":\"允许图片. 头像: <img class=\\\"popup-images logImg\\\" src=\\\"http://aws-ind-pic.snackvideo"
                + ".in/bs2/overseaHead/20210402203624_BMTUwMDAwNTQzNTk4Mjc3_s.jpg\\\">\"},{\"logType\":\"标记\","
                + "\"adminName\":\"系统(System)\",\"sourcePage\":\"用户日志记录\",\"time\":1617338599000,\"desc\":\"允许图片. 头像:"
                + " <img class=\\\"popup-images logImg\\\" src=\\\"http://aws-ind-pic.snackvideo"
                + ".in/bs2/overseaHead/20210402124304_BMTUwMDAwNTQzNTk4Mjc3_s.jpg\\\">\"},{\"logType\":\"用户设置\","
                + "\"adminName\":\"系统(System)\",\"sourcePage\":\"用户名审核\",\"time\":1616668856000,\"desc\":\"用户名: You "
                + "can call me Kanaya \\uD83D\\uDE09\"},{\"logType\":\"用户设置\",\"adminName\":\"系统(System)\","
                + "\"sourcePage\":\"简介审核\",\"time\":1616668777000,\"desc\":\"简介: Hi.!\\uD83D\\uDC4Bit's me kanaya CEO"
                + " of Lipsync and random dance ☺️ Thanks for follow me \\uD83D\\uDC8C\"}],"
                + "\"lastLogin\":\"********\",\"lastLoginPort\":0,\"userText\":\"Hi.!\\uD83D\\uDC4Bit's me kanaya CEO"
                + " of Lipsync don't forget To follow my IG: kanayalecy\",\"lastStartUpDeviceId\":\"********\","
                + "\"isOverSeaUser\":1,\"userPhone\":\"+62*******1821\",\"headUrl\":\"http://aws-ind-pic.snackvideo"
                + ".in/bs2/overseaHead/20210402203624_BMTUwMDAwNTQzNTk4Mjc3_s.jpg\",\"isTrustDeviceUser\":true,"
                + "\"sysVer\":\"KWAI_BULLDOG_ANDROID_11\",\"likeCount\":138,\"allPhotoCommentClosed\":false,"
                + "\"registerEmail\":\"********\",\"isPartner\":true,\"phoneModel\":\"OPPO(CPH2059)\","
                + "\"bucketLists\":{\"lka\":\"斯里兰卡\",\"ru\":\"俄罗斯桶\",\"ko\":\"韩国桶\",\"spa\":\"西班牙桶\","
                + "\"in\":\"印度尼西亚桶\",\"ms\":\"马来西亚桶\",\"en\":\"英语桶\",\"npl\":\"尼泊尔\",\"en_PH\":\"菲律宾桶\","
                + "\"tur\":\"土耳其桶\",\"br\":\"巴西桶\",\"mmr\":\"缅甸桶\",\"zh_TW\":\"台湾桶\",\"vnm\":\"越南桶\",\"th\":\"泰国桶\","
                + "\"ja\":\"日本桶\",\"pak\":\"巴基斯坦\",\"zh_CN\":\"大陆桶\",\"mix\":\"混合桶\",\"bgd\":\"孟加拉国\","
                + "\"ind\":\"印度桶\"},\"potentialCategory\":null,\"anchorApplyStatus\":1,\"userKwaiId\":\"bmxsq459, "
                + "Kanayalecy\",\"blockPermission\":{\"actionBanBlock\":true,\"userHealth\":true,"
                + "\"registerPhone\":true,\"clearDesc\":true,\"allUserCounts\":true,\"thirdOriginalInfo\":true,"
                + "\"registerEmail\":true,\"languageAndCountry\":true,\"userBanTime\":true,\"promotionChannel\":true,"
                + "\"livestreamPermissions\":true,\"userBanHigh\":true,\"lastStartUp\":true,\"overSeaHomePage\":true,"
                + "\"partBan\":true,\"clearHeader\":true,\"latitudeAndLongitude\":true,\"registerExt\":true,"
                + "\"updateUserName\":true,\"updateDesc\":true,\"familyUpdate\":true,\"userReviewLog\":true,"
                + "\"actionBanBlock4Read\":true,\"userCACSat\":true,\"clearUserName\":true,\"updateUserKwaiId\":true,"
                + "\"cleanAllPhoto\":true},\"longitude\":\"********\",\"userExploreLocaleIntValue\":8,"
                + "\"detailStatus\":[{\"date\":\"2021-05-02\",\"status\":0,\"total\":1,"
                + "\"photos\":[{\"150001294558373\":0}],\"photoIdStr\":\"150001294558373\",\"statusIdStr\":\"0\"},"
                + "{\"date\":\"2021-05-03\",\"status\":0,\"total\":1,\"photos\":[{\"150001296117001\":0}],"
                + "\"photoIdStr\":\"150001296117001\",\"statusIdStr\":\"0\"},{\"date\":\"2021-05-04\",\"status\":0,"
                + "\"total\":1,\"photos\":[{\"150001297804238\":0}],\"photoIdStr\":\"150001297804238\","
                + "\"statusIdStr\":\"0\"},{\"date\":\"2021-05-05\",\"status\":0,\"total\":1,"
                + "\"photos\":[{\"150001299297620\":0}],\"photoIdStr\":\"150001299297620\",\"statusIdStr\":\"0\"},"
                + "{\"date\":\"2021-05-06\",\"status\":0,\"total\":1,\"photos\":[{\"150001300575832\":0}],"
                + "\"photoIdStr\":\"150001300575832\",\"statusIdStr\":\"0\"},{\"date\":\"2021-05-07\",\"status\":0,"
                + "\"total\":2,\"photos\":[{\"150001302077088\":0},{\"150001302066927\":0}],"
                + "\"photoIdStr\":\"150001302077088,150001302066927\",\"statusIdStr\":\"0,0\"},"
                + "{\"date\":\"2021-05-08\",\"status\":0,\"total\":1,\"photos\":[{\"150001303454688\":0}],"
                + "\"photoIdStr\":\"150001303454688\",\"statusIdStr\":\"0\"},{\"date\":\"2021-05-09\",\"status\":0,"
                + "\"total\":2,\"photos\":[{\"150001304966959\":0},{\"150001304957931\":0}],"
                + "\"photoIdStr\":\"150001304966959,150001304957931\",\"statusIdStr\":\"0,0\"},"
                + "{\"date\":\"2021-05-10\",\"status\":0,\"total\":2,\"photos\":[{\"150001306389038\":0},"
                + "{\"150001306376255\":0}],\"photoIdStr\":\"150001306389038,150001306376255\",\"statusIdStr\":\"0,"
                + "0\"},{\"date\":\"2021-05-11\",\"status\":0,\"total\":2,\"photos\":[{\"150001307766065\":0},"
                + "{\"150001307760639\":0}],\"photoIdStr\":\"150001307766065,150001307760639\",\"statusIdStr\":\"0,"
                + "0\"},{\"date\":\"2021-05-12\",\"status\":0,\"total\":1,\"photos\":[{\"150001309262578\":0}],"
                + "\"photoIdStr\":\"150001309262578\",\"statusIdStr\":\"0\"},{\"date\":\"2021-05-13\",\"status\":0,"
                + "\"total\":1,\"photos\":[{\"150001310567709\":0}],\"photoIdStr\":\"150001310567709\","
                + "\"statusIdStr\":\"0\"},{\"date\":\"2021-05-14\",\"status\":0,\"total\":1,"
                + "\"photos\":[{\"150001312065266\":0}],\"photoIdStr\":\"150001312065266\",\"statusIdStr\":\"0\"},"
                + "{\"date\":\"2021-05-15\",\"status\":0,\"total\":1,\"photos\":[{\"150001313626606\":0}],"
                + "\"photoIdStr\":\"150001313626606\",\"statusIdStr\":\"0\"},{\"date\":\"2021-05-16\",\"status\":0,"
                + "\"total\":1,\"photos\":[{\"150001316228569\":0}],\"photoIdStr\":\"150001316228569\","
                + "\"statusIdStr\":\"0\"},{\"date\":\"2021-05-17\",\"status\":0,\"total\":1,"
                + "\"photos\":[{\"150001316928120\":0}],\"photoIdStr\":\"150001316928120\",\"statusIdStr\":\"0\"},"
                + "{\"date\":\"2021-05-18\",\"status\":0,\"total\":1,\"photos\":[{\"150001318218092\":0}],"
                + "\"photoIdStr\":\"150001318218092\",\"statusIdStr\":\"0\"},{\"date\":\"2021-05-19\",\"status\":0,"
                + "\"total\":1,\"photos\":[{\"150001319724982\":0}],\"photoIdStr\":\"150001319724982\","
                + "\"statusIdStr\":\"0\"},{\"date\":\"2021-05-20\",\"status\":0,\"total\":1,"
                + "\"photos\":[{\"150001321254838\":0}],\"photoIdStr\":\"150001321254838\",\"statusIdStr\":\"0\"},"
                + "{\"date\":\"2021-05-21\",\"status\":0,\"total\":1,\"photos\":[{\"150001323326210\":0}],"
                + "\"photoIdStr\":\"150001323326210\",\"statusIdStr\":\"0\"},{\"date\":\"2021-05-22\",\"status\":0,"
                + "\"total\":1,\"photos\":[{\"150001324509142\":0}],\"photoIdStr\":\"150001324509142\","
                + "\"statusIdStr\":\"0\"},{\"date\":\"2021-05-23\",\"status\":0,\"total\":0,\"photos\":[],"
                + "\"photoIdStr\":\"\",\"statusIdStr\":\"\"},{\"date\":\"2021-05-24\",\"status\":0,\"total\":0,"
                + "\"photos\":[],\"photoIdStr\":\"\",\"statusIdStr\":\"\"},{\"date\":\"2021-05-25\",\"status\":0,"
                + "\"total\":1,\"photos\":[{\"150001329809056\":0}],\"photoIdStr\":\"150001329809056\","
                + "\"statusIdStr\":\"0\"},{\"date\":\"2021-05-26\",\"status\":0,\"total\":0,\"photos\":[],"
                + "\"photoIdStr\":\"\",\"statusIdStr\":\"\"},{\"date\":\"2021-05-27\",\"status\":0,\"total\":1,"
                + "\"photos\":[{\"150001333937331\":0}],\"photoIdStr\":\"150001333937331\",\"statusIdStr\":\"0\"},"
                + "{\"date\":\"2021-05-28\",\"status\":0,\"total\":0,\"photos\":[],\"photoIdStr\":\"\","
                + "\"statusIdStr\":\"\"},{\"date\":\"2021-05-29\",\"status\":0,\"total\":0,\"photos\":[],"
                + "\"photoIdStr\":\"\",\"statusIdStr\":\"\"},{\"date\":\"2021-05-30\",\"status\":2,\"total\":2,"
                + "\"photos\":[{\"150001339878603\":0},{\"150001339854166\":2}],\"photoIdStr\":\"150001339878603,"
                + "150001339854166\",\"statusIdStr\":\"0,2\"},{\"date\":\"2021-05-31\",\"status\":0,\"total\":0,"
                + "\"photos\":[],\"photoIdStr\":\"\",\"statusIdStr\":\"\"}],\"registerIp\":\"********\","
                + "\"userExploreLocale\":\"印度尼西亚桶\",\"postTotalDays\":24,\"healthyPostDays\":23,"
                + "\"shadowStatusDesc\":\"否\",\"selectOperator\":0,\"hasRegisterLocation\":true,"
                + "\"userMotherLanguage\":\"in_ID\",\"registerDevice\":\"ANDROID_f467bfa3c57069b9\","
                + "\"userChannel\":\"null-null--\",\"loginLocation\":\"********\",\"userCounts\":{\"photo\":0,"
                + "\"fan\":9940,\"admView\":\"用户-粉丝数:9940<br />用户-关注数:12<br />\",\"pureFan\":9940,\"fanOffset\":0,"
                + "\"notify\":0,\"black\":1,\"fansCountRENREN\":0,\"fansCountQQWEIBO\":0,\"fansCountSINA\":0,"
                + "\"newNotifyComment\":0,\"newNotifyLike\":0,\"newNotifyMessage\":0,\"newNotifyReply\":0,"
                + "\"newNotifyFollow\":0,\"newMayfriend\":0,\"newCommentLike\":0,\"newFollowfeed\":0,"
                + "\"newFollowrequest\":0,\"newAt\":0,\"newPhotoSameFrame\":0,\"newMusicUsed\":0,"
                + "\"newIntownComment\":0,\"newIntownCommentReply\":0,\"newMissU\":0,\"newNotifyMomentLike\":0,"
                + "\"newNotifyMomentComment\":0,\"newNotifyMomentCommentReply\":0,\"newNotifyMomentAt\":0,"
                + "\"newNotifyMomentCommentAt\":0,\"acceptFollowRequest\":0,\"newShareTokenOpened\":0,"
                + "\"newRewardCount\":0,\"newFollowingMomentCount\":0,\"newMissUAuthorNewPhoto\":0,"
                + "\"profileVisit\":0,\"follow\":12},\"flowSimpleInfo\":{\"idType\":\"author_id\","
                + "\"businessId\":150000543598277,\"taskStatus\":\"completed\",\"realView\":null,\"consumed\":10087,"
                + "\"flowQuota\":10000},\"isVerified\":false,\"latitude\":\"********\","
                + "\"registerTypeDesc\":\"********\",\"registerType\":\"********\",\"commentPartBan\":false,"
                + "\"isPotentialUser\":false,\"photoCount\":78,\"userBanTimes\":0,\"unhealthyPostDays\":1,"
                + "\"clientVer\":\"3.5.2.438\",\"startTime\":1622437204351,\"userCache\":{\"userId\":150000543598277,"
                + "\"userName\":\"You can call me Kanaya \\uD83D\\uDE09\",\"userHead\":\"http://aws-ind-pic"
                + ".snackvideo.in/bs2/overseaHead/20210402203624_BMTUwMDAwNTQzNTk4Mjc3_s.jpg\",\"userSex\":\"F\","
                + "\"userStatus\":0,\"userSettings\":12338,\"userText\":\"Hi.!\\uD83D\\uDC4Bit's me kanaya CEO of "
                + "Lipsync don't forget To follow my IG: kanayalecy\",\"birthday\":\"2000-09-06\","
                + "\"extParams\":{\"head\":15},\"rawUserHead\":\"http://aws-ind-pic.snackvideo"
                + ".in/bs2/overseaHead/20210402203624_BMTUwMDAwNTQzNTk4Mjc3_s.jpg\",\"banned\":false,"
                + "\"userDenyDownloadFlag\":true,\"userDenyPhotoDownloadFlag\":false,\"userDenyCommentFlag\":false,"
                + "\"userDenyMessageFlag\":false,\"userNotRecommendToContacts\":false,"
                + "\"userNotRecommendToQqFriends\":false,\"userPrivacyNewsFlag\":false,"
                + "\"downloadMyWorkNotAllowedFlag\":true,\"hideVideoYouLikeFlag\":true,"
                + "\"hideMyDiamondLevelFlag\":false,\"rawUserName\":\"You can call me Kanaya \\uD83D\\uDE09\","
                + "\"userHeadAdmin\":\"http://aws-ind-pic.snackvideo"
                + ".in/bs2/overseaHead/20210402203624_BMTUwMDAwNTQzNTk4Mjc3_s.jpg\",\"male\":false,\"female\":true,"
                + "\"userPrivacyFlag\":false,\"userLocationPrivacyFlag\":true,\"unknown\":false,"
                + "\"bigHead\":\"http://aws-ind-pic.snackvideo"
                + ".in/bs2/overseaHead/20210402203624_BMTUwMDAwNTQzNTk4Mjc3_b.jpg\",\"tinyHead\":\"http://aws-ind-pic"
                + ".snackvideo.in/bs2/overseaHead/20210402203624_BMTUwMDAwNTQzNTk4Mjc3_t.jpg\","
                + "\"smallWebp\":\"http://aws-ind-pic.snackvideo"
                + ".in/bs2/overseaHead/20210402203624_BMTUwMDAwNTQzNTk4Mjc3_sw.webp\","
                + "\"tinyWebp\":\"http://aws-ind-pic.snackvideo"
                + ".in/bs2/overseaHead/20210402203624_BMTUwMDAwNTQzNTk4Mjc3_tw.webp\"},\"registerDate\":\"********\","
                + "\"clientId\":2,\"socialBanned\":false,\"officialFamilyCategory\":null,"
                + "\"userKwaiIds\":[\"Kanayalecy\",\"bmxsq459\"],\"userName\":\"You can call me Kanaya "
                + "\\uD83D\\uDE09\",\"exploreLocales\":[\"zh_CN\",\"zh_TW\",\"en\",\"ko\",\"ja\",\"th\",\"ms\","
                + "\"in\",\"ru\",\"en_PH\",\"br\",\"vnm\",\"mmr\",\"ind\",\"tur\",\"spa\",\"mix\",\"pak\",\"bgd\","
                + "\"npl\",\"lka\"],\"userId\":150000543598277,\"localeList\":[\"zh-TW\",\"en\",\"ko\",\"ja\",\"th\","
                + "\"ms\",\"id\",\"ru\",\"fil\",\"pt\",\"vi\",\"my\",\"hi\",\"de\",\"tr\",\"fr\",\"es\",\"spa\","
                + "\"ar\",\"ps\",\"ur\",\"pa\",\"sd\",\"bn\",\"ne\",\"si\"],"
                + "\"actionBanMap\":{\"types\":[\"USER_NAME\",\"USER_TEXT\",\"USER_AVATAR\",\"USER_BG\",\"COMMENT\","
                + "\"MESSAGE\",\"MESSAGE_GROUP\",\"POST_PHOTO\",\"INTOWN_POST_PUBLISH\",\"INTOWN_POST_TOUCH\","
                + "\"INTOWN_COMMENT\",\"MOMENT_PUBLISH\"],\"time_mg\":\"\",\"time_ic\":\"\",\"time_ipp\":\"\","
                + "\"time_upb\":\"\",\"time_ipt\":\"\",\"time_cmt\":\"\",\"time_uun\":\"\",\"time_uut\":\"\","
                + "\"time_mp\":\"\",\"time_uua\":\"\",\"time_msg\":\"\",\"time_pp\":\"\"},"
                + "\"registerLocation\":\"********\",\"anchorCategory\":null,\"moreLogToShow\":false,"
                + "\"banComment\":\"永久封禁\",\"messageFlag\":\"所有人可私信我\",\"selectLogType\":0}}";
        JSONObject json = createJson(srcJson, tarJson);

        System.out.println(json.toString());
    }
}
