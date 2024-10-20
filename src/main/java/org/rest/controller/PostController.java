package org.rest.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
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
    @CrossOrigin(origins = "*", allowedHeaders = "*") //cors
    @RequestMapping(value = "/api/Post/v1", produces = MediaType.APPLICATION_JSON_VALUE)
    @Tag(name = "Post", description = "Endpoints For Managing Post") //swagger config
    public class PostController {

        @Autowired //dependencies injection
        private PostService service;

        //Https verbs
        @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, "application/x-yaml"})
        //swagger
        @Operation(summary = "Finds A Post", description = "Finds A Post", tags = {"Post"},
                responses = {
                        @ApiResponse(description = "Sucess", responseCode = "200",
                                content = @Content(schema = @Schema(implementation = PostVO.class))
                        ),
                        @ApiResponse(description = "Not content", responseCode = "204", content = @Content),
                        @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                        @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                        @ApiResponse(description = "Not Found", responseCode = "401", content = @Content),
                        @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
                }
        )
        public List<PostVO> findAll() {
            return service.findAll();
        }

        @GetMapping(value = "/{id}",
                produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, "application/x-yaml" })

        @Operation (summary = "Find Post", description = "Find Post", tags = {"Post"},
                responses = {
                        @ApiResponse(description = "Sucess", responseCode = "200",
                                content = {
                                        @Content(
                                                mediaType = "application/json",
                                                array = @ArraySchema(schema = @Schema(implementation = PostVO.class))
                                        )
                                }),
                        @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                        @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                        @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                        @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)

                })
        public PostVO findById(@PathVariable(value = "id") Long id) {
            return service.findById(id);
        }


        @PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE,"application/x-yaml" } ,
                produces = { MediaType.APPLICATION_JSON_VALUE , MediaType.APPLICATION_XML_VALUE,"application/x-yaml"})

        @Operation(summary = "Adds a new Post",
                description = "Adds a new Post by passing in a JSON, XML or YML representation of the Post!",
                tags = {"Post"},
                responses = {
                        @ApiResponse(description = "Success", responseCode = "200",
                                content = @Content(schema = @Schema(implementation = PostVO.class))
                        ),
                        @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                        @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                        @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
                }
        )
        public PostVO create(@RequestBody PostVO Post) {
            return service.create(Post);
        }


        @PutMapping(consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE ,"application/x-yaml"} ,
                produces = { MediaType.APPLICATION_JSON_VALUE , MediaType.APPLICATION_XML_VALUE,"application/x-yaml"})

        @Operation(summary = "Updates a Post",
                description = "Updates a Post by passing in a JSON, XML or YML representation of the Post!",
                tags = {"Post"},
                responses = {
                        @ApiResponse(description = "Updated", responseCode = "200",
                                content = @Content(schema = @Schema(implementation = PostVO.class))
                        ),
                        @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                        @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                        @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                        @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
                }
        )
        public PostVO update(@RequestBody PostVO Post) {
            return service.update(Post);
        }


        @DeleteMapping(value = "/{id}")

        @Operation(summary = "Deletes a Post",
                description = "Deletes a Post by passing in a JSON, XML or YML representation of the Post!",
                tags = {"Post"},
                responses = {
                        @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                        @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                        @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                        @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                        @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
                }
        )
        public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
            service.delete(id);
            return ResponseEntity.noContent().build();
        }


    }

