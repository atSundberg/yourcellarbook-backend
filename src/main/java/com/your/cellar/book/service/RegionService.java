package com.your.cellar.book.service;

import com.your.cellar.book.dto.RegionDto;
import com.your.cellar.book.dto.converter.RegionConverter;
import com.your.cellar.book.entity.Region;
import com.your.cellar.book.repository.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RegionService {

    private RegionRepository regionRepository;
    private RegionConverter regionConverter;

    @Autowired
    public RegionService(RegionRepository regionRepository, RegionConverter regionConverter) {
        this.regionRepository = regionRepository;
        this.regionConverter = regionConverter;
    }

    public Region getOrCreateRegion(Region region) {
        Region reg = regionRepository.findRegionByName(region.getName());

        if (reg != null) {
            return reg;
        } else {
            Region newRegion = new Region(region.getName(), region.getCountry());
            return regionRepository.save(newRegion);
        }
    }

    public Set<RegionDto> fetchAllRegions() {
        List<Region> regions = regionRepository.findAll();
        return regions.stream().map(region -> regionConverter.regionToRegionDto(region)).collect(Collectors.toSet());
    }

    public RegionDto addNewRegion(RegionDto regionRequestDto) {
        Region existingRegion = regionRepository.findRegionByName(regionRequestDto.getName());

        if (existingRegion != null && (existingRegion.getCountry().equals(regionRequestDto.getCountry()))) {
                return null;
        }

        if (regionRequestDto.getName() != null && regionRequestDto.getCountry() != null) {
            Region region = regionConverter.RegionDtoToRegion(regionRequestDto);

            region = regionRepository.save(region);
            return regionConverter.regionToRegionDto(region);
        }
        return null;
    }
}
