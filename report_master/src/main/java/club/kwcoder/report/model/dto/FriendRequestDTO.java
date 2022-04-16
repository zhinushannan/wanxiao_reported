package club.kwcoder.report.model.dto;

import lombok.Data;

@Data
public class FriendRequestDTO {

    /**
     * 加好友请求的 flag（需从上报的数据中获得）
     */
    private String flag;
    /**
     * 是否同意请求
     */
    private Boolean approve = true;
    /**
     * 添加后的好友备注（仅在同意时有效）
     */
    private String mark = "";

    /**
     * 机器人的QQ号
     */
    private String botId;

}
