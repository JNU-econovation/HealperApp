package com.romancePeople.healper.service;

import com.romancePeople.healper.domain.Record;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class RecordService {

    private final RecordRepository recordRepository;
    private final MemberRepository memberRepository;

    private static final String NOT_FOUND_MEMBER = "회원이 존재하지 않습니다";

    private static final String NOT_FOUND_RECORD = "기록이 존재하지 않습니다.";
    private static final String NOT_FOUND_RECENT_RECORD = "최근 기록이 존재하지 않는다..";

    @Transactional
    public Record createRecord(int memberId, LocalDate dateTime, Integer rest) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(new IllegalArgumentException(NOT_FOUND_MEMBER);


        Record record = Record.builder()
                .localDate(dateTime)
                .member(member)
                .isRest(rest)
                .build();

        recordRepository.save(record);
        return record;
    }

    @Transactional
    public Record getRecord(Long recordId){
        Record record=getRecordById(recordId);
        return record;
    }

    @Transactional
    public List<Record> getRecentRecords(Long memberId){
        List<Record> recentRecordList=RecordRepository.findByRecent(memberId)
                .orElseThrow(new IllegalArgumentException(NOT_FOUND_RECENT_RECORD));

        return recentRecordList;
    }


    @Transactional
    public List<Record> getMonthRecord(Long memberId, LocalDate date){
        List<Record>monthRecordList=RecordRepository.findByMonth(memberId,date)
                .orElseThrow(new IllegalArgumentException(NOT_FOUND_MEMBER));

        return monthRecordList;
    }

    @Transactional
    public Record updateRecord(Long memberId, Long recordId, Integer rest){
        Record record=getRecordById(recordId);
        Long findMemberByRecordId=record.getMemberId();
        if(findMemberByRecordId==memberId){
            record.modifyRest(rest);
        }
        return record;
    }

    @Transactional
    public Boolean deleteRecord(Long memberId, Long recordId){
        Record record=getRecordById(recordId);

        if(record.getMemberId()==memberId){
            recordRepository.deleteById(recordId);
            return true;
        }
        return false;
    }


    private Record getRecordById(Long recordId){
        Record record=RecordRepository.findByRecordId(recordId)
                .orElseThrow(new IllegalArgumentException(NOT_FOUND_RECORD));

        return record;
    }
}
