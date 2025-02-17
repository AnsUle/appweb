package sprintboot.appweb.model;

import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class Person {

    private Integer id;
    private String firstName;
    private String lastName;

}
