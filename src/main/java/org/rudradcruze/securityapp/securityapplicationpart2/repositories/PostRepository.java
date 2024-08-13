package org.rudradcruze.securityapp.securityapplicationpart2.repositories;

import org.rudradcruze.securityapp.securityapplicationpart2.entities.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<PostEntity, Long> {
}
