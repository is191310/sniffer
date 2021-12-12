package at.ac.fhstp.sniffer.repository;
import org.springframework.data.repository.CrudRepository;

import at.ac.fhstp.sniffer.entity.Comments;
public interface CommentRepository extends CrudRepository <Comments, Integer> 
{

}
