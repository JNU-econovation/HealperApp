package com.romancePeople.healper.domain.record;

import com.romancePeople.healper.domain.SQLDate;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RecordExercise {

    @Id
    @GeneratedValue
    private Long id;

    private int setNumber;

    private int countPerSet;

    private int successCount;

    private int failCount;

    private boolean isSuccess;

    @Embedded
    private SQLDate sqlDate;

    @ManyToOne
    @JoinColumn(name = "record_id")
    private Record record;

}

