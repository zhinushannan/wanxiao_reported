package club.kwcoder.report.model.dto;

import lombok.Data;

@Data
public class BotInsertDTO {

    private String accountUin;
    private String accountPassword;
    private Integer serversHttpPort;

}
