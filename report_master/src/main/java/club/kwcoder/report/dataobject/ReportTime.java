package club.kwcoder.report.dataobject;

import java.io.Serializable;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * report_time
 * @author 
 */
@Data
@Accessors(chain = true)
public class ReportTime implements Serializable {
    private String clazzName;

    /**
     * 提醒时间，从00:00开始为1，每半小时+1
     */
    private Integer time;

    private static final long serialVersionUID = 1L;
}