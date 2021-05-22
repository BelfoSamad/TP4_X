package dz.esisba.msscolarite;

import dz.esisba.msscolarite.dao.EtablissementRepository;
import dz.esisba.msscolarite.dao.EtudiantRepository;
import dz.esisba.msscolarite.entities.Etablissement;
import dz.esisba.msscolarite.entities.Etudiant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

import java.sql.Date;

@SpringBootApplication
@EnableFeignClients
public class MsScolariteApplication implements CommandLineRunner {

    @Autowired
    EtudiantRepository etudiantRepository;
    @Autowired
    EtablissementRepository etablissementRepository;
    @Autowired
    RepositoryRestConfiguration repositoryRestConfiguration;

    public static void main(String[] args) {
        SpringApplication.run(MsScolariteApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        repositoryRestConfiguration.exposeIdsFor(Etudiant.class);

        Etablissement e1 = etablissementRepository.save(new Etablissement(null, "esi-sba", null));
        Etablissement e2 = etablissementRepository.save(new Etablissement(null, "univ-sba", null));

        etudiantRepository.save(new Etudiant(null, "malki", Date.valueOf("1999-01-01"), "4isi", null, e1, 1L, null));
        etudiantRepository.save(new Etudiant(null, "ali", Date.valueOf("1995-08-11"), "4siw", null, e1, 2L, null));
        etudiantRepository.save(new Etudiant(null, "karim", Date.valueOf("1996-11-22"), "MatserSiC", null, e2, 1L, null));
        etudiantRepository.save(new Etudiant(null, "fouad", Date.valueOf("2000-02-15"), "MasterSiR", null, e2, 1L, null));
    }
}
