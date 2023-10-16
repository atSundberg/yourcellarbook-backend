package com.your.cellar.book.dto.converter;

import com.your.cellar.book.dto.GrapeDto;
import com.your.cellar.book.entity.Grape;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class GrapeConverter {
    private ModelMapper modelMapper;

    @Autowired
    public GrapeConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Grape grapeDtoToGrape(GrapeDto grapeDto) {
        return this.modelMapper.map(grapeDto, Grape.class);
    }

    public GrapeDto grapeToGrapeDto(Grape grape) {
        return this.modelMapper.map(grape, GrapeDto.class);
    }
}


