package com.romancePeople.healper.domain.member;

import com.romancePeople.healper.domain.SQLDate;
import com.romancePeople.healper.domain.record.Record;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Member {

    @Id
    @GeneratedValue
    private Long id;

    @Embedded
    private SQLDate sqlDate;

}
