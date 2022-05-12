package club.kwcoder.report.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class BotUtil {

    @Value("${path.bot.templates}")
    private String TEMPLATE_PATH;

    @Value("${path.bot.app}")
    private String botAppPath;

    public String getTemplatePath() {
        return this.TEMPLATE_PATH;
    }

    public String getBotPath(String port) {
        return botAppPath + port + "/";
    }

    public String getCurrentLogPath(String port) {
        return this.getBotPath(port) + "logs/" + LocalDate.now() + ".log";
    }

}
