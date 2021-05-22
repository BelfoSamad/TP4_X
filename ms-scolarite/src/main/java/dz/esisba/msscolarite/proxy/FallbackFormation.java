package dz.esisba.msscolarite.proxy;

import dz.esisba.msscolarite.DTO.Formation;
import org.springframework.stereotype.Component;

@Component
public class FallbackFormation implements FormationProxy{
    @Override
    public Formation getFormation(Long idf) {
        return new Formation(idf,"php",0) ;
    }
}
