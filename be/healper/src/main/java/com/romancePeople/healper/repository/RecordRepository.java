package com.romancePeople.healper.repository;

import com.romancePeople.healper.domain.record.Record;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class RecordRepository {

    private final EntityManager em;

    public Record save(Record record) {
        em.persist(record);
        return record;
    }

    public Optional<Record> findByRecordId(Long memberId, Long recordId) {
        return em.createQuery("select r from Record r where r.member.id = :memberId and r.id = :recordId", Record.class)
                 .setParameter("memberId", memberId)
                 .setParameter("recordId", recordId)
                 .getResultList()
                 .stream()
                 .findAny();
    }

    public List<Record> findByRecent(Long memberId) {
        LocalDate now = LocalDate.now();
        LocalDate minus = now.minusDays(14);
        return em.createQuery("select r from Record r where r.member.id =:memberId and ( r.date between :minus and :now)", Record.class)
                 .setParameter("memberId", memberId)
                 .setParameter("minus", minus)
                 .setParameter("now", now)
                 .getResultList();
    }

    public List<Record> findByMonth(Long memberId, LocalDate date) {
        LocalDate firstDayOfMonth = LocalDate.of(date.getYear(), date.getMonth(), date.withDayOfMonth(1).getDayOfMonth());
        LocalDate lastDayOfMonth = LocalDate.of(date.getYear(), date.getMonth(), date.lengthOfMonth());
        return em.createQuery("select r from Record r where r.member.id = :memberId and ( r.date between :firstDayOfMonth and :lastDayOfMonth)", Record.class)
                 .setParameter("memberId", memberId)
                 .setParameter("firstDayOfMonth", firstDayOfMonth)
                 .setParameter("lastDayOfMonth", lastDayOfMonth)
                 .getResultList();

    }

    public Record update(Record record) {
        em.merge(record);
        return record;
    }


    public void delete(Long memberId, Long recordId) {
        Optional<Record> recordOptional = em.createQuery("select r from Record r where r.member.id = :memberId and r.id = :recordId", Record.class)
                                            .setParameter("memberId", memberId)
                                            .setParameter("recordId", recordId)
                                            .getResultList()
                                            .stream()
                                            .findAny();
        Record record = recordOptional.get();
        em.remove(record);
    }

}
