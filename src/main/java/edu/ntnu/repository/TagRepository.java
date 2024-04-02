package edu.ntnu.repository;

import edu.ntnu.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for the Tag entity.
 */
@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {

  Tag findByTagId(Long tagId);

  Tag findByTagName(String tagName);


}
