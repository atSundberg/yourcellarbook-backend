package com.your.cellar.book.dto.converter;

import com.your.cellar.book.dto.UserWineDto;
import com.your.cellar.book.entity.UserWine;
import com.your.cellar.book.model.UserWineCompositeKey;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class UserWineConverter {
    private ModelMapper modelMapper;

    @Autowired
    public UserWineConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public UserWine userWineDtoToUserWine(UserWineDto userWineDto) {
        return this.modelMapper.map(userWineDto, UserWine.class);
    }

    public UserWineDto userWineToUserWineDto(UserWine userWine) {
        return this.modelMapper.map(userWine, UserWineDto.class);
    }
}
