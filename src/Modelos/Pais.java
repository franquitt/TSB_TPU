package Modelos;

import Soporte.TSB_OAHashtable;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collection;
import java.util.Scanner;

public class Pais
{
    private TSB_OAHashtable region;

    public Pais(String carpeta)
    {
        region = new TSB_OAHashtable();
        region.put("00",new Region("00","Argentina"));
        calcRegiones(carpeta);
    }

    public void calcRegiones(String path)
    {
        String linea[], codigo, nombre, distrito, seccion;
        Scanner scanner;
        Region reg,dis, secc, circ;
        try {
            scanner = new Scanner(new File(path + "\\descripcion_regiones.dsv"));
            while (scanner.hasNextLine()) {
                linea = scanner.nextLine().split("\\|");
                codigo = linea[0];
                nombre = linea[1];
                switch(codigo.length())
                {
                    case 2:
                        reg = (Region) region.get("00");
                        dis = reg.cargarRegion(codigo);
                        dis.setNombre(nombre);
                        break;
                    case 5:
                        distrito = codigo.substring(0,2);
                        dis = ((Region) region.get("00")).cargarRegion(distrito);
                        secc = dis.cargarRegion(codigo);
                        secc.setNombre(nombre);
                        break;
                    case 11:
                        distrito = codigo.substring(0,2);
                        dis = ((Region) region.get("00")).cargarRegion(distrito);
                        seccion = codigo.substring(0,5);
                        secc = dis.cargarRegion(seccion);
                        circ = secc.cargarRegion(codigo);
                        circ.setNombre(nombre);
                        break;
                    default:
                        break;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Archivo no encontrado " + e);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public Collection getDistritos()
    {
        Region region = (Region) this.region.get("00");
        return region.getSubregiones();
    }
}

