package RentalCarApp.RentalCarApp.Services;

import RentalCarApp.RentalCarApp.Dto.Signup;
import RentalCarApp.RentalCarApp.Dto.UserDto;
import org.springframework.stereotype.Service;

@Service
public interface AuthenticationService {

    public UserDto createUser(Signup signup);

    boolean hasCustomerWithSameEmail(String email);
}
