package dz.esisba.msscolarite.controller;

import dz.esisba.msscolarite.DTO.Formation;
import dz.esisba.msscolarite.dao.EtudiantRepository;
import dz.esisba.msscolarite.entities.Etudiant;
import dz.esisba.msscolarite.proxy.FormationProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api")
public class ScolariteController {

    @Autowired
    EtudiantRepository etudiantRepository;
    @Autowired
    FormationProxy formationProxy;

    @GetMapping("/etudiant/{id}")
    public Etudiant getEtudiantWithFormation(@PathVariable("id") Long ide) {
        Etudiant etudiant = etudiantRepository.findById(ide).get();
        Formation formation = formationProxy.getFormation(etudiant.getIdFormation());
        etudiant.setFormation(formation);

        return etudiant;
    }

    @GetMapping("/etudiant")
    public List<Etudiant> getEtudiantsWithFormation() {
        List<Etudiant> etudiants = etudiantRepository.findAll();
        etudiants.forEach((e) -> {
                    e.setFormation(formationProxy.getFormation(e.getIdFormation()));
                }
        );
        return etudiants;
    }


}
