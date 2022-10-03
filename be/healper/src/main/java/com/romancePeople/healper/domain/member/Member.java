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

    public Member(SQLDate sqlDate) {
        this.sqlDate = sqlDate;
    }

//    @JsonIgnore
//    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<Record> records;

    public void addRecord(Record record) {
//        this.getRecords()
//            .add(record);
        record.setMember(this);
    }


}
