package com.janglejay.selection_system_backstage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BaseRepository<T,ID> extends JpaRepository<T,ID> {
    //强制从数据库里把数据拉回来
    T refresh(T t);
}
