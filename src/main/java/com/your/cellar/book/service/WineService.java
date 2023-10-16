package com.your.cellar.book.service;

import com.your.cellar.book.dto.GrapeDto;
import com.your.cellar.book.dto.converter.GrapeConverter;
import com.your.cellar.book.dto.converter.WineConverter;
import com.your.cellar.book.dto.request.WineRequestModel;
import com.your.cellar.book.dto.response.WineResponseModel;
import com.your.cellar.book.entity.Grape;
import com.your.cellar.book.entity.Producer;
import com.your.cellar.book.entity.Region;
import com.your.cellar.book.entity.Wine;
import com.your.cellar.book.repository.WineRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class WineService {

    private WineRepository wineRepository;
    private ProducerService producerService;
    private RegionService regionService;
    private GrapeService grapeService;
    private GrapeConverter grapeConverter;
    private WineConverter wineConverter;

    public WineService(WineRepository wineRepository, ProducerService producerService, RegionService regionService, GrapeService grapeService, GrapeConverter grapeConverter, WineConverter wineConverter) {
        this.wineRepository = wineRepository;
        this.producerService = producerService;
        this.regionService = regionService;
        this.grapeService = grapeService;
        this.grapeConverter = grapeConverter;
        this.wineConverter = wineConverter;
    }

    public Set<WineResponseModel> fetchAllWines() {
        List<Wine> wines = wineRepository.findAll();
        return wines.stream().map(wine -> wineConverter.wineToWineDto(wine)).collect(Collectors.toSet());
    }

    public Wine fetchWineById(Long wineId) {
        return wineRepository.findById(wineId).orElse(null);
    }

    public WineResponseModel addWine(WineRequestModel wineRequestModel) {
        Wine wine = wineConverter.wineDtoToWine(wineRequestModel);

        if (wine != null) {
            Producer producer = producerService.getOrCreateProducer(wineRequestModel.getProducer().getName());
            Region region = regionService.getOrCreateRegion(wineRequestModel.getRegion());
            Set<Grape> grapes = wineRequestModel.getGrapes().stream().map(grape -> grapeService.getOrCreateGrape(grape.getName())).collect(Collectors.toSet());

            wine.setGrapes(grapes);
            wine.setProducer(producer);
            wine.setRegion(region);

            wine = wineRepository.save(wine);
            return wineConverter.wineToWineDto(wine);
        }
        return null;
    }


    public WineResponseModel updateWineGrape(Long wineId, List<GrapeDto> grapeRequestDto) {
        Wine wine = wineRepository.findById(wineId).orElse(null);

        if (wine == null) {
            return null;
        }

        Set<Grape> grapes = grapeRequestDto.stream().map(grape -> grapeConverter.grapeDtoToGrape(grape)).collect(Collectors.toSet());

        if (!grapes.isEmpty()) {
            wine.setGrapes(grapes);
            wineRepository.save(wine);
            return wineConverter.wineToWineDto(wine);
        }
        return null;
    }
}

