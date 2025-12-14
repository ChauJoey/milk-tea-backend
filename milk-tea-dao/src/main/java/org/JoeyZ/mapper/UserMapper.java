package org.JoeyZ.mapper;

import org.JoeyZ.entity.MtUser;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    MtUser getUserById(Long id);
}
