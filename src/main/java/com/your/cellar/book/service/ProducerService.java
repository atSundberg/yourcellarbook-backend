package com.your.cellar.book.service;

import com.your.cellar.book.dto.ProducerDto;
import com.your.cellar.book.dto.converter.ProducerConverter;
import com.your.cellar.book.entity.Producer;
import com.your.cellar.book.repository.ProducerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ProducerService {

    private ProducerRepository producerRepository;
    private ProducerConverter producerConverter;

    @Autowired
    public ProducerService(ProducerRepository producerRepository, ProducerConverter producerConverter) {
        this.producerRepository = producerRepository;
        this.producerConverter = producerConverter;
    }

    public Producer getOrCreateProducer(String name) {
        Producer producer = producerRepository.findProducerByName(name);
        if (producer != null) {
            return producer;
        } else {
            Producer newProducer = new Producer(name);
            return producerRepository.save(newProducer);
        }
    }

    public Set<ProducerDto> getAllProducers() {
        List<Producer> producers = producerRepository.findAll();
        return producers.stream().map(producer -> producerConverter.producerToProducerDto(producer)).collect(Collectors.toSet());
    }

    public ProducerDto addNewProducer(ProducerDto producerRequestDto) {
        Producer producer = producerConverter.ProducerDtoToProducer(producerRequestDto);
        if (producerRepository.findProducerByName(producer.getName()) == null) {
            producer = producerRepository.save(producer);
            return producerConverter.producerToProducerDto(producer);
        }
        return null;
    }
}
