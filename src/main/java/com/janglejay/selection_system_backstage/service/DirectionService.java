package com.janglejay.selection_system_backstage.service;

import com.janglejay.selection_system_backstage.entity.Direction;
import com.janglejay.selection_system_backstage.repository.DirectionRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Data
@Transactional
public class DirectionService {
    @Autowired
    private DirectionRepository directionRepository;
    //添加毕业设计方向
    public void addDirection(Direction direction){
        directionRepository.save(direction);
    }

}
