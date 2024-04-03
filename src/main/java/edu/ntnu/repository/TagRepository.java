package edu.ntnu.repository;

import edu.ntnu.dao.TagDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for the Tag entity.
 */
@Repository
public interface TagRepository extends JpaRepository<TagDAO, Long> {

  TagDAO findByTagId(Long tagId);

  TagDAO findByTagName(String tagName);


}
