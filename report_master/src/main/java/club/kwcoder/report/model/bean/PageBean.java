package club.kwcoder.report.model.bean;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

@Data
@Accessors(chain = true)
public class PageBean<T> {

    private Integer page = 1;
    private Integer size = 10;
    private Long total;
    private List<T> data = new ArrayList<>();

}
