package com.romancePeople.healper.repository;

import com.romancePeople.healper.domain.record.RecordExercise;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class RecordExerciseRepository {

    private final EntityManager em;

    public RecordExercise save(RecordExercise recordExercise) {
        em.persist(recordExercise);
        return recordExercise;
    }

    public RecordExercise update(RecordExercise recordExercise) {
        em.persist(recordExercise);
        return recordExercise;
    }

}
