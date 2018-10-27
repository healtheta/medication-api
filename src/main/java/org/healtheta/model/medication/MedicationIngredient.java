package org.healtheta.model.medication;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.healtheta.model.common.CodeableConcept;
import org.healtheta.model.common.Ratio;

import javax.persistence.*;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name = "_MedicationIngredient")
public class MedicationIngredient {

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
    private CodeableConcept itemReference;

    @Column(name = "_isActive")
    private boolean isActive;

    @OneToOne(fetch = FetchType.LAZY,
            cascade =  CascadeType.ALL)
    @JoinColumn(name = "_amount")
    private Ratio amount;

    public Long getId() {
        return id;
    }

    public CodeableConcept getItemCodeableConcept() {
        return itemCodeableConcept;
    }

    public CodeableConcept getItemReference() {
        return itemReference;
    }

    public boolean isActive() {
        return isActive;
    }

    public Ratio getAmount() {
        return amount;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setItemCodeableConcept(CodeableConcept itemCodeableConcept) {
        this.itemCodeableConcept = itemCodeableConcept;
    }

    public void setItemReference(CodeableConcept itemReference) {
        this.itemReference = itemReference;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public void setAmount(Ratio amount) {
        this.amount = amount;
    }
}
