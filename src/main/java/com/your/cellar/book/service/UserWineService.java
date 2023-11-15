package com.your.cellar.book.service;

import com.your.cellar.book.dto.UserWineDto;
import com.your.cellar.book.dto.UserWinePublicDto;
import com.your.cellar.book.dto.converter.UserWineConverter;
import com.your.cellar.book.dto.converter.UserWinePublicConverter;
import com.your.cellar.book.entity.User;
import com.your.cellar.book.entity.UserWine;
import com.your.cellar.book.entity.Wine;
import com.your.cellar.book.repository.UserRepository;
import com.your.cellar.book.repository.UserWineRepository;
import com.your.cellar.book.repository.WineRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserWineService {

    private static final Integer ZERO = 0;
    private static final Logger LOG = LoggerFactory.getLogger(UserWineService.class);
    private UserWineRepository userWineRepository;
    private WineService wineService;
    private UserWineConverter userWineConverter;
    private UserWinePublicConverter userWinePublicConverter;
    private UserRepository userRepository;
    private UserService userService;
    private WineRepository wineRepository;


    @Autowired
    public UserWineService(UserWineRepository userWineRepository, WineService wineService, UserWineConverter userWineConverter, UserWinePublicConverter userWinePublicConverter, UserRepository userRepository, UserService userService, WineRepository wineRepository) {
        this.userWineRepository = userWineRepository;
        this.wineService = wineService;
        this.userWineConverter = userWineConverter;
        this.userWinePublicConverter = userWinePublicConverter;
        this.userRepository = userRepository;
        this.userService = userService;
        this.wineRepository = wineRepository;
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

    @Transactional
    public UserWineDto createNewUserWine(UserWineDto userWineRequestDto) {
        LOG.info("SERVICE.createNewUserWine.request: {}", userWineRequestDto);
        UserWine userWine = userWineConverter.userWineDtoToUserWine(userWineRequestDto);
        LOG.info("SERVICE.createNewUserWine.converted: {}", userWine);
        if (userWine.getId() != null && userWineRepository.findById(userWine.getId()).orElse(null) == null) {
            userWine.setCreatedAt(LocalDateTime.now());

            Wine wine = wineRepository.findById(userWine.getId().getWineId()).orElse(null);
            User user = userRepository.findById(userWine.getId().getUserId()).orElse(null);
            if (wine != null && user != null) {
                userWine.setWine(wine);
                userWine.setUser(user);
            }

            userWine = userWineRepository.save(userWine);
            LOG.info("SERVICE.UserWine.added {}", userWine);
            return userWineConverter.userWineToUserWineDto(userWine);
        } else {
            return null;
        }
    }

    private User findUserById(Long userId) {
        return userRepository.findById(userId).orElse(null);
    }

    public UserWineDto updateUserWinePublicStatus(UserWineDto userWineRequestDto) {
        UserWine userWine = userWineRepository.findById(userWineRequestDto.getId()).orElse(null);
        if (userWine != null) {
            userWine.setPublic(userWineRequestDto.isPublic());
            userWine = userWineRepository.save(userWine);

            return userWineConverter.userWineToUserWineDto(userWine);
        }
        return null;
    }


    public Set<Set<UserWinePublicDto>> fetchAllPublicWines() {
        List<UserWine> userWines = userWineRepository.findAll();
        LOG.info("SERVICE.FetchAllPublicWines.userWines: {}", userWines);
        Set<Long> userIds = new HashSet<>();
        Set<Set<UserWinePublicDto>> userWinePublicDtosSet = new HashSet<>();
        for (UserWine userWine : userWines) {
            if (userService.getShowWineListStatusByUser(userWine.getUser().getUserId())) {
                userIds.add(userWine.getUser().getUserId());
            }
        }
        for (Long id : userIds) {
            String wineListName = Objects.requireNonNull(userRepository.findById(id).orElse(null)).getWineListName();
            Set<UserWinePublicDto> userWinePublicDtos = userWineRepository.findByUserId(id)
                    .stream()
                    .map(userWine ->
                            userWineConverter.userWineToUserWineDto(userWine))
                    .map(userWineDto -> {
                                UserWinePublicDto userWinePublicDto = userWinePublicConverter.userWineDtoToUserWinePublicDto(userWineDto);
                                userWinePublicDto.setWineListName(wineListName);
                                return userWinePublicDto;
                            }
                    )
                    .collect(Collectors.toSet());
            userWinePublicDtosSet.add(userWinePublicDtos);
        }
        return userWinePublicDtosSet;
    }

    public UserWineDto handleDrinkWine(UserWineDto userWineRequestDto) {
        UserWine userWine = userWineRepository.findById(userWineRequestDto.getId()).orElse(null);
        LOG.info("Input DTO: {}", userWineRequestDto);
        LOG.info("Input converted userWine: {}", userWine);

        if (userWine != null) {
            userWine.setQuantity(userWineRequestDto.getQuantity() <= ZERO ? ZERO : userWineRequestDto.getQuantity());
            userWine.setFinished(Objects.equals(userWine.getQuantity(), ZERO));
            userWine.setThoughts(userWineRequestDto.getThoughts());
            userWine.setRating(userWineRequestDto.getRating());
            userWine.getConsumedAt().add(LocalDateTime.now());
            userWine = userWineRepository.save(userWine);

            LOG.info("Updated userWine: {}", userWine);

            return userWineConverter.userWineToUserWineDto(userWine);
        }

        return null;
    }
}
