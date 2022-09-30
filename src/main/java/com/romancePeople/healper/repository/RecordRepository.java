package com.romancePeople.healper.repository;

import com.romancePeople.healper.domain.record.Record;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.time.LocalDate;
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

    public Optional<Record> findByRecordId(int memberId, int recordId) {
        return em.createQuery("select r from Record r where r.member.id = :memberId", Record.class)
                 .setParameter("memberId", memberId)
                 .getResultList()
                 .stream()
                 .findAny();
    }

    public List<Record> findByRecent(Long memberId) {
        LocalDate now = LocalDate.now();
        System.out.println("now = " + now);
        System.out.println("now.getClass() = " + now.getClass());
        LocalDate minus = now.minusDays(14);
        System.out.println("minus = " + minus);
        System.out.println("minus.getClass() = " + minus.getClass());
        return em.createQuery("select r from Record r where r.member.id =:memberId and ( r.date between :minus and :now)", Record.class)
                 .setParameter("memberId", memberId)
                 .setParameter("minus", minus)
                 .setParameter("now", now)
                 .getResultList();
    }

    public List<Record> findByMonth(Long memberId, LocalDate date) {
        LocalDate month = LocalDate.of(date.getYear(), date.getMonth(), 31);
        return em.createQuery("select r from Record r where r.member.id = :memberId and r.date < :month", Record.class)
                 .setParameter("month", month)
                 .getResultList();

    }

    public Record update(Record record) {
        em.merge(record);
        return record;
    }

}
