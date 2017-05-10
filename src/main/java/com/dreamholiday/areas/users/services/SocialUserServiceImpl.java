package com.dreamholiday.areas.users.services;

import com.dreamholiday.areas.users.entities.SocialUser;
import com.dreamholiday.areas.users.enums.Gender;
import com.dreamholiday.areas.users.repositories.SocialUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.social.facebook.api.User;
import org.springframework.stereotype.Service;

@Service
public class SocialUserServiceImpl implements SocialUserService {

    private final SocialUserRepository socialUserRepository;

    private final RoleService roleService;

    @Autowired
    public SocialUserServiceImpl(SocialUserRepository socialUserRepository, RoleService roleService) {
        this.socialUserRepository = socialUserRepository;
        this.roleService = roleService;
    }

    @Override
    public void registerOrLogin(User facebookUser) {
        String email = facebookUser.getEmail();
        SocialUser socialUser = this.socialUserRepository.findOneByUsername(email);
        if (socialUser == null) {
            socialUser = this.registerUser(email, facebookUser);
        }

        loginUser(socialUser);
    }

    private SocialUser registerUser(String email, User facebookUser) {
        SocialUser user = new SocialUser();
        user.setFirstName(facebookUser.getFirstName());
        user.setLastName(facebookUser.getLastName());
        user.setGender(Gender.valueOf(facebookUser.getGender().toUpperCase()));
        user.setUsername(email);
        user.setProvider("FACEBOOK");
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setCredentialsNonExpired(true);
        user.setEnabled(true);
        user.addRole(this.roleService.getDefaultRole());
        this.socialUserRepository.save(user);
        return user;
    }

    private void loginUser(SocialUser socialUser) {
        Authentication authentication = new UsernamePasswordAuthenticationToken(socialUser, null, socialUser.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}
