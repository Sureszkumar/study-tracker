package com.sample.domain;


import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public abstract class BaseDomain {

    @Column(name = "CREATION_DATE_TIME", nullable = false)
    @NotNull
    private LocalDateTime creationDateTime;

    @Column(name = "LAST_CHANGE_TIMESTAMP", nullable = false)
    @NotNull
    private LocalDateTime lastChangeTimestamp;

    public abstract Long getId();

    protected abstract void setId(Long id);

    public LocalDateTime getCreationDateTime() {
        return creationDateTime;
    }

    public void setCreationDateTime(LocalDateTime creationDateTime) {
        this.creationDateTime = creationDateTime;
    }

    public LocalDateTime getLastChangeTimestamp() {
        return lastChangeTimestamp;
    }

    public void setLastChangeTimestamp(LocalDateTime lastChangeTimestamp) {
        this.lastChangeTimestamp = lastChangeTimestamp;
    }
}
