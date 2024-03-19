package com.singleton.peliculascrud;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;


public class HelloController {
 public HelloController(){}
    @FXML
    private TextField nombreTextField;

    @FXML
    private TextField isbnTextField;

    @FXML
    private TextField stockTextField;

    @FXML
    private TextField anoTextField;

    @FXML
    private TextField companiaTextField;

    @FXML
    private TableView<Pelicula> peliculaTableView;

    @FXML
    private TableColumn<Pelicula, String> nombreColumn;

    @FXML
    private TableColumn<Pelicula, String> isbnColumn;

    @FXML
    private TableColumn<Pelicula, Integer> stockColumn;

    @FXML
    private TableColumn<Pelicula, Integer> anoColumn;

    @FXML
    private TableColumn<Pelicula, String> companiaColumn;

    @FXML
    public Button agregarButton;

    @FXML
    public Button modificarButton;

    @FXML
    public Button eliminarButton;

    private ObservableList<Pelicula> peliculaList = FXCollections.observableArrayList();

    public HelloController(Button agregarButton, Button modificarButton, Button eliminarButton) {
        this.agregarButton = agregarButton;
        this.modificarButton = modificarButton;
        this.eliminarButton = eliminarButton;
    }

    public void initialize() {
        // Configurar las columnas
        nombreColumn.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        isbnColumn.setCellValueFactory(new PropertyValueFactory<>("isbn"));
        stockColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        anoColumn.setCellValueFactory(new PropertyValueFactory<>("ano"));
        companiaColumn.setCellValueFactory(new PropertyValueFactory<>("compania"));

        // Asignar la lista de películas al TableView
        peliculaTableView.setItems(peliculaList);
    }

    @FXML
    public void handleadd(ActionEvent event) {
        String nombre = nombreTextField.getText();
        String isbn = isbnTextField.getText();
        String stock= stockTextField.getText();
        String ano= anoTextField.getText();
        String compania = companiaTextField.getText();

        Pelicula pelicula = new Pelicula(nombre, isbn, stock, ano, compania);
        peliculaList.add(pelicula);

        limpiarCampos();
    }

    @FXML
    public void handlemod(ActionEvent event) {
        Pelicula peliculaSeleccionada = peliculaTableView.getSelectionModel().getSelectedItem();
        if (peliculaSeleccionada != null) {
            // Modificar los datos de la película seleccionada
            peliculaSeleccionada.setNombre(nombreTextField.getText());
            peliculaSeleccionada.setIsbn(isbnTextField.getText());
            peliculaSeleccionada.setStock(stockTextField.getText());
            peliculaSeleccionada.setAno(anoTextField.getText());
            peliculaSeleccionada.setCompania(companiaTextField.getText());

            // Actualizar la TableView
            peliculaTableView.refresh();
            limpiarCampos();
        }
    }

    @FXML
    public void handleelim (ActionEvent event) {
        Pelicula peliculaSeleccionada = peliculaTableView.getSelectionModel().getSelectedItem();
        if (peliculaSeleccionada != null) {
            peliculaList.remove(peliculaSeleccionada);
            limpiarCampos();
        }
    }

    private void limpiarCampos() {
        nombreTextField.clear();
        isbnTextField.clear();
        stockTextField.clear();
        anoTextField.clear();
        companiaTextField.clear();
    }
    @FXML
    public void handleSave(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Guardar archivo");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Archivos de texto", "*.txt"));
        File file = fileChooser.showSaveDialog(new Stage());
        if (file != null) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                for (Pelicula pelicula : peliculaTableView.getItems()) {
                    writer.write(pelicula.toString()); // Suponiendo que Pelicula tiene un método toString adecuado
                    writer.newLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    @FXML
    public void handleOpen(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Abrir archivo");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Archivos de texto", "*.txt"));
        File file = fileChooser.showOpenDialog(new Stage());
        if (file != null) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    // Separar los datos de la línea por un separador, por ejemplo, una coma ","
                    String[] data = line.split(",");

                    // Verificar que hay suficientes datos en la línea para crear una Pelicula
                    if (data.length >= 5) {
                        String nombre = data[0];
                        String isbn = data[1];
                        String stock = data[2];
                        String ano = data[3];
                        String compania = data[4];
                        Pelicula pelicula = new Pelicula(nombre, isbn, stock, ano, compania);
                        peliculaList.add(pelicula);
                    }
                }

                // Actualizar la TableView para mostrar los cambios
                peliculaTableView.refresh();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

