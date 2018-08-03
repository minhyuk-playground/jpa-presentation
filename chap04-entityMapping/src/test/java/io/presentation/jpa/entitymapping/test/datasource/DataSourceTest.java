package io.presentation.jpa.entitymapping.test.datasource;

import io.presentation.jpa.entitymapping.config.DataSourceConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;

import static org.junit.Assert.assertTrue;

/**
 * Created By Minhyuk Yoon on 2018. 7. 31.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DataSourceConfig.class)
public class DataSourceTest {

    @Autowired
    private DataSource dataSource;

    @Test
    public void testDI() {
        assertTrue(dataSource != null);
    }
}
