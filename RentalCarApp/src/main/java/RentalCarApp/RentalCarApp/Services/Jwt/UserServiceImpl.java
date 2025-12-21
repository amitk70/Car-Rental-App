package RentalCarApp.RentalCarApp.Services.Jwt;

import RentalCarApp.RentalCarApp.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements  UserService{


    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetailsService userDetailsSerices() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                return userRepository.findFirstByEmail(username)
                        .orElseThrow(()-> new UsernameNotFoundException("User Not Found"));
            }
        };
    }
}
