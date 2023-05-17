package com.project.bulbeniback.repos;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.project.bulbeniback.entity.Post;

public interface PostRepository extends JpaRepository<Post, Long> {

	List<Post> findByWorfAndCityAndDistrictAndCategory(int worf, String city, String district, String category);

	Optional<List<Post>> findByWorf(int worf);

    List<Post> findByTitleContaining(String title);

}
