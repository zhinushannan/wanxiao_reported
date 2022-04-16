package club.kwcoder.report.dataobject;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * group_list
 * @author 
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
public class GroupList extends GroupListKey implements Serializable {
    private String groupName;

    private Integer maxMemberCount;

    private Integer memberCount;

    private String mark;

    public GroupList setBotId(String botId) {
        super.setBotId(botId);
        return this;
    }

    public GroupList setGroupId(String groupId) {
        super.setGroupId(groupId);
        return this;
    }

    private static final long serialVersionUID = 1L;
}