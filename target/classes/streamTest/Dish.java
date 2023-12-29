package streamTest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Dish {
    private String name;
    /**
     * 热量
     */
    private int calories;

    private Type type;

    /**
     * 菜类型
     */
    public enum Type {
        MEAT,
        FISH,
        VEGETABLE,
        OTHER
    }

    /**
     * CommonUtils.THRESHOLD为400
     * 判断一个菜是否是低热量食物
     */
    public boolean isLowCaloricDish() {
        return 500 > this.getCalories();
    }

}