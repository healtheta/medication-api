package org.healtheta.model.medication;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.Date;
import javax.persistence.*;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name = "_MedicationBatch")
public class MedicationBatch {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "_id")
    private Long id;

    @Column(name = "_lotNumber")
    private String lotNumber;

    @Column(name = "_expirationDate")
    private Date expirationDate;

    public Long getId() {
        return id;
    }

    public String getLotNumber() {
        return lotNumber;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setLotNumber(String lotNumber) {
        this.lotNumber = lotNumber;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }
}
