package club.kwcoder.report.model.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class ResultBean<T> {

    private boolean flag;
    private Integer code;
    private String message;
    private T data;

    public static <T> ResultBean<T> ok(String message, T data) {
        return new ResultBean<>(true, 200, message, data);
    }


}
