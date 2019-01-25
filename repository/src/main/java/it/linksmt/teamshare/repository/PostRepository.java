package it.linksmt.teamshare.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.linksmt.teamshare.entities.Post;
@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {
}
