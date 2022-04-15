package club.kwcoder.report.model.task;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class FriendModel {

    @Data
    public static class Friend {

        String nickname;
        String remark;
        String user_id;

    }


    private List<Friend> data = new ArrayList<>();
    private Integer retcode;
    private String status;

}
