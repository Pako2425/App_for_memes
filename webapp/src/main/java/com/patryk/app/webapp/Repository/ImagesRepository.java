package com.patryk.app.webapp.Repository;

import com.patryk.app.webapp.Model.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImagesRepository extends JpaRepository<Image, Long> {

}
