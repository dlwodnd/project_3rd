package com.green.hoteldog.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QWithdrawalUserEntity is a Querydsl query type for WithdrawalUserEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QWithdrawalUserEntity extends EntityPathBase<WithdrawalUserEntity> {

    private static final long serialVersionUID = -1505677280L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QWithdrawalUserEntity withdrawalUserEntity = new QWithdrawalUserEntity("withdrawalUserEntity");

    public final DateTimePath<java.time.LocalDateTime> applyDate = createDateTime("applyDate", java.time.LocalDateTime.class);

    public final DateTimePath<java.time.LocalDateTime> approvalDate = createDateTime("approvalDate", java.time.LocalDateTime.class);

    public final StringPath reason = createString("reason");

    public final QUserEntity userPk;

    public QWithdrawalUserEntity(String variable) {
        this(WithdrawalUserEntity.class, forVariable(variable), INITS);
    }

    public QWithdrawalUserEntity(Path<? extends WithdrawalUserEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QWithdrawalUserEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QWithdrawalUserEntity(PathMetadata metadata, PathInits inits) {
        this(WithdrawalUserEntity.class, metadata, inits);
    }

    public QWithdrawalUserEntity(Class<? extends WithdrawalUserEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.userPk = inits.isInitialized("userPk") ? new QUserEntity(forProperty("userPk")) : null;
    }

}

