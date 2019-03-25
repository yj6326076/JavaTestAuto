package com.yj.testdemo.repository;

import com.yj.testdemo.entity.ExecuteResult;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 执行结果repository
 * @author exyangjun003
 */
public interface ExecuteResultRepository extends JpaRepository<ExecuteResult, Long> {
}
