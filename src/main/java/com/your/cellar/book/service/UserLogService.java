package com.your.cellar.book.service;

import com.your.cellar.book.entity.UserLog;
import com.your.cellar.book.repository.UserLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserLogService {

    private UserLogRepository userLogRepository;

    @Autowired
    public UserLogService(UserLogRepository userLogRepository) {
        this.userLogRepository = userLogRepository;
    }

    public void logUserSignIn(String username) {
        UserLog userLog = new UserLog();
        userLog.setUsername(username);
        userLog.setAction("Sign-In");
        userLog.setTimestamp(LocalDateTime.now());

        userLogRepository.save(userLog);
    }

    public void logDataModification(String username, String action) {
        UserLog userLog = new UserLog();
        userLog.setUsername(username);
        userLog.setAction(action);
        userLog.setTimestamp(LocalDateTime.now());

        userLogRepository.save(userLog);
    }

    public void logDataModification(String username, String action, String details) {
        UserLog userLog = new UserLog();
        userLog.setUsername(username);
        userLog.setAction(action);
        userLog.setDetails(details);
        userLog.setTimestamp(LocalDateTime.now());

        userLogRepository.save(userLog);
    }

}
