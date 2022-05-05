package club.kwcoder.report.dataobject;

import java.io.Serializable;
import lombok.Data;

/**
 * clazz
 * @author 
 */
@Data
public class Clazz implements Serializable {
    private String clazzName;

    private String teacherName;

    private String date;

    private String deptId;

    private String groupId;

    private String botId;

    private Integer delete;

    private static final long serialVersionUID = 1L;
}