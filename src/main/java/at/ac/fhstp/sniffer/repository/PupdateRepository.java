package at.ac.fhstp.sniffer.repository;
import org.springframework.data.repository.CrudRepository;

import at.ac.fhstp.sniffer.entity.Pupdate;
public interface PupdateRepository extends CrudRepository <Pupdate, Integer> 
{
    
}
