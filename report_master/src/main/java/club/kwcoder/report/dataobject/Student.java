package club.kwcoder.report.dataobject;

import java.io.Serializable;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

/**
 * student
 * @author 
 */
@Data
public class Student implements Serializable {
    @Excel(name = "studentId")
    private String studentId;

    @Excel(name = "studentQq")
    private String studentQq;

    @Excel(name = "studentName")
    private String studentName;

    private String studentClazz;

    /**
     * 是否不在班级内，0在，1不在
（参军或休学或其他情况）
     */
    private Integer remove;

    private static final long serialVersionUID = 1L;
}