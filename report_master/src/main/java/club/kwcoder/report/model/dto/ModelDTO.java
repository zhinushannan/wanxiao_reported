package club.kwcoder.report.model.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

@Data
@Accessors(chain = true)
public class ModelDTO implements Serializable {

    private String teacherName;

    private String username;

    private String password;

    private List<String> clazz;

    private static final long serialVersionUID = 1L;
}