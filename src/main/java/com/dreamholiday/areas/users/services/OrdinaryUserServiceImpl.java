package com.dreamholiday.areas.users.services;

import com.dreamholiday.areas.users.entities.OrdinaryUser;
import com.dreamholiday.areas.users.entities.Role;
import com.dreamholiday.areas.users.entities.User;
import com.dreamholiday.areas.users.enums.Gender;
import com.dreamholiday.areas.users.models.bindingModels.AddUserBindingModel;
import com.dreamholiday.areas.users.models.bindingModels.EditUserBindingModel;
import com.dreamholiday.errors.Errors;
import com.dreamholiday.areas.users.models.viewModels.EditUserViewModel;
import com.dreamholiday.areas.users.repositories.OrdinaryUserRepository;
import com.dreamholiday.areas.users.repositories.RoleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class OrdinaryUserServiceImpl implements OrdinaryUserService {

    private final OrdinaryUserRepository userRepository;

    private final ModelMapper modelMapper;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final RoleRepository roleRepository;

    @Autowired
    public OrdinaryUserServiceImpl(OrdinaryUserRepository userRepository, ModelMapper modelMapper, BCryptPasswordEncoder bCryptPasswordEncoder, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.roleRepository = roleRepository;
    }

    @Override
    public void register(AddUserBindingModel addUserBindingModel) {
        OrdinaryUser user = this.modelMapper.map(addUserBindingModel, OrdinaryUser.class);
        long totalUserCount = this.userRepository.count();
        if (totalUserCount == 0) {
            Role role = this.roleRepository.findOneByAuthority("ROLE_ADMIN");
            user.getAuthorities().add(role);
        } else {
            Role role = this.roleRepository.findOneByAuthority("ROLE_USER");
            user.getAuthorities().add(role);
        }

        String password = this.bCryptPasswordEncoder.encode(addUserBindingModel.getPassword());
        user.setPassword(password);
        user.setGender(Gender.valueOf(addUserBindingModel.getGender().toUpperCase()));
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setCredentialsNonExpired(true);
        user.setEnabled(true);

        this.userRepository.save(user);
    }

    @Override
    public EditUserViewModel findOneToEdit(Long id) {
        User user = this.userRepository.findOne(id);
        EditUserViewModel editUserViewModel = this.modelMapper.map(user, EditUserViewModel.class);

        return editUserViewModel;
    }

    @Override
    public void update(EditUserBindingModel editUserBindingModel) {

    }

//    @Override
//    public void update(EditUserBindingModel editUserBindingModel) {
//        OrdinaryUser user = this.userRepository.findOne(editUserBindingModel.getId());
//        user.setFirstName(editUserBindingModel.getFirstName());
//        user.setLastName(editUserBindingModel.getLastName());
//        user.setUsername(editUserBindingModel.getUsername());
//        user.setPhone(editUserBindingModel.getPhone());
//        this.userRepository.saveAndFlush(user);
//    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.userRepository.findOneByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(Errors.INVALID_CREDENTIALS);
        }

        return user;
    }
}
