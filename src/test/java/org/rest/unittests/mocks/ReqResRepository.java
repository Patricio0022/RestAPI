package org.rest.unittests.mocks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.rest.data.vo.PostVO;
import org.rest.mapper.DozerMapper;
import org.rest.model.Post;
import org.rest.repository.PostRepository;
import org.rest.services.PostService;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
public class ReqResRepository {

    @InjectMocks
    private PostService postService;
    @Mock
    private PostRepository postRepository;
    @Mock
    private DozerMapper dozerMapper;

    private Post entity;
    private PostVO postVO;

    @BeforeEach
    public void setUp() {
        entity = new Post();
        entity.setId(1L);
        entity.setTitle("Post Title");
        entity.setBody("Post Body");

        postVO = new PostVO();
        postVO.setKey(1L);
        postVO.setTitle("Post Title");
        postVO.setBody("Post Body");
    }

    @Test
    public void findById() {
        when(postRepository.findById(1L)).thenReturn(java.util.Optional.of(entity));

        when(dozerMapper.parseObject(entity, PostVO.class)).thenReturn(postVO);

        PostVO result = postService.findById(1L);


        assertNotNull(result);
        verify(postRepository, times(1)).findById(1L);
        verify(dozerMapper, times(1)).parseObject(entity, PostVO.class);
    }
}
