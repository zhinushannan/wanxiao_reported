package club.kwcoder.report.dataobject;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * clazz
 * @author 
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
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