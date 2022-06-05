package club.kwcoder.report.model.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class LogDTO {

    private String time;
    private String level;
    private String content;

    public String toLog() {
        return this.time + " " + this.level + " " + this.content;
    }

}
