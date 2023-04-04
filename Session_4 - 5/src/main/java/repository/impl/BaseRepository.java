package main.java.repository.impl;

import main.java.mapper.Mapper;
import main.java.repository.Repository;


public abstract class BaseRepository<ENTITY, ID> implements Repository<ENTITY, ID> {

    private final Mapper<ENTITY> mapper;

    public BaseRepository(Mapper<ENTITY> mapper) {
        this.mapper = mapper;
    }

    protected Mapper<ENTITY> getMapper() {
        return mapper;
    }

}
