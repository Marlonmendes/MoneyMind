package com.moneymind.user.interfaces.rest;

import com.moneymind.user.application.RegisterUserUseCase;
import com.moneymind.user.domain.model.UserProfile;
import com.moneymind.user.domain.model.Users;
import com.moneymind.user.domain.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final RegisterUserUseCase userUseCase;

    public UserController(RegisterUserUseCase registerUserUseCase) {
        this.userUseCase = registerUserUseCase;
    }

    @PostMapping
    public ResponseEntity<?> registerUser(
            @RequestBody RegisterUserRequest request
    ) {

        userUseCase.execute(
                request.getEmail(),
                request.getPassword(),
                request.getFirstName(),
                request.getLastName(),
                request.getTimezone(),
                request.getLanguage());

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(request);
    }

    @GetMapping
    public Page<Users> findAll(Pageable pageable){
        return userUseCase.findAll(pageable);
    }
}
