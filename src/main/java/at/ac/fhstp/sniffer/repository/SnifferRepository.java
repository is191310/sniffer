package at.ac.fhstp.sniffer.repository;
import org.springframework.data.repository.CrudRepository;

import at.ac.fhstp.sniffer.entity.Sniffer;
public interface SnifferRepository extends CrudRepository <Sniffer, Integer>
{

}
