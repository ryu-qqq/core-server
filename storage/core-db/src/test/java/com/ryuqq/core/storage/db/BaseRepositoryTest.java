package com.ryuqq.core.storage.db;

import org.junit.jupiter.api.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import jakarta.persistence.EntityManager;

@Tag("unitTest")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
@Import({

})
public class BaseRepositoryTest {

	@Autowired
	private EntityManager em;

	protected EntityManager getEntityManager(){
		return em;
	}


	protected void flushAndClear() {
		flush();
		clear();
	}

	protected void flush(){
		em.flush();
	}

	protected void clear(){
		em.clear();
	}

}
