package RentalCarApp.RentalCarApp.Dto;

import RentalCarApp.RentalCarApp.Enums.UserRole;
import jakarta.persistence.Entity;
import lombok.Data;


@Data
public class UserDto {

    private Long id;

    private String name;

    private String email;

    private String password;

    private UserRole userRole;



}
