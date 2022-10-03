package com.romancePeople.healper.domain.record;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.romancePeople.healper.domain.SQLDate;
import com.romancePeople.healper.domain.member.Member;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Record {

    @Id
    @GeneratedValue
    private Long id;

    private LocalDate date;

    private boolean isRest;

    @Embedded
    private SQLDate sqlDate;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @JsonIgnore
    @OneToMany(mappedBy = "record", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RecordExercise> recordExerciseList;

    public void addRecordExercises(List<RecordExercise> exercises) {
        for (RecordExercise exercise : exercises) {
            this.recordExerciseList.add(exercise);
        }
    }

}
