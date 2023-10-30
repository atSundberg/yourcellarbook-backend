package com.your.cellar.book.dto.converter;

import com.your.cellar.book.dto.UserWineDto;
import com.your.cellar.book.dto.UserWinePublicDto;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class UserWinePublicConverter {

    private ModelMapper modelMapper;

    @Autowired
    public UserWinePublicConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public UserWineDto userWinePublicDtoToUserWineDto(UserWinePublicDto userWinePublicDto) {
        return this.modelMapper.map(userWinePublicDto, UserWineDto.class);
    }

    public UserWinePublicDto userWineDtoToUserWinePublicDto(UserWineDto userWineDto) {
        return this.modelMapper.map(userWineDto, UserWinePublicDto.class);
    }
}
