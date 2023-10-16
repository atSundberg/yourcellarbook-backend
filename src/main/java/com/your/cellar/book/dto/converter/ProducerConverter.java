package com.your.cellar.book.dto.converter;

import com.your.cellar.book.dto.ProducerDto;
import com.your.cellar.book.entity.Producer;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class ProducerConverter {
    private ModelMapper modelMapper;

    @Autowired
    public ProducerConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Producer ProducerDtoToProducer(ProducerDto producerDto) {
        return this.modelMapper.map(producerDto, Producer.class);
    }

    public ProducerDto producerToProducerDto(Producer producer) {
        return this.modelMapper.map(producer, ProducerDto.class);
    }
}
