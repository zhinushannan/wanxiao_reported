package club.kwcoder.report.dataobject;

import java.io.Serializable;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * friend_list
 * @author 
 */
@Data
@Accessors(chain = true)
public class FriendList extends FriendListKey implements Serializable {
    private String nickname;

    private String remark;

    private String mark;

    public FriendList setBotId(String botId) {
        super.setBotId(botId);
        return this;
    }

    public FriendList setUserId(String userId) {
        super.setUserId(userId);
        return this;
    }

    private static final long serialVersionUID = 1L;
}