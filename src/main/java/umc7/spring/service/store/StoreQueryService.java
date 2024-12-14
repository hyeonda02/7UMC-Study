package umc7.spring.service.store;

import org.springframework.data.domain.Page;
import umc7.spring.domain.Mission;
import umc7.spring.domain.Store;

import java.util.List;

public interface StoreQueryService {
    List<Store> findStoresByNameAndScore(String name, Float score);
    boolean storeExist(Long storeId);

    Page<Mission> getMissionList(Long storeId, Integer page);
}
