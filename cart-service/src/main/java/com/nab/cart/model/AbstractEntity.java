package com.nab.cart.model;

import java.io.Serializable;
import java.time.Instant;

import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Data;
/**
 * Base abstract class for entities which will hold definitions for created, last modified, created by,
 * last modified by attributes.
 */
@Data
public abstract class AbstractEntity  implements Serializable{
	private static final long serialVersionUID = 1L;

    @Field(name = "created_by")
    private String createdBy = "Auto Generator";

    @Field(name = "created_date")
    private Instant createdDate = Instant.now();

    @LastModifiedBy
    @Field(name = "last_modified_by")
    private String lastModifiedBy= "Auto Generator";

    @LastModifiedDate
    @Field(name = "last_modified_date")
    private Instant lastModifiedDate = Instant.now();
}
