package org.healtheta.web;

import org.healtheta.model.common.Identifier;
import org.healtheta.model.common.OperationOutcome;
import org.healtheta.model.common.Reference;
import org.healtheta.model.common.repos.IdentifierRepo;
import org.healtheta.model.common.repos.ReferenceRepo;
import org.healtheta.model.medication.Medication;
import org.healtheta.model.medication.repo.MedicationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class MedicationController {
    @Autowired
    MedicationRepo medicationRepo;
    @Autowired
    private IdentifierRepo identifierRepo;
    @Autowired
    private ReferenceRepo referenceRepo;

    @RequestMapping(value = "/create", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    @ResponseBody
    public ResponseEntity<?> create(@RequestBody Medication medication){
        Identifier tmpId = medication.getIdentifier();
        if(tmpId.getValue() == null){
            return new ResponseEntity<OperationOutcome>(OperationOutcome.InvalidParameter(),
                    HttpStatus.OK);
        }

        if(identifierRepo.findIdentifierByValue(tmpId.getValue()) != null){
            return new ResponseEntity<OperationOutcome>(OperationOutcome.RecordExists(),
                    HttpStatus.OK);
        }

        try{
            Medication tmp = new Medication();
            tmp = medicationRepo.save(tmp);
            medication.setId(tmp.getId());

            Reference ref = new Reference();
            ref.setIdentifier(medication.getIdentifier());
            ref.setDisplay("medication-reference");
            medication.setReference(ref);
            medication = medicationRepo.save(medication);
            return new ResponseEntity<Medication>(medication, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<OperationOutcome>(OperationOutcome.InternalError(),
                    HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/read/{id}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public ResponseEntity<?> read(@PathVariable String id){
        try{
            Long lId = Long.parseLong(id);
            Medication medication = medicationRepo.findMedicationById(lId);
            if(medication != null){
                return new ResponseEntity<Medication>(medication, HttpStatus.OK);
            }else{
                return new ResponseEntity<OperationOutcome>(OperationOutcome.RecordNotFound(), HttpStatus.NOT_FOUND);
            }
        }catch(Exception e){
            return new ResponseEntity<OperationOutcome>(OperationOutcome.InternalError(),
                    HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    @ResponseBody
    public ResponseEntity<?> update(@RequestBody Medication medication){
        Long id = medication.getId();
        Medication tmp = medicationRepo.findMedicationById(id);
        if ( tmp != null){
            //record exists let;s update.
            //reference and identifiers are not to be updated.
            medication.setIdentifier(tmp.getIdentifier());
            medication.setReference(tmp.getReference());
            medication = medicationRepo.save(medication);
            return new ResponseEntity<Medication>(medication, HttpStatus.OK);
        }else{
            return new ResponseEntity<OperationOutcome>(OperationOutcome.RecordNotFound(), HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE, produces = "application/json")
    @ResponseBody
    public ResponseEntity<?> delete(String id) {
        return null;
    }

    @RequestMapping(value = "/search",
            method = RequestMethod.GET,
            produces = "application/json")
    @ResponseBody
    public ResponseEntity<?> search(@RequestParam(value = "manufacturer", required=false) Long manufacturer) {
        if(manufacturer != null){
            Reference ref = referenceRepo.findReferenceById(manufacturer);
            List<Medication> medicationList = medicationRepo.findMedicationByManufacturer(ref);
            return new ResponseEntity<List>(medicationList, HttpStatus.OK);
        }else{
            return new ResponseEntity<OperationOutcome>(OperationOutcome.OperationNotSupported(),
                    HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/format",
            method = RequestMethod.GET,
            produces = "application/json")
    @ResponseBody
    public ResponseEntity<?> format(){
        Medication medication = new Medication();
        Identifier identifier = new Identifier();
        identifier.setValue("MEDICATION:KDJMDMKD-92929229-DKDKDKDDK-92929292");
        medication.setIdentifier(identifier);
        return new ResponseEntity<Medication>(medication, HttpStatus.OK);
    }
}
