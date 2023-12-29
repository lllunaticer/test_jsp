package testJson;

import java.util.Collections;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.NumberSerializer;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.kuaishou.framework.util.ObjectMapperUtils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * @author Xingjian LONG <longxingjian@kuaishou.com>
 * Created on 2022-05-06
 */
@Data
@Builder(toBuilder = true)
@AllArgsConstructor
public class User {
    @JsonSerialize(using = ToStringSerializer.class)
    private long materialId;
    private String materialName;  // 标题
    private List<Book> books;

    public static void main(String[] args) {
        User xxx = User.builder()
                .materialId(123L)
                .materialName("xxx")
                .books(
                        Collections.singletonList(
                                Book.builder()
                                        .id(456L)
                                        .name("book")
                                        .build()
                        )
                )
                .build();
        System.out.println(ObjectMapperUtils.toJSON(xxx));
    }

    @Data
    @Builder
    static class Book {
        private long id;
        private String name;
    }
}
