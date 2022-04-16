package club.kwcoder.report.dataobject;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * bot_request
 * @author 
 */
@Data
public class BotRequest implements Serializable {
    private Integer requestId;

    private String flag;

    private String botId;

    /**
     * 1-friend
0-group
     */
    private Integer type;

    private String targetId;

    private String comment;

    private Date createTime;

    private static final long serialVersionUID = 1L;
}