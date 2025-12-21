package RentalCarApp.RentalCarApp.Dto;

import RentalCarApp.RentalCarApp.Enums.UserRole;
import lombok.Data;

@Data
public class AuthenticationResponse {

    private String jwt;

    private UserRole userRole;

    private Long userId;
}
