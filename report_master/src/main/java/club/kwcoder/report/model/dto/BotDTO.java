package club.kwcoder.report.model.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

@Data
@Accessors(chain = true)
public class BotDTO implements Serializable {

    private String botId;

    private Integer port;

    /**
     * 1正常，0停止

     */
    private Integer status;

    private List<String> clazz;

    private static final long serialVersionUID = 1L;

}