package org.rest.repository;

import org.rest.model.Post;
import org.rest.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

    @Repository
    public interface PostRepository extends JpaRepository<Post, Long> {
    }

