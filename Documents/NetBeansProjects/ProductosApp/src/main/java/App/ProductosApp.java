package App;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class ProductosApp extends Application {

    @Override
    public void start(Stage stage) {

        TextField txtCodigo = new TextField();
        
        TextField txtNombre = new TextField();
        TextField txtPrecio = new TextField();
        TextField txtDescripcion = new TextField();

        ComboBox<Categoria> comboCategoria = new ComboBox<>();
        comboCategoria.getItems().addAll(
                new Categoria("Tecnología"),
                new Categoria("Ropa"),
                new Categoria("Hogar")
        );

        ListView<Producto> lista = new ListView<>();

        Button btnAgregar = new Button("Agregar");
        btnAgregar.setStyle(
    "-fx-background-color: #3A4A43; " +
    "-fx-text-fill: white; " +
    "-fx-font-weight: bold;"
);
        Button btnLimpiar = new Button("Limpiar");
        btnLimpiar.setStyle(
    "-fx-background-color: #3A4A43; " +
    "-fx-text-fill: white; " +
    "-fx-font-weight: bold;"
);
        Button btnEliminar = new Button("Eliminar");
        btnEliminar.setStyle(
    "-fx-background-color: #3A4A43; " +
    "-fx-text-fill: white; " +
    "-fx-font-weight: bold;"
);

        Label titulo = new Label("Registro de Productos");
        titulo.setTextFill(Color.BLUE);
        titulo.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;-fx-text-fill: #3A4A43;");

        GridPane form = new GridPane();
        form.setPadding(new Insets(10));
        form.setHgap(10);
        form.setVgap(10);

        form.add(new Label("Código:"), 0, 0);
        form.add(txtCodigo, 1, 0);

        form.add(new Label("Nombre:"), 0, 1);
        form.add(txtNombre, 1, 1);

        form.add(new Label("Precio:"), 0, 2);
        form.add(txtPrecio, 1, 2);

        form.add(new Label("Categoría:"), 0, 3);
        form.add(comboCategoria, 1, 3);

        form.add(new Label("Descripción:"), 0, 4);
        form.add(txtDescripcion, 1, 4);

        form.add(btnAgregar, 1, 5);
        form.add(btnLimpiar, 1, 6);
        form.add(btnEliminar, 1, 7);
        form.add(lista, 1, 8);

        btnAgregar.setOnAction(e -> {
    String codigo = txtCodigo.getText();
    String nombre = txtNombre.getText();
    String precioTexto = txtPrecio.getText();
    Categoria categoria = comboCategoria.getValue();
    String descripcion = txtDescripcion.getText();

    if (codigo.length() < 3) {
        mostrarAlerta("El código debe tener al menos 3 caracteres");
        return;
    }

    if (nombre.isEmpty() || precioTexto.isEmpty() || categoria == null) {
        mostrarAlerta("Complete todos los campos obligatorios");
        return;
    }

    for (Producto p : lista.getItems()) {
        if (p.toString().startsWith(codigo + " ")) {
            mostrarAlerta("Ya existe un producto con ese código");
            return;
        }
    }

    double precio;

    try {
        precio = Double.parseDouble(precioTexto);
    } catch (Exception ex) {
        mostrarAlerta("Precio inválido");
        return;
    }

    Producto p = new Producto(codigo, nombre, precio, categoria, descripcion);
    lista.getItems().add(p);
});

        btnLimpiar.setOnAction(e -> {
            txtCodigo.clear();
            txtNombre.clear();
            txtPrecio.clear();
            txtDescripcion.clear();
            comboCategoria.setValue(null);
        });

        btnEliminar.setOnAction(e -> {

            if (!lista.getSelectionModel().isEmpty()) {
                lista.getItems().remove(
                        lista.getSelectionModel().getSelectedIndex()
                );
            }
        });

        VBox root = new VBox(10, titulo, form);
        root.setStyle("-fx-background-color: #06D49E;");
        root.setPadding(new Insets(15));

        Scene scene = new Scene(root, 400, 500);
        stage.setTitle("Productos y Categorías");
        
        stage.setScene(scene);
        stage.show();
    }

    private void mostrarAlerta(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch();
    }
}