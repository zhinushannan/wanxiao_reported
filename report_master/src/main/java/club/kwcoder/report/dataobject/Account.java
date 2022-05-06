package club.kwcoder.report.dataobject;

import java.io.Serializable;
import lombok.Data;

/**
 * account
 * @author 
 */
@Data
public class Account implements Serializable {
    private String teacherName;

    private String username;

    private String password;

    private static final long serialVersionUID = 1L;
}