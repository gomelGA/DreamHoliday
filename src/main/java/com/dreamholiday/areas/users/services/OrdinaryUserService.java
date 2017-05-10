package com.dreamholiday.areas.users.services;

import com.dreamholiday.areas.users.models.bindingModels.AddUserBindingModel;
import com.dreamholiday.areas.users.models.bindingModels.EditUserBindingModel;
import com.dreamholiday.areas.users.models.viewModels.EditUserViewModel;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface OrdinaryUserService extends UserDetailsService {

    void register(AddUserBindingModel addUserBindingModel);

    EditUserViewModel findOneToEdit(Long id);

    void update(EditUserBindingModel editUserBindingModel);
}
