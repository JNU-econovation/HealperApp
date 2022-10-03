package com.romancePeople.healper.domain.record;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.romancePeople.healper.domain.SQLDate;
import com.romancePeople.healper.domain.member.Member;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

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

    @Setter
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

//    @OneToMany(mappedBy = "record", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<RecordExercise> recordExercise;

    public void addRecordExercise(RecordExercise recordExercise) {
//        this.getRecordExercise()
//            .add(recordExercise);
        recordExercise.setRecord(this);
    }

}
