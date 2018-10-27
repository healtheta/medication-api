package org.healtheta.model.medication.repo;
import java.util.List;
import org.healtheta.model.common.*;
import org.healtheta.model.common.Identifier;
import org.healtheta.model.medication.Medication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicationRepo extends JpaRepository<Medication, Long> {
    public Medication findMedicationById(Long id);
    public Medication findMedicationByIdentifier(Identifier identifier);
    public List<Medication> findMedicationByManufacturer(Reference reference);
}
