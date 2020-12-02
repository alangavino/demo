package com.acme.demo.configuration;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import com.acme.demo.bo.DemoBO;
import com.acme.demo.dao.ConnectionDAO;

@SpringBootApplication
@ComponentScan(basePackages = "com.acme.demo")
public class DemoApplication {

	public static void main(String[] args) {

		SpringApplication.run(DemoApplication.class, args);
	}

	@PostConstruct
	public void inicilizacionObject() {
		ConnectionDAO connectionDAO = new ConnectionDAO();
		try {

			Map<String, String> dbParams = new HashMap<>();
			dbParams.put("logFileFolder", "src/main/resources");
			inicializarBD(connectionDAO.createConnection());
			DemoBO initialDemo = new DemoBO(true, true, true, true, true, true, dbParams);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void inicializarBD(Connection conn) throws SQLException {
		Statement statement = conn.createStatement();
		StringBuffer query = new StringBuffer();
		query.append("DROP TABLE IF EXISTS Log_Values; \n").append("CREATE TABLE Log_Values( ")
				.append("id IDENTITY NOT NULL PRIMARY KEY, tipo_log VARCHAR(1),message VARCHAR(500));");
		statement.executeUpdate(query.toString());
		statement.close();
		conn.close();
	}
}
