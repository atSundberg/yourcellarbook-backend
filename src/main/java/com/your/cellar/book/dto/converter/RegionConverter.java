package com.your.cellar.book.dto.converter;

import com.your.cellar.book.dto.RegionDto;
import com.your.cellar.book.entity.Region;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class RegionConverter {
    private ModelMapper modelMapper;

    @Autowired
    public RegionConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Region RegionDtoToRegion(RegionDto regionDto) {
        return this.modelMapper.map(regionDto, Region.class);
    }

    public RegionDto regionToRegionDto(Region region) {
        return this.modelMapper.map(region, RegionDto.class);
    }
}


