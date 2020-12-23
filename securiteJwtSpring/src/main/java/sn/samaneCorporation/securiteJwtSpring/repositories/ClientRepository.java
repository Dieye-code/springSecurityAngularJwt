package sn.samaneCorporation.securiteJwtSpring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import sn.samaneCorporation.securiteJwtSpring.entities.Client;

public interface ClientRepository extends JpaRepository<Client, Integer> {

}
