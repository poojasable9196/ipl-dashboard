package io.javabrains.ipldashboard.repository;

import org.springframework.data.repository.CrudRepository;

import io.javabrains.ipldashboard.modal.Team;

public interface TeamRepository extends CrudRepository<Team, Long> {
    Team findByName(String name);
}
