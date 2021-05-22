package dz.esisba.msscolarite.proxy;

import dz.esisba.msscolarite.DTO.Formation;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="ms-formation")
@LoadBalancerClient(name = "ms-formation", configuration = LBConfiguration.class)
public interface FormationProxy {

    @GetMapping("/formations/{id}")
    @Bulkhead(name="fall1", fallbackMethod = "fallbackFormation")
    Formation getFormation(@PathVariable("id") Long idf);

    default Formation fallbackFormation(Long idf,Throwable throwable) {
        return new Formation(idf,"php",0) ;
    }
}
