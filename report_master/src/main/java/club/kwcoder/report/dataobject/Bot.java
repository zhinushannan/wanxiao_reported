package club.kwcoder.report.dataobject;

import java.io.Serializable;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * bot
 * @author 
 */
@Data
@Accessors(chain = true)
public class Bot implements Serializable {
    private String botId;

    private Integer port;

    /**
     * 1正常，0停止

     */
    private Integer status;

    private static final long serialVersionUID = 1L;
}