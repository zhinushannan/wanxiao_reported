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
public class FriendListKey implements Serializable {
    private String botId;

    private String userId;

    private static final long serialVersionUID = 1L;
}