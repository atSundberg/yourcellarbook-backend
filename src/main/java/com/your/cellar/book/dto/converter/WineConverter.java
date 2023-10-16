package com.your.cellar.book.dto.converter;

import com.your.cellar.book.dto.request.WineRequestModel;
import com.your.cellar.book.dto.response.WineResponseModel;
import com.your.cellar.book.entity.Wine;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class WineConverter {
    private ModelMapper modelMapper;

    @Autowired
    public WineConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Wine wineDtoToWine(WineRequestModel wineRequestModel) {
        return this.modelMapper.map(wineRequestModel, Wine.class);
    }

    public WineResponseModel wineToWineDto(Wine wine) {
        return this.modelMapper.map(wine, WineResponseModel.class);
    }
}

