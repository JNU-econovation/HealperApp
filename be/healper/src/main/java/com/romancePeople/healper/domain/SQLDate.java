package com.romancePeople.healper.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.Embeddable;
import java.time.LocalDateTime;

@Embeddable
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SQLDate {

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private LocalDateTime deletedAt;

    public SQLDate(LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public void updateUpdateAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void updateDeleteAt(LocalDateTime deletedAt) {
        this.deletedAt = deletedAt;
    }

}
