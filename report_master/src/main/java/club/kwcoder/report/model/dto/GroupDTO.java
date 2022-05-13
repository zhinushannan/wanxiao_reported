package club.kwcoder.report.model.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
public class GroupDTO implements Serializable {

    private String groupId;

    private String clazzName;

    private String mark;

    /**
     * 1 在群内
     * 0 不在群内
     */
    private Boolean isMember;

    private static final long serialVersionUID = 1L;

}
