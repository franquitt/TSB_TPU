package Modelos;

import Soporte.TSB_OAHashtable;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.Scanner;

public class Resultados {

    private Postulaciones agrupaciones;
    private TSB_OAHashtable resultados;
    private TSB_OAHashtable mesas;
    private Pais pais;

    public Resultados(Pais pais, Postulaciones agrupaciones, String carpeta) {
        this.agrupaciones = agrupaciones;
        this.pais = pais;
        resultados = new TSB_OAHashtable();
        resultados.put("00", agrupaciones.llenarAgrupaciones());
        sumarPorAgrupacion(carpeta);
    }

    public void sumarPorAgrupacion(String path) {
        mesas = new TSB_OAHashtable();
        TSB_OAHashtable res;
        String linea[], categoria, codAgrupacion;
        int votos;
        Scanner scanner;
        try {
            scanner = new Scanner(new File(path + "\\mesas_totales_agrp_politica.dsv"));
            Region circuito = null;
            while (scanner.hasNextLine()) {
                linea = scanner.nextLine().split("\\|");
                categoria = linea[4];
                if (categoria.compareTo("000100000000000") == 0) {

                    codAgrupacion = linea[5];
                    if (circuito == null || !circuito.getNombre().equals(linea[2])) {
                        Iterator<Region> iterador = (pais.getDistritos()).iterator();
                        while (iterador.hasNext()) {
                            Region distrito = iterador.next();
                            if (distrito.getCodigo().equals(linea[0])) {
                                circuito = distrito.getSubregion(linea[1]).getSubregion(linea[2]);
                                break;
                            }
                        }
                    }

                    votos = Integer.parseInt(linea[6]);
                    res = (TSB_OAHashtable) resultados.get("00");

                    ((Agrupacion) res.get(codAgrupacion)).sumar(votos);
                    ((Agrupacion) cargarResultados(linea[0]).get(codAgrupacion)).sumar(votos);
                    ((Agrupacion) cargarResultados(linea[1]).get(codAgrupacion)).sumar(votos);
                    ((Agrupacion) cargarResultados(linea[2]).get(codAgrupacion)).sumar(votos);
                    ((Agrupacion) cargarResultados(linea[3]).get(codAgrupacion)).sumar(votos);

                    circuito.cargarRegion(linea[3]);


                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Archivo no encontrado " + e);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public String getResultados(String codRegion) {
        return ((TSB_OAHashtable) resultados.get(codRegion)).toString();
    }

    public TSB_OAHashtable cargarResultados(String codRegion) {
        TSB_OAHashtable table = (TSB_OAHashtable) resultados.get(codRegion);
        if (table != null)
            return table;
        else {
            resultados.put(codRegion, agrupaciones.llenarAgrupaciones());
            return (TSB_OAHashtable) resultados.get(codRegion);
        }
    }

}
