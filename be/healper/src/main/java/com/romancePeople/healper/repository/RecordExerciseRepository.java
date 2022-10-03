package com.romancePeople.healper.repository;

import com.romancePeople.healper.domain.record.RecordExercise;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class RecordExerciseRepository {

    private final EntityManager em;

    public List<RecordExercise> save(List<RecordExercise> recordExercises) {
        for (RecordExercise recordExercise : recordExercises) {
            em.persist(recordExercise);
        }
        return recordExercises;
    }

    public List <RecordExercise> update(List<RecordExercise> recordExercises) {
        for (RecordExercise recordExercise : recordExercises) {
            em.merge(recordExercise);
        }
        return recordExercises;
    }

}