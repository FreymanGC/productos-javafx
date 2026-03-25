package App;

import javafx.application.Application;
import javafx.collections.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import modelo.*;

public class ProductosApp extends Application {

    private ObservableList<Producto> productos = FXCollections.observableArrayList();

    @Override
    public void start(Stage stage) {

        TextField txtCodigo = new TextField();
        TextField txtNombre = new TextField();
        TextField txtPrecio = new TextField();
        TextField txtCategoria = new TextField();
        TextField txtDescripcion = new TextField();

        Button btnAgregar = new Button("Agregar");
        Button btnLimpiar = new Button("Limpiar");

        ListView<Producto> lista = new ListView<>(productos);

        GridPane form = new GridPane();
        form.setHgap(10);
        form.setVgap(10);

        form.add(new Label("Código:"), 0, 0);
        form.add(txtCodigo, 1, 0);

        form.add(new Label("Nombre:"), 0, 1);
        form.add(txtNombre, 1, 1);

        form.add(new Label("Precio:"), 0, 2);
        form.add(txtPrecio, 1, 2);

        form.add(new Label("Categoría:"), 0, 3);
        form.add(txtCategoria, 1, 3);

        form.add(new Label("Descripción:"), 0, 4);
        form.add(txtDescripcion, 1, 4);

        form.add(btnAgregar, 1, 5);
        form.add(btnLimpiar, 1, 6);
        form.add(lista, 1, 7);

        btnAgregar.setOnAction(e -> {

            String codigo = txtCodigo.getText().trim();
            String nombre = txtNombre.getText().trim();
            String precioTexto = txtPrecio.getText().trim();
            String nombreCat = txtCategoria.getText().trim();
            String desc = txtDescripcion.getText().trim();

            if (codigo.isEmpty() || nombre.isEmpty() || precioTexto.isEmpty() || nombreCat.isEmpty()) {
                alerta("Campos obligatorios");
                return;
            }

            if (codigo.length() < 3) {
                alerta("Código mínimo 3 caracteres");
                return;
            }

            double precio;
            try {
                precio = Double.parseDouble(precioTexto);
                if (precio <= 0) {
                    alerta("Precio inválido");
                    return;
                }
            } catch (Exception ex) {
                alerta("Precio debe ser número");
                return;
            }

            Categoria categoria = new Categoria(nombreCat, desc);
            Producto producto = new Producto(codigo, nombre, precio, categoria);

            productos.add(producto);
        });
        
        btnLimpiar.setOnAction(e -> {
    txtCodigo.clear();
    txtNombre.clear();
    txtPrecio.clear();
    txtCategoria.clear();
    txtDescripcion.clear();
        });

        Scene scene = new Scene(form, 400, 500);
        stage.setScene(scene);
        stage.setTitle("Productos y Categorías");
        stage.show();
    }

    private void alerta(String msg) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(msg);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch();
    }
}