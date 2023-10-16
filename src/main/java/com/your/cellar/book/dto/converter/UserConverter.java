package com.your.cellar.book.dto.converter;

import com.your.cellar.book.dto.request.UserRequestModel;
import com.your.cellar.book.dto.response.UserResponseModel;
import com.your.cellar.book.entity.User;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * The UserConverter class is used to convert user dto class to user entity class and vice versa.
 *
 * @author adams.sundberg@gmail.com
 * @version 1.0
 * @since 1.0
 */
@Component
@NoArgsConstructor
public class UserConverter {

    private ModelMapper modelMapper;

    @Autowired
    public UserConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public User userDtoToUser(UserRequestModel userRequestModel) {
        return this.modelMapper.map(userRequestModel, User.class);
    }

    public UserResponseModel userToUserDto(User user) {
        return this.modelMapper.map(user, UserResponseModel.class);
    }
}
