package service.entity_builder;

import service.entity_builder.sax.BuilderException;

import java.util.HashSet;
import java.util.Set;

public abstract class AbstractEntitiesBuilder<T>  {
    protected Set<T> entities;
    public AbstractEntitiesBuilder() {
        entities = new HashSet<T>();
    }
    public AbstractEntitiesBuilder(Set<T> entities) {
        this.entities = entities;
    }
    public Set<T> getEntities() {
        return entities;
    }
    abstract public void buildSetEntities(String fileName) throws BuilderException;
}
