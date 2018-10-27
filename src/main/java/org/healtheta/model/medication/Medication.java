package org.healtheta.model.medication;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.healtheta.model.common.Attachment;
import org.healtheta.model.common.CodeableConcept;
import org.healtheta.model.common.Identifier;
import org.healtheta.model.common.Reference;
import java.util.List;
import javax.persistence.*;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name = "Medication")
public class Medication {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY,
            cascade =  CascadeType.ALL)
    @JoinColumn(unique=true, name = "_identifier")
    private Identifier identifier;

    @OneToOne(fetch = FetchType.LAZY,
            cascade =  CascadeType.ALL)
    @JoinColumn(name = "_code")
    private CodeableConcept code;

    @Column(name = "_status")
    private Long status;

    @Column(name = "_isBrand")
    private boolean isBrand;

    @Column(name = "_isOverTheCounter")
    private boolean isOverTheCounter;

    @OneToOne(fetch = FetchType.LAZY,
            cascade =  CascadeType.ALL)
    @JoinColumn(name = "_manufacturer")
    private Reference manufacturer;

    @OneToOne(fetch = FetchType.LAZY,
            cascade =  CascadeType.ALL)
    @JoinColumn(name = "_form")
    private CodeableConcept form;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @Column(name = "_ingredient")
    private List<MedicationIngredient> ingredient;

    @OneToOne(fetch = FetchType.LAZY,
            cascade =  CascadeType.ALL)
    @JoinColumn(name = "_package")
    private MedicationPackage _package;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @Column(name = "_image")
    private List<Attachment> image;

    @OneToOne(fetch = FetchType.LAZY,
            cascade =  CascadeType.ALL)
    @JoinColumn(name = "_reference")
    private Reference reference;

    public Long getId() {
        return id;
    }

    public Identifier getIdentifier() {
        return identifier;
    }

    public CodeableConcept getCode() {
        return code;
    }

    public Long getStatus() {
        return status;
    }

    public boolean isBrand() {
        return isBrand;
    }

    public boolean isOverTheCounter() {
        return isOverTheCounter;
    }

    public Reference getManufacturer() {
        return manufacturer;
    }

    public CodeableConcept getForm() {
        return form;
    }

    public List<MedicationIngredient> getIngredient() {
        return ingredient;
    }

    public MedicationPackage get_package() {
        return _package;
    }

    public List<Attachment> getImage() {
        return image;
    }

    public Reference getReference() {
        return reference;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setIdentifier(Identifier identifier) {
        this.identifier = identifier;
    }

    public void setCode(CodeableConcept code) {
        this.code = code;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public void setBrand(boolean brand) {
        isBrand = brand;
    }

    public void setOverTheCounter(boolean overTheCounter) {
        isOverTheCounter = overTheCounter;
    }

    public void setManufacturer(Reference manufacturer) {
        this.manufacturer = manufacturer;
    }

    public void setForm(CodeableConcept form) {
        this.form = form;
    }

    public void setIngredient(List<MedicationIngredient> ingredient) {
        this.ingredient = ingredient;
    }

    public void set_package(MedicationPackage _package) {
        this._package = _package;
    }

    public void setImage(List<Attachment> image) {
        this.image = image;
    }

    public void setReference(Reference reference) {
        this.reference = reference;
    }
}
