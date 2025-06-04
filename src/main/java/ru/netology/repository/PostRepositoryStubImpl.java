package ru.netology.repository;

import org.springframework.stereotype.Repository;
import ru.netology.exception.NotFoundException;
import ru.netology.model.Post;
import ru.netology.model.PostDTO;
import ru.netology.service.Mapper;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class PostRepositoryStubImpl implements PostRepository {
    private final Map<Long, PostDTO> postMap;
    private final AtomicLong postIds;

    public PostRepositoryStubImpl() {
        postMap = new ConcurrentHashMap<>();
        postIds = new AtomicLong(1);
    }

    public List<Post> all() {
        List<Post> posts = new ArrayList<>();
        for (PostDTO postDTO : postMap.values()) {
            if (!postDTO.isRemoved()) {
                Mapper mapper = new Mapper();
                posts.add(mapper.mapPostDTOToPost(postDTO));
            }
        }

        for (PostDTO postDTO : postMap.values()) {
            System.out.println(postDTO);
        }

        return posts;
    }

    public Optional<Post> getById(long id) {
        Mapper mapper = new Mapper();
        return Optional.ofNullable(mapper.mapPostDTOToPost(postMap.get(id)));
    }

    public Post save(Post post) {
        if (post.getId() == 0) {
            while (postMap.containsKey(postIds.longValue())) postIds.getAndIncrement();
            post.setId(postIds.getAndIncrement());
        }

        Mapper mapper = new Mapper();
        postMap.put(post.getId(), mapper.mapPostToPostDTO(post));
        return post;
    }

    public void removeById(long id) {
        if (postMap.containsKey(id) && !postMap.get(id).isRemoved()) {
            postMap.get(id).setRemoved(true);
        } else {
            throw new NotFoundException();
        }
    }
}