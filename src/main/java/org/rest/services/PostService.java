package org.rest.services;

import org.rest.controller.PostController;
import org.rest.data.vo.PostVO;
import org.rest.exceptions.RequiredObjectIsNullException;
import org.rest.exceptions.ResourceNotFoundException;
import org.rest.mapper.DozerMapper;
import org.rest.model.Post;
import org.rest.repository.PersonRepository;
import org.rest.repository.PostRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    private static final Logger logger = LoggerFactory.getLogger(PostService.class);

    @Autowired
    private DozerMapper dozerMapper;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private PersonRepository personRepository;

    public List<PostVO> findAll() {
        logger.info("Finding all Posts!");
        List<PostVO> posts = dozerMapper.parseListObjects(postRepository.findAll(), PostVO.class);
        logger.debug("Posts found: {}", posts.size());
        return posts;
    }

    public PostVO findById(Long id) {
        logger.info("Finding Post with ID: {}", id);
        Post entity = postRepository.findById(id)
                .orElseThrow(() -> {
                    logger.error("No records found for ID: {}", id);
                    return new ResourceNotFoundException("No records found for this ID: " + id);
                });
        logger.debug("Post found: {}", entity);
        return dozerMapper.parseObject(entity, PostVO.class);
    }

    public PostVO create(PostVO post) {
        validatePost(post);

        if (!personRepository.existsById(post.getPerson().getKey())) {
            logger.error("No records found for person ID: {}", post.getPerson().getKey());
            throw new ResourceNotFoundException("No records found for this person ID: " + post.getPerson().getKey());
        }

        logger.info("Creating Post with title: {}", post.getTitle());
        Post entity = dozerMapper.parseObject(post, Post.class);
        return dozerMapper.parseObject(postRepository.save(entity), PostVO.class);
    }

    public PostVO update(PostVO post) {
        validatePost(post);

        logger.info("Updating Post with ID: {}", post.getKey());
        Post entity = postRepository.findById(post.getKey())
                .orElseThrow(() -> {
                    logger.error("No records found for ID: {}", post.getKey());
                    return new ResourceNotFoundException("No records found for this ID: " + post.getKey());
                });

        entity.setTitle(post.getTitle());
        entity.setBody(post.getBody());

        return dozerMapper.parseObject(postRepository.save(entity), PostVO.class);
    }

    public void delete(Long id) {
        logger.info("Deleting Post with ID: {}", id);
        Post entity = postRepository.findById(id)
                .orElseThrow(() -> {
                    logger.error("No records found for ID: {}", id);
                    return new ResourceNotFoundException("No records found for this ID: " + id);
                });
        postRepository.delete(entity);
        logger.info("Post with ID: {} deleted successfully", id);
    }

    private void validatePost(PostVO post) {
        if (post == null) {
            logger.error("Required object is null when trying to create or update Post");
            throw new RequiredObjectIsNullException("Post object cannot be null");
        }
    }
}
