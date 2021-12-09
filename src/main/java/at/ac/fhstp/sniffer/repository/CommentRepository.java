package at.ac.fhstp.sniffer.repository;
import org.springframework.data.repository.CrudRepository;

import at.ac.fhstp.sniffer.Entity.Comments;
public interface CommentRepository extends CrudRepository <Comments, Integer> {
}
