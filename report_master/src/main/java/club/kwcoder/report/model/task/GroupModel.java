package club.kwcoder.report.model.task;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class GroupModel {

    @Data
    public static class Group {

        Integer group_create_time;
        String group_id;
        Integer group_level;
        String group_memo;
        String group_name;
        Integer max_member_count;
        Integer member_count;

    }

    private List<Group> data = new ArrayList<>();
    private Integer retcode;
    private String status;

}
