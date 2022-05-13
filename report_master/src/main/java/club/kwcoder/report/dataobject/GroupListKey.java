package club.kwcoder.report.dataobject;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * group_list
 * @author 
 */
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class GroupListKey implements Serializable {
    private String botId;

    private String groupId;

    private static final long serialVersionUID = 1L;
}