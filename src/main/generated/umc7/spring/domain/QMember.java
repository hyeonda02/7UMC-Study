package umc7.spring.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMember is a Querydsl query type for Member
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMember extends EntityPathBase<Member> {

    private static final long serialVersionUID = -694893339L;

    public static final QMember member = new QMember("member1");

    public final umc7.spring.domain.common.QBaseEntity _super = new umc7.spring.domain.common.QBaseEntity(this);

    public final StringPath address = createString("address");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final StringPath email = createString("email");

    public final EnumPath<umc7.spring.domain.enums.Gender> gender = createEnum("gender", umc7.spring.domain.enums.Gender.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final DatePath<java.time.LocalDate> inactiveDate = createDate("inactiveDate", java.time.LocalDate.class);

    public final ListPath<umc7.spring.domain.mappings.MemberPrefer, umc7.spring.domain.mappings.QMemberPrefer> memberPreferList = this.<umc7.spring.domain.mappings.MemberPrefer, umc7.spring.domain.mappings.QMemberPrefer>createList("memberPreferList", umc7.spring.domain.mappings.MemberPrefer.class, umc7.spring.domain.mappings.QMemberPrefer.class, PathInits.DIRECT2);

    public final ListPath<umc7.spring.domain.mappings.MissionComplete, umc7.spring.domain.mappings.QMissionComplete> missionCompleteList = this.<umc7.spring.domain.mappings.MissionComplete, umc7.spring.domain.mappings.QMissionComplete>createList("missionCompleteList", umc7.spring.domain.mappings.MissionComplete.class, umc7.spring.domain.mappings.QMissionComplete.class, PathInits.DIRECT2);

    public final StringPath name = createString("name");

    public final StringPath phone = createString("phone");

    public final ListPath<PointTransaction, QPointTransaction> pointTransactionList = this.<PointTransaction, QPointTransaction>createList("pointTransactionList", PointTransaction.class, QPointTransaction.class, PathInits.DIRECT2);

    public final ListPath<Question, QQuestion> questionList = this.<Question, QQuestion>createList("questionList", Question.class, QQuestion.class, PathInits.DIRECT2);

    public final ListPath<Review, QReview> reviewList = this.<Review, QReview>createList("reviewList", Review.class, QReview.class, PathInits.DIRECT2);

    public final EnumPath<umc7.spring.domain.enums.MemberStatus> status = createEnum("status", umc7.spring.domain.enums.MemberStatus.class);

    public final NumberPath<Integer> totalPoint = createNumber("totalPoint", Integer.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QMember(String variable) {
        super(Member.class, forVariable(variable));
    }

    public QMember(Path<? extends Member> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMember(PathMetadata metadata) {
        super(Member.class, metadata);
    }

}

