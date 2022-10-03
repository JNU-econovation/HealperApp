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

    private Integer successCount;

    private Integer failCount;

    private Boolean isSuccess;

    @Embedded
    private SQLDate sqlDate;

    @ManyToOne
    @JoinColumn(name = "record_id")
    private Record record;

}

