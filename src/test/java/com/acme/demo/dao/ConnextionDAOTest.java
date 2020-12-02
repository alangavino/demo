package com.acme.demo.dao;

import static org.junit.Assert.assertEquals;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.test.util.ReflectionTestUtils;

public class ConnextionDAOTest {
	@InjectMocks
	ConnectionDAO connectionDAO;

	@Mock
	ConnectionDAO conexionDao;

	@Spy
	DriverManager driverManager;

	@Mock
	Connection connection;
	@Mock
	PreparedStatement preparedStatement;

	@Before
	public void initMocks() {
		MockitoAnnotations.initMocks(this);
		ReflectionTestUtils.setField(connectionDAO, "username", "sa");
		ReflectionTestUtils.setField(connectionDAO, "password", "");
		ReflectionTestUtils.setField(connectionDAO, "databaseUrl", "jdbc:h2:~/test");

	}

	@Test
	public void setUsernameTest() throws NoSuchFieldException, IllegalAccessException {
		final ConnectionDAO connection = new ConnectionDAO();
		connection.setUsername("sa");

		final Field field = connection.getClass().getDeclaredField("username");
		field.setAccessible(true);
		assertEquals("Fields equal", field.get(connection), "sa");

	}

	@Test
	public void setPasswordTest() throws NoSuchFieldException, IllegalAccessException {
		final ConnectionDAO connection = new ConnectionDAO();
		connection.setPassword("passwordprueba");
		final Field field = connection.getClass().getDeclaredField("password");
		field.setAccessible(true);
		assertEquals("Fields equal", field.get(connection), "passwordprueba");

	}

	@Test
	public void setConnectionUrlTest() throws NoSuchFieldException, IllegalAccessException {
		final ConnectionDAO connection = new ConnectionDAO();
		connection.setConnectionUrl("jdbc:h2:~/test");
		final Field field = connection.getClass().getDeclaredField("databaseUrl");
		field.setAccessible(true);
		assertEquals("Fields equal", field.get(connection), "jdbc:h2:~/test");

	}

	@Test
	public void whenCreateConnectionThenReturnConnection() throws SQLException {
		Properties connectionProps = new Properties();
		connectionProps.put("user", "sa");
		connectionProps.put("password", "");
		String databaseUrl = "jdbc:h2:~/test";
		DriverManager.registerDriver(new org.h2.Driver());
		DriverManager.getConnection(databaseUrl, connectionProps);
		connectionDAO.createConnection();

	}

	@Test
	public void sabeLogTest() throws SQLException {

		String sqlPrueba = "INSERT INTO Log_Values(tipo_log,message) VALUES(?,?)";
		Mockito.doReturn(preparedStatement).when(connection).prepareStatement(sqlPrueba,
				Statement.RETURN_GENERATED_KEYS);
		preparedStatement.setString(Mockito.anyInt(), Mockito.anyString());
		preparedStatement.setString(Mockito.anyInt(), Mockito.anyString());
		preparedStatement.executeUpdate();
		preparedStatement.close();
		Assert.assertNotNull(connection.prepareStatement(sqlPrueba, Statement.RETURN_GENERATED_KEYS));
		connectionDAO.saveLog("1", "prueba1");

	}

}
