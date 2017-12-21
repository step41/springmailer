package org.step41.projects.springmailer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.step41.projects.springmailer.entities.Message;

@Repository
public interface MessagesRepository extends JpaRepository<Message, Long> {}
