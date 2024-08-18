package org.rudradcruze.securityapp.securityapplicationpart2.repositories;

import org.rudradcruze.securityapp.securityapplicationpart2.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
}
