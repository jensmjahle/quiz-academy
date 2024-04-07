package edu.ntnu.mapper;

import edu.ntnu.dto.TagDTO;
import edu.ntnu.dao.TagDAO;
import org.springframework.stereotype.Component;

@Component
public class TagMapper {



    public  TagDTO toDTO(TagDAO tagDAO) {
        return new TagDTO(
            tagDAO.getTagId(),
            tagDAO.getTagName()
        );
    }
    public TagDAO toDAO(TagDTO tagDTO) {
        return new TagDAO(
            tagDTO.getTagId(),
            tagDTO.getTagName()
        );
    }

    public TagDAO toDAOWithoutId(TagDTO tagDTO) {
        TagDAO tagDAO = new TagDAO();
        tagDAO.setTagName(tagDTO.getTagName());
        return tagDAO;
    }


}
