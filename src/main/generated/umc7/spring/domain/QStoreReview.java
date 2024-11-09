package umc7.spring.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QStoreReview is a Querydsl query type for StoreReview
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QStoreReview extends EntityPathBase<StoreReview> {

    private static final long serialVersionUID = 1711041582L;

    public static final QStoreReview storeReview = new QStoreReview("storeReview");

    public final umc7.spring.domain.common.QBaseEntity _super = new umc7.spring.domain.common.QBaseEntity(this);

    public final StringPath content = createString("content");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QStoreReview(String variable) {
        super(StoreReview.class, forVariable(variable));
    }

    public QStoreReview(Path<? extends StoreReview> path) {
        super(path.getType(), path.getMetadata());
    }

    public QStoreReview(PathMetadata metadata) {
        super(StoreReview.class, metadata);
    }

}

