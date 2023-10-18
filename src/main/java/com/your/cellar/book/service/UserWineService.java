package com.your.cellar.book.service;

import com.your.cellar.book.dto.UserWineDto;
import com.your.cellar.book.dto.converter.UserWineConverter;
import com.your.cellar.book.entity.UserWine;
import com.your.cellar.book.repository.UserWineRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class UserWineService {

    private static final Logger LOG = LoggerFactory.getLogger(UserWineService.class);
    private UserWineRepository userWineRepository;
    private WineService wineService;
    private UserWineConverter userWineConverter;

    @Autowired
    public UserWineService(UserWineRepository userWineRepository, WineService wineService, UserWineConverter userWineConverter) {
        this.userWineRepository = userWineRepository;
        this.wineService = wineService;
        this.userWineConverter = userWineConverter;
    }

    public Set<UserWineDto> fetchAllUserWines(Long userId) {
        List<UserWine> userWines = userWineRepository.findByUserId(userId);
        LOG.info("userWines: {}", userWines);

        List<UserWineDto> userWineDtos = new ArrayList<>();

        for (UserWine userWine : userWines) {
            UserWineDto userWineDto = userWineConverter.userWineToUserWineDto(userWine);
            userWineDto.setWine(wineService.fetchWineById(userWine.getId().getWineId()));
            userWineDtos.add(userWineDto);
        }
        LOG.info("userWines: {}", userWineDtos);

        return new HashSet<>(userWineDtos);
    }

    public UserWineDto updateUserWineInformation(UserWineDto userWineRequestDto) {
        UserWine userWine = userWineRepository.findById(userWineRequestDto.getId()).orElse(null);
        if (userWine == null || userWineRequestDto.getInformation() == null) {
            return null;
        }

        userWine.setInformation(userWineRequestDto.getInformation());
        userWineRepository.save(userWine);

        return userWineConverter.userWineToUserWineDto(userWine);
    }

    public UserWineDto updateWineQuantity(UserWineDto userWineRequestDto) {
        UserWine userWine = userWineRepository.findById(userWineRequestDto.getId()).orElse(null);
        if (userWine == null || Objects.equals(userWineRequestDto.getQuantity(), userWine.getQuantity())) {
            return null;
        }

        userWine.setQuantity(userWineRequestDto.getQuantity() > 0 ? userWineRequestDto.getQuantity() : 0);
        userWine.setFinished(userWine.getQuantity() <= 0);

        userWine = userWineRepository.save(userWine);
        return userWineConverter.userWineToUserWineDto(userWine);
    }

    public UserWineDto createNewUserWine(UserWineDto userWineRequestDto) {
        UserWine userWine = userWineConverter.userWineDtoToUserWine(userWineRequestDto);
        if (userWine.getId() != null && userWineRepository.findById(userWine.getId()).orElse(null) == null) {
            userWine.setCreatedAt(LocalDateTime.now());
            userWine = userWineRepository.save(userWine);

            UserWineDto userWineDto = userWineConverter.userWineToUserWineDto(userWine);
            userWineDto.setWine(wineService.fetchWineById(userWine.getId().getWineId()));
            return userWineDto;
        } else {
            return null;
        }
    }

}
