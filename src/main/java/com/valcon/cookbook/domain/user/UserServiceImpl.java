package com.valcon.cookbook.domain.user;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ConversionService conversionService;

    public UserServiceImpl(UserRepository userRepository, ConversionService conversionService) {
        this.userRepository = userRepository;
        this.conversionService = conversionService;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(Long id)  {
        return userRepository.findById(id)
                             .orElseThrow(() -> new EntityNotFoundException(String.format("User with id %s not found", id)));
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username)
                             .orElseThrow(() -> new EntityNotFoundException(String.format("User %s not found", username)));

    }

}
