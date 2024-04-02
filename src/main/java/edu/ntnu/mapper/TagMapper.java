package edu.ntnu.mapper;

import edu.ntnu.dto.TagDTO;
import edu.ntnu.model.Tag;
import edu.ntnu.repository.TagRepository;
import java.util.logging.Logger;
import org.springframework.stereotype.Component;

@Component
public class TagMapper {



    public  TagDTO toTagDTO(Tag tag) {
        return new TagDTO(
            tag.getTagId(),
            tag.getTagName()
        );
    }
    public Tag toTag(TagDTO tagDTO) {
        return new Tag(
            tagDTO.getTagId(),
            tagDTO.getTagName()
        );
    }

    public Tag toTagWithoutKey(TagDTO tagDTO) {
        Tag tag = new Tag();
        tag.setTagName(tagDTO.getTagName());
        return tag;
    }


}
