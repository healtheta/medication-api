package org.healtheta.model.medication;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.healtheta.model.common.CodeableConcept;
import org.healtheta.model.common.Quantity;
import org.healtheta.model.common.Reference;

import javax.persistence.*;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name = "_MedicationContent")
public class MedicationContent {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY,
            cascade =  CascadeType.ALL)
    @JoinColumn(name = "_itemCodeableConcept")
    private CodeableConcept itemCodeableConcept;

    @OneToOne(fetch = FetchType.LAZY,
            cascade =  CascadeType.ALL)
    @JoinColumn(name = "_itemReference")
    private Reference itemReference;

    @OneToOne(fetch = FetchType.LAZY,
            cascade =  CascadeType.ALL)
    @JoinColumn(name = "_amount")
    private Quantity amount;

    public Long getId() {
        return id;
    }

    public CodeableConcept getItemCodeableConcept() {
        return itemCodeableConcept;
    }

    public Reference getItemReference() {
        return itemReference;
    }

    public Quantity getAmount() {
        return amount;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setItemCodeableConcept(CodeableConcept itemCodeableConcept) {
        this.itemCodeableConcept = itemCodeableConcept;
    }

    public void setItemReference(Reference itemReference) {
        this.itemReference = itemReference;
    }

    public void setAmount(Quantity amount) {
        this.amount = amount;
    }
}
