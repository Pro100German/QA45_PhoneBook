package dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@Builder

public class UserDtoLombok {
    private String id;
    private String name;
    private String lastName;
    private String Phone;
    private String email;
    private String Address;
    private String description;

}
