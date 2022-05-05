package club.kwcoder.report.dataobject;

import java.io.Serializable;
import lombok.Data;

/**
 * student
 * @author 
 */
@Data
public class Student implements Serializable {
    private String studentQq;

    private String studentName;

    private String studentClazz;

    private static final long serialVersionUID = 1L;
}