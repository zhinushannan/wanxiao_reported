package club.kwcoder.report.dataobject;

import java.io.Serializable;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelEntity;
import lombok.Data;

/**
 * student
 * @author 
 */
@Data
public class Student implements Serializable {

    @Excel(name = "studentQq")
    private String studentQq;

    @Excel(name = "studentName")
    private String studentName;

    private String studentClazz;

    private static final long serialVersionUID = 1L;
}