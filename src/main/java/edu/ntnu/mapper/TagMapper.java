package edu.ntnu.mapper;

import edu.ntnu.dto.TagDTO;
import edu.ntnu.dao.TagDAO;
import org.springframework.stereotype.Component;

/**
 * Mapper class for the Tag entity.
 */
@Component
public class TagMapper {

    /**
     * Maps a TagDAO object to a TagDTO object.
     * @param tagDAO The TagDAO object to map.
     * @return The TagDTO object.
     */
    public  TagDTO toTagDTO(TagDAO tagDAO) {
        return new TagDTO(
            tagDAO.getTagId(),
            tagDAO.getTagName()
        );
    }

    /**
     * Maps a TagDTO object to a TagDAO object.
     * @param tagDTO The TagDTO object to map.
     * @return The TagDAO object.
     */
    public TagDAO toDAO(TagDTO tagDTO) {
        return new TagDAO(
            tagDTO.getTagId(),
            tagDTO.getTagName()
        );
    }

    /**
     * Maps a TagDTO object to a TagDAO object without the id.
     * @param tagDTO The TagDTO object to map.
     * @return The TagDAO object.
     */
    public TagDAO toDAOWithoutId(TagDTO tagDTO) {
        TagDAO tagDAO = new TagDAO();
        tagDAO.setTagName(tagDTO.getTagName());
        return tagDAO;
    }


}
