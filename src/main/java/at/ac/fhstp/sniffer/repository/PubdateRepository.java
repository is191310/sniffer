package at.ac.fhstp.sniffer.repository;

import org.springframework.data.repository.CrudRepository;

import at.ac.fhstp.sniffer.entity.Pubdate;
public interface PubdateRepository extends CrudRepository <Pubdate, Integer> 
{
    
}
