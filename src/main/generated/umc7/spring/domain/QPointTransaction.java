package umc7.spring.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPointTransaction is a Querydsl query type for PointTransaction
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPointTransaction extends EntityPathBase<PointTransaction> {

    private static final long serialVersionUID = -845929543L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPointTransaction pointTransaction = new QPointTransaction("pointTransaction");

    public final umc7.spring.domain.common.QBaseEntity _super = new umc7.spring.domain.common.QBaseEntity(this);

    public final StringPath content = createString("content");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QMember member;

    public final NumberPath<Integer> point = createNumber("point", Integer.class);

    public final EnumPath<umc7.spring.domain.enums.TransactionType> type = createEnum("type", umc7.spring.domain.enums.TransactionType.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QPointTransaction(String variable) {
        this(PointTransaction.class, forVariable(variable), INITS);
    }

    public QPointTransaction(Path<? extends PointTransaction> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QPointTransaction(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QPointTransaction(PathMetadata metadata, PathInits inits) {
        this(PointTransaction.class, metadata, inits);
    }

    public QPointTransaction(Class<? extends PointTransaction> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new QMember(forProperty("member")) : null;
    }

}

