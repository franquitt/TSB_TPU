package Soporte;

import Modelos.Agrupacion;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Archivo
{
    private String path;
    private TSB_OAHashtable agrupaciones;
    public Archivo(String path)
    {
        this.path = path;
    }

    @Override
    public String toString()
    {
        return "TextFile{" +
                "path='" + path + '\'' +
                '}';
    }

    public TSB_OAHashtable Agrupaciones()
    {
        String[] linea;
        String categoria, codigo, nombre;
        agrupaciones = new TSB_OAHashtable<>();
        Scanner desPostulaciones = null;
        try {
            desPostulaciones = new Scanner(new File(path + "\\descripcion_postulaciones.dsv"));

            while (desPostulaciones.hasNextLine())
            {
                linea = desPostulaciones.nextLine().split("\\|");
                categoria = linea[0];
                //esta es la categoria de presidente y vice
                if(categoria.equals("000100000000000"))
                {
                    codigo = linea[2];
                    nombre = linea[3];
                    agrupaciones.put(codigo, new Agrupacion(codigo, nombre, 0));
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Archivo no encontrado..." + e.getMessage());
        } catch (Exception e) {
            System.out.println(e);
        }
        finally {
            assert desPostulaciones != null;
            desPostulaciones.close();
        }

        return agrupaciones;
    }
}
