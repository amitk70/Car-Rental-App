package RentalCarApp.RentalCarApp.Services;

import RentalCarApp.RentalCarApp.Dto.Signup;
import RentalCarApp.RentalCarApp.Dto.UserDto;
import RentalCarApp.RentalCarApp.Entity.User;
import RentalCarApp.RentalCarApp.Enums.UserRole;
import RentalCarApp.RentalCarApp.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticatoinServiceImpl implements AuthenticationService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDto createUser(Signup signup) {
        User user = new User();
        user.setName(signup.getName());
        user.setEmail(signup.getEmail());
        user.setPassword(new BCryptPasswordEncoder().encode(signup.getPassword()));
        user.setUserRole(UserRole.CUSTOMER);
        User createdCustomer=userRepository.save(user);
        UserDto createdUserDto = new UserDto();
        createdUserDto.setId((createdCustomer.getId()));
        createdUserDto.setName(createdCustomer.getName());
        createdUserDto.setEmail(createdCustomer.getEmail());
        createdUserDto.setUserRole(createdCustomer.getUserRole());
        return createdUserDto;
    }

    @Override
    public boolean hasCustomerWithSameEmail(String email) {
        return userRepository.findFirstByEmail(email).isPresent();
    }
}
