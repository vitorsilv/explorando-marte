package com.example.explorando_marte.repositories;

import com.example.explorando_marte.models.Probe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProbeRepository extends JpaRepository<Probe, Integer> {
}
