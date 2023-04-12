module carlot {
	
	// JavaFX
	requires javafx.controls;
	requires javafx.graphics;
	
	// MySQL
	requires java.sql;
	requires java.sql.rowset;
	
	// This package uses JavaFX and MySQL
	opens carlot to javafx.graphics, javafx.fxml, javafx.base;
}