package umc7.spring.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import umc7.spring.domain.QStore;
import umc7.spring.domain.Store;


import java.util.List;

@Repository
@RequiredArgsConstructor
public class StoreRepositoryImpl implements StoreRepositoryCustom{
    private final JPAQueryFactory jpaQueryFactory;
    private final QStore store = QStore.store;
    @Override
    public List<Store> dynamicQueryWithBooleanBuilder(String name, Float score) {
        BooleanBuilder builder = new BooleanBuilder();
        if (name != null) {
            builder.and(store.name.eq(name));
        }
        if (store != null) {
            builder.and(store.score.goe(4.0f));
        }
        return jpaQueryFactory
                .selectFrom(store)
                .where(builder)
                .fetch();
    }
}
