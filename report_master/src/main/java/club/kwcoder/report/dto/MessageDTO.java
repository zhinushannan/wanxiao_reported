package club.kwcoder.report.dto;

import lombok.Data;

import java.util.Date;

@Data
public class MessageDTO {
        private Long id;

        private String botId;

        private String message;

        /**
         * 发送目标，可能是群组也可能是私信，为群号或QQ账号
         */
        private String targetId;

        /**
         * 0是群组，1是私信
         */
        private Integer type;

        private Date sendTime;

        private static final long serialVersionUID = 1L;
}
