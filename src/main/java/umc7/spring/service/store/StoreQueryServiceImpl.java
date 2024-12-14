package umc7.spring.service.store;


import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc7.spring.domain.Mission;
import umc7.spring.domain.Store;
import umc7.spring.repository.MissionRepository;
import umc7.spring.repository.StoreRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StoreQueryServiceImpl implements StoreQueryService {
    private final StoreRepository storeRepository;
    private final MissionRepository missionRepository;

    @Override
    public List<Store> findStoresByNameAndScore(String name, Float score) {
        List<Store> filterdStores = storeRepository.dynamicQueryWithBooleanBuilder(name, score);
        filterdStores.forEach(store -> System.out.println("Store : "+store));
        return filterdStores;
    }

    @Override
    public boolean storeExist(Long storeId) {
        return storeRepository.existsById(storeId);
    }

    @Override
    public Page<Mission> getMissionList(Long storeId, Integer page) {
        Store store = storeRepository.findById(storeId).get();
        Page<Mission> missionPage = missionRepository.findAllByStore(store, PageRequest.of(page, 10));
        return missionPage;
    }
}
