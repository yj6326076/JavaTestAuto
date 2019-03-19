package com.yj.testdemo.repository;

import com.yj.testdemo.entity.CityCode;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author yangjun
 */
public interface CityCodeRepository extends JpaRepository<CityCode, String> {
}
