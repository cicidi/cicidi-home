//package com.cicidi.home.domain.repository;
//
//import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
//import org.springframework.stereotype.Component;
//
//import javax.persistence.EntityManager;
//import javax.transaction.Transactional;
//import java.io.Serializable;
//import java.util.Optional;
//
//@Component
//public class BaseRepositoryImpl<T, ID extends Serializable>
//        extends SimpleJpaRepository<T, ID> implements BaseRepository<T, ID> {
//
//    private final EntityManager entityManager;
//
//    public BaseRepositoryImpl(Class<T> domainClass, EntityManager entityManager) {
//        super(domainClass, entityManager);
//        this.entityManager = entityManager;
//    }
//
//
//    @Transactional
//    @Override
//    public Optional<T> deleteById(ID id) {
//        T deleted = entityManager.find(this.getDomainClass(), id);
//        Optional<T> returned = Optional.empty();
//
//        if (deleted != null) {
//            entityManager.remove(deleted);
//            returned = Optional.of(deleted);
//        }
//        return returned;
//    }
//}