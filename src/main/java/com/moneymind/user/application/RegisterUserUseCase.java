package com.moneymind.user.application;

import com.moneymind.user.domain.model.Credentials;
import com.moneymind.user.domain.model.FinancialSettings;
import com.moneymind.user.domain.model.UserProfile;
import com.moneymind.user.domain.model.Users;
import com.moneymind.user.domain.repository.UserRepository;
import com.moneymind.user.domain.valueObject.Email;
import com.moneymind.user.domain.valueObject.UserId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RegisterUserUseCase {
    private final UserRepository repository;

    private final PasswordEncoder passwordEncoder;

    public RegisterUserUseCase(UserRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserId execute(
            String email,
            String password,
            String firstName,
            String lastName,
            String timezone,
            String language
    ) {

        Email emailUser = new Email(email);

        repository.findByEmail(emailUser)
                .ifPresent(u -> {
                    throw new RuntimeException("Email already exists");
                });

        UserId id = UserId.newId();
        Credentials credentials = Credentials.create(emailUser, passwordEncoder.encode(password));
        FinancialSettings financialSettings = FinancialSettings.defaultSettings();
        UserProfile profile = UserProfile.create(id, firstName, lastName, timezone, language);
        Users users = Users.register(
                id,
                emailUser,
                credentials,
                profile,
                financialSettings
        );

        repository.save(users);

        return users.getId();
    }

    public Page<Users> findAll(Pageable pageable){
        return repository.findAll(pageable);
    }

}