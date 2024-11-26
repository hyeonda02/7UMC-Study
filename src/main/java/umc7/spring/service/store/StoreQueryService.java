package umc7.spring.service.store;

import umc7.spring.domain.Store;

import java.util.List;

public interface StoreQueryService {
    List<Store> findStoresByNameAndScore(String name, Float score);
    boolean storeExist(Long storeId);
}
