package org.rest.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.rest.data.vo.PostVO;
import org.rest.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*") // CORS
@RequestMapping(value = "/api/post/v1", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "post", description = "Endpoints for managing posts") // Swagger config
public class PostController {

    @Autowired
    private PostService postService;

    @Operation(summary = "Test Endpoint", description = "This endpoint is for testing purposes.")
    @ApiResponse(responseCode = "200", description = "Successful response")
    @GetMapping("/get")
    public String testEndpoint() {
        return "Hello, this is a test endpoint!";
    }

    @GetMapping
    @Operation(summary = "Find all posts", description = "Retrieves a list of all posts", tags = {"Post"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = PostVO.class))),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
            })
    public List<PostVO> findAll() {
        return postService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostVO> findById(@PathVariable Long id) {
        PostVO postVO = postService.findById(id);
        return ResponseEntity.ok(postVO);
    }
    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, "application/x-yaml"})
    @Operation(summary = "Adds a new post", description = "Adds a new post by passing in a JSON, XML or YML representation of the post!",
            tags = {"Post"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "201",
                            content = @Content(schema = @Schema(implementation = PostVO.class))),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            })
    public PostVO create(@RequestBody PostVO post) {
        return postService.create(post);
    }

    @PutMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, "application/x-yaml"})
    @Operation(summary = "Updates a post", description = "Updates a post by passing in a JSON, XML or YML representation of the post!",
            tags = {"Post"},
            responses = {
                    @ApiResponse(description = "Updated", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = PostVO.class))),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            })
    public PostVO update(@RequestBody PostVO post) {
        return postService.update(post);
    }

    @DeleteMapping(value = "/{id}")
    @Operation(summary = "Deletes a post", description = "Deletes a post by ID", tags = {"Post"},
            responses = {
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            })
    public ResponseEntity<Void> delete(@PathVariable(value = "id") Long id) {
        postService.delete(id);
        return ResponseEntity.noContent().build();
    }
}