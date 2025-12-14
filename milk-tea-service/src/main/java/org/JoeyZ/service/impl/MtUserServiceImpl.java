package org.JoeyZ.service.impl;

import lombok.RequiredArgsConstructor;
import org.JoeyZ.entity.MtUser;
import org.JoeyZ.mapper.UserMapper;
import org.JoeyZ.service.MtUserService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MtUserServiceImpl implements MtUserService {

    private final UserMapper userMapper;
    @Override
    public MtUser getUserById(Long id) {
        return userMapper.getUserById(id);
    }
}
