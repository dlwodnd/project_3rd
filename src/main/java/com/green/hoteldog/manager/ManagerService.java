package com.green.hoteldog.manager;

import com.green.hoteldog.common.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ManagerService {
    private final ManagerRepository managerRepository;

    public List <UserEntity> getAllUser(){
        return managerRepository.findAllByOdrerbycreatedAtdesc();
    }
}
