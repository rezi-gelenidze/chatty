package io.github.rezi_gelenidze.chatty.auth_service.dto.user;

import java.time.LocalDate;

import io.github.rezi_gelenidze.chatty.auth_service.entity.User;
import io.github.rezi_gelenidze.chatty.auth_service.entity.Profile;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDetailsResponse {
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String bio;
    private String profilePicture;

    public static UserDetailsResponse fromEntity(User user) {
        Profile profile = user.getProfile();

        return new UserDetailsResponse(
                user.getUsername(),
                user.getEmail(),
                profile.getFirstName(),
                profile.getLastName(),
                profile.getDateOfBirth(),
                profile.getBio(),
                profile.getProfilePicture()
        );
    }
}