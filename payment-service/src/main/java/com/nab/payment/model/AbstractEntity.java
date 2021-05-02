package com.nab.payment.model;

import java.io.Serializable;
import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
/**
 * Base abstract class for entities which will hold definitions for created, last modified, created by,
 * last modified by attributes.
 */
@Data
@MappedSuperclass
@EntityListeners(AbstractEntity.class)
public abstract class AbstractEntity  implements Serializable{
	private static final long serialVersionUID = 1L;

    @Column(name = "created_by", nullable = false, length = 30, updatable = false)
    @JsonProperty("createdBy")
    private String createdBy = "Auto Generator";

    @Column(name = "created_date", updatable = false)
    @JsonProperty("createdDate")
    private Instant createdDate = Instant.now();

    @LastModifiedBy
    @Column(name = "last_modified_by", length = 30)
    @JsonProperty("lastModifiedBy")
    private String lastModifiedBy= "Auto Generator";

    @LastModifiedDate
    @Column(name = "last_modified_date")
    @JsonProperty("lastModifiedDate")
    private Instant lastModifiedDate = Instant.now();
}
