package service.builder;

import service.builder.sax.BuilderException;

import java.util.HashSet;
import java.util.Set;

public abstract class AbstractEntitiesBuilder<T>  {
    protected Set<T> entities;
    public AbstractEntitiesBuilder() {
        entities = new HashSet<>();
    }
    public AbstractEntitiesBuilder(Set<T> entities) {
        this.entities = entities;
    }
    public Set<T> getEntities() {
        return entities;
    }
    abstract public void buildSetEntities(String fileName) throws BuilderException;
}
