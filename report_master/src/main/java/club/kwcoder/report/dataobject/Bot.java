package club.kwcoder.report.dataobject;

import java.io.Serializable;
import lombok.Data;

/**
 * bot
 * @author 
 */
@Data
public class Bot implements Serializable {
    private String botId;

    private Integer port;

    /**
     * 1正常，0停止

     */
    private Integer status;

    private static final long serialVersionUID = 1L;
}