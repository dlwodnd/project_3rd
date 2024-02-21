package com.green.hoteldog.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QRefundPolicyEntity is a Querydsl query type for RefundPolicyEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QRefundPolicyEntity extends EntityPathBase<RefundPolicyEntity> {

    private static final long serialVersionUID = -1051959158L;

    public static final QRefundPolicyEntity refundPolicyEntity = new QRefundPolicyEntity("refundPolicyEntity");

    public final NumberPath<Long> daysBeforeCheckIn = createNumber("daysBeforeCheckIn", Long.class);

    public final NumberPath<Long> policyPk = createNumber("policyPk", Long.class);

    public final NumberPath<Long> refundPercentage = createNumber("refundPercentage", Long.class);

    public QRefundPolicyEntity(String variable) {
        super(RefundPolicyEntity.class, forVariable(variable));
    }

    public QRefundPolicyEntity(Path<? extends RefundPolicyEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QRefundPolicyEntity(PathMetadata metadata) {
        super(RefundPolicyEntity.class, metadata);
    }

}

