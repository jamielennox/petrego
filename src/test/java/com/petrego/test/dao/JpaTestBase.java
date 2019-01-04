package com.petrego.test.dao;

import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@EnableJpaRepositories(basePackages = {"com.petrego"})
@EntityScan(basePackages = {"com.petrego"})
@DataJpaTest
@ActiveProfiles(profiles = {"local"})
public abstract class JpaTestBase {
}
