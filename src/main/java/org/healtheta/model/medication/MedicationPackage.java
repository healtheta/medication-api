package org.healtheta.model.medication;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.healtheta.model.common.CodeableConcept;
import java.util.List;
import javax.persistence.*;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name = "_MedicationPackage")
public class MedicationPackage {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY,
            cascade =  CascadeType.ALL)
    @JoinColumn(name = "_container")
    private CodeableConcept container;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @Column(name = "_content")
    private List<MedicationContent> content;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @Column(name = "_batch")
    private List<MedicationBatch> batch;

    public Long getId() {
        return id;
    }

    public CodeableConcept getContainer() {
        return container;
    }

    public List<MedicationContent> getContent() {
        return content;
    }

    public List<MedicationBatch> getBacth() {
        return batch;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setContainer(CodeableConcept container) {
        this.container = container;
    }

    public void setContent(List<MedicationContent> content) {
        this.content = content;
    }

    public void setBacth(List<MedicationBatch> batch) {
        this.batch = batch;
    }
}
