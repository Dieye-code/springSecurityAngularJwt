package sn.samaneCorporation.securiteJwtSpring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import sn.samaneCorporation.securiteJwtSpring.entities.Compte;

public interface CompteRepository extends JpaRepository<Compte, Integer> {

}
