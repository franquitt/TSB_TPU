package main;

import Soporte.Grabar;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import java.io.File;
import java.util.Map;
import Modelos.*;
import javafx.stage.Stage;
import javax.swing.*;

public class Controller
{
    public ImageView ivImagen;
    public Button btnCargar;
    public Button btnSalir;
    public Button btnLimpiar;
    public Button btnBuscarArchivo;
    public Label lblDireccion;
    public ComboBox cmbDistritos ;
    public ComboBox cmbSecciones ;
    public ComboBox cmbCircuitos ;
    public ComboBox cmbMesas = new ComboBox();
    public TextArea txtResultados;


    private Resultados res;
    private File file;
    private Pais pais;
    private String carpeta;
    private String codigo;
    private String nombre;

    public void btnBuscarArchivo(javafx.event.ActionEvent actionEvent)
    {
        //btnLimpiar(actionEvent);
        FileChooser fc = new FileChooser();
        fc.setTitle("Buscar Archivo");
        // fc.showOpenDialog devuelve un archivo tipo file
        file = fc.showOpenDialog(null);
        //file.getPath() =*string* devuelve la ruta del archivo // file.getParent() = *string* devuelve la carpeta donde esta el archivo

        if (file != null)
        {   carpeta = file.getParent();
            lblDireccion.setText(file.getParent());
        }
    }

    public void btnCargar(ActionEvent actionEvent)
    {
        try{
            nombre = "Argentina";
            cmbDistritos.getItems().clear();
            cmbSecciones.getItems().clear();
            cmbCircuitos.getItems().clear();
            cmbMesas.getItems().clear();

            if(btnCargar.getText().equals("Limpiar Filtros"))
            {
                showResultadosCombinacion("00");
                btnCargar.setText("Cargar");
            }

            else {
                Postulaciones lista = new Postulaciones(carpeta);
                pais = new Pais(carpeta);
                res = new Resultados(pais, lista, carpeta);

                btnCargar.setText("Limpiar Filtros");
                showResultadosCombinacion("00");
            }
            cmbDistritos.setItems(FXCollections.observableArrayList(pais.getDistritos()));

        }catch (Exception e )
        {
            JOptionPane.showMessageDialog(null, "Error al intentar cargar los resultados\n No se encontraron los arhivos necesarios ","Lectura", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void showResultadosCombinacion(String codRegion)
    {
        res.getResultados(codRegion);
        txtResultados.setText("Resultados de " + nombre + ": \n" + res.getResultados(codRegion));
    }

    public void votosDistrito(ActionEvent event)
    {
        try
        {
            Region distrito = (Region) cmbDistritos.getValue();
            nombre = "Argentina " + distrito.getNombre();

            cmbSecciones.getItems().clear();
            cmbCircuitos.getItems().clear();
            cmbMesas.getItems().clear();

            ObservableList ol = FXCollections.observableArrayList(distrito.getSubregiones());
            cmbSecciones.setItems(ol);
            showResultadosCombinacion(distrito.getCodigo());

        }catch (Exception e)
        {

        }

    }

    public void votosSeccion(ActionEvent event)
    {
        try
        {
            Region distrito = (Region) cmbDistritos.getValue();
            Region seccion = (Region) cmbSecciones.getValue();
            nombre = "Argentina " + distrito.getNombre() + ", " + seccion.getNombre();

            cmbCircuitos.getItems().clear();
            cmbMesas.getItems().clear();

            ObservableList ol = FXCollections.observableArrayList(seccion.getSubregiones());
            cmbCircuitos.setItems(ol);
            showResultadosCombinacion(seccion.getCodigo());

        }catch (Exception e)
        {

        }

    }

    public void votosCircuito(ActionEvent event)
    {
        try {
            Region distrito = (Region) cmbDistritos.getValue();
            Region seccion = (Region) cmbSecciones.getValue();
            Region circuito = (Region) cmbCircuitos.getValue();
            nombre = "Argentina " + distrito.getNombre() + ", " + seccion.getNombre() + ", " + circuito.getNombre();

            cmbMesas.getItems().clear();
            ObservableList ol = FXCollections.observableArrayList(circuito.getSubregiones());
            cmbMesas.setItems(ol);
            showResultadosCombinacion(circuito.getCodigo());

        }catch (Exception e)
        {

        }
    }
    public void votosMesa(ActionEvent event)
    {
        try {
            Region distrito = (Region) cmbDistritos.getValue();
            Region seccion = (Region) cmbSecciones.getValue();
            Region circuito = (Region) cmbCircuitos.getValue();
            Region mesa = (Region) cmbMesas.getValue();
            nombre = "Argentina " + distrito.getNombre() + ", " + seccion.getNombre() + ", " + circuito.getNombre() + ", " + mesa.getCodigo();

            showResultadosCombinacion(mesa.getCodigo());
        }catch (Exception e)
        {

        }
    }

    public void btnLimpiar(javafx.event.ActionEvent action)
    {
        lblDireccion.setText("\"Buscar carpeta de Destino\"");
        file = null;
        pais = null;
        codigo = null;
        nombre = "";
        cmbDistritos.getItems().clear();
        cmbSecciones.getItems().clear();
        cmbCircuitos.getItems().clear();
        cmbMesas.getItems().clear();
        txtResultados.setText("");
    }

    public void Guardar(javafx.event.ActionEvent action)
    {
        try
        {
            Grabar gb = new Grabar(carpeta + "\\" + nombre);
            gb.write(txtResultados.getText());
            JOptionPane.showMessageDialog(null, "Archivo guardado Exitosamente","Exportar", JOptionPane.PLAIN_MESSAGE);
        }catch (Exception e )
        {
            JOptionPane.showMessageDialog(null, "Error al intentar guardar","Exportar", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void btnSalir(ActionEvent a)
    {
        Stage stage = (Stage) btnSalir.getScene().getWindow();
        stage.close();
    }
}
