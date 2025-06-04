package ru.netology.service;

import org.springframework.stereotype.Component;
import ru.netology.model.Post;
import ru.netology.model.PostDTO;

@Component
public class Mapper {
    //    from Post to PostDTO
    public PostDTO mapPostToPostDTO(Post post) {
        return new PostDTO(post.getId(), post.getContent());
    }

    //    from PostDTO to Post
    public Post mapPostDTOToPost(PostDTO postDTO) {
        return (postDTO.isRemoved()) ? null : new Post(postDTO.getId(), postDTO.getContent());
    }
}
