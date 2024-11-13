package umc7.spring.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import umc7.spring.domain.Mission;
import umc7.spring.domain.QMission;
import umc7.spring.domain.QStore;
import umc7.spring.domain.mappings.QMissionComplete;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MissionRepositoryImpl implements MissionRepositoryCustom{
    private final JPAQueryFactory jpaQueryFactory;
    private final QMission mission = QMission.mission;
    private final QStore store = QStore.store;
    private final QMissionComplete missionComplete = QMissionComplete.missionComplete;
    @Override
    public Page<Mission> findByMemberWithCompleteMission(Long memberId, Boolean status, Pageable pageable) {
        BooleanBuilder builder = new BooleanBuilder();
        if (status != null) {
            builder.and(missionComplete.status.eq(status));
        }
        builder.and(missionComplete.member.id.eq(memberId));

        List<Mission> missions =  jpaQueryFactory.selectFrom(mission)
                .leftJoin(mission.missionCompleteList, missionComplete)
                .where(builder)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        long total = jpaQueryFactory
                .selectFrom(mission)
                .join(mission.missionCompleteList, missionComplete)
                .where(builder)
                .fetchCount();

        return new PageImpl<>(missions, pageable, total);
    }

    @Override
    public Page<Mission> findByMemberWithRegion(Long memberId, Long regionId, Pageable pageable) {
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(store.region.id.eq(regionId));

        builder.and(missionComplete.status.eq(true));
        builder.and(missionComplete.member.id.eq(memberId));
        builder.and(store.missionList.contains(mission));

        List<Mission> missions = jpaQueryFactory
                .selectFrom(mission)
                .join(store.missionList,mission)
                .leftJoin(mission.missionCompleteList,missionComplete)
                .where(builder)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        long total = jpaQueryFactory
                .selectFrom(mission)
                .join(store.missionList, mission)
                .leftJoin(mission.missionCompleteList, missionComplete)
                .where(builder)
                .fetchCount();

        return new PageImpl<>(missions, pageable, total);

    }
}
