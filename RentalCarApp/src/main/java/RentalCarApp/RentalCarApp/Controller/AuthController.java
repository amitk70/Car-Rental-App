package RentalCarApp.RentalCarApp.Controller;


import RentalCarApp.RentalCarApp.Dto.AuthenticationRequest;
import RentalCarApp.RentalCarApp.Dto.AuthenticationResponse;
import RentalCarApp.RentalCarApp.Dto.Signup;
import RentalCarApp.RentalCarApp.Dto.UserDto;
import RentalCarApp.RentalCarApp.Entity.User;
import RentalCarApp.RentalCarApp.Repository.UserRepository;
import RentalCarApp.RentalCarApp.Services.AuthenticatoinServiceImpl;
import RentalCarApp.RentalCarApp.Services.Jwt.UserService;
import RentalCarApp.RentalCarApp.Services.UserServices;
import RentalCarApp.RentalCarApp.Utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin("http://localhost:4200/")
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthenticatoinServiceImpl authenticationService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    public AuthController(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/signup")
    public ResponseEntity<?> createCustomer(@RequestBody Signup signup){
        if (authenticationService.hasCustomerWithSameEmail(signup.getEmail())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email Already Exist");
        }
        UserDto createdUserDto = authenticationService.createUser(signup);
        if(createdUserDto == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Bad Request");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUserDto);
    }



    @PostMapping("/login")
    public AuthenticationResponse createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest)
        throws BadCredentialsException,
            DisabledException,
            UsernameNotFoundException{
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(),authenticationRequest.getPassword()));
        }catch (BadCredentialsException e){
            throw new BadCredentialsException("Incorrect Username or Password");
        }
        final UserDetails userDetails = userService.userDetailsSerices().loadUserByUsername(authenticationRequest.getEmail());
        Optional<User> optionalUser = userRepository.findFirstByEmail(userDetails.getUsername());
        final String jwt = jwtUtils.generateToken(userDetails);
        AuthenticationResponse authenticationResponse = new AuthenticationResponse();
        if(optionalUser.isPresent()){
            authenticationResponse.setJwt(jwt);
            authenticationResponse.setUserId(optionalUser.get().getId());
            authenticationResponse.setUserRole(optionalUser.get().getUserRole());
        }

        return authenticationResponse;
    }
}
