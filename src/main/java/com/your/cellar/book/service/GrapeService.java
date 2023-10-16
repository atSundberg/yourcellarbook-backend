package com.your.cellar.book.service;

import com.your.cellar.book.dto.GrapeDto;
import com.your.cellar.book.dto.converter.GrapeConverter;
import com.your.cellar.book.entity.Grape;
import com.your.cellar.book.repository.GrapeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class GrapeService {

    private GrapeRepository grapeRepository;
    private GrapeConverter grapeConverter;

    @Autowired
    public GrapeService(GrapeRepository grapeRepository, GrapeConverter grapeConverter) {
        this.grapeRepository = grapeRepository;
        this.grapeConverter = grapeConverter;
    }

    public Grape getOrCreateGrape(String name) {
        Grape grape = grapeRepository.findByName(name);
        if (grape != null) {
            return grape;
        } else {
            Grape newGrape = new Grape(name);
            return grapeRepository.save(newGrape);
        }
    }


    public Set<Grape> fetchAllGrapes() {
        return new HashSet<>(grapeRepository.findAll());
    }

    public GrapeDto addNewGrape(GrapeDto grapeRequestDto) {
        Grape grape = grapeConverter.grapeDtoToGrape(grapeRequestDto);
        if (grapeRepository.findByName(grape.getName()) == null) {
            grape = grapeRepository.save(grape);
            return grapeConverter.grapeToGrapeDto(grape);
        }
        return null;
    }
}
