package edu.ntnu.repositories;

import edu.ntnu.entities.Tag;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {

  Tag findByTagId(Long tagId);

  List<Tag> getAllTags();

}
