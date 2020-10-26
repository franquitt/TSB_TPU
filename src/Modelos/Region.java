package Modelos;

import Soporte.TSB_OAHashtable;

import java.util.Collection;

public class Region
{
    private String codigo;
    private String nombre;
    private TSB_OAHashtable resultados;
    private TSB_OAHashtable subregiones;


    public Region(String codigo, String nombre)
    {
        this.codigo = codigo;
        this.nombre = nombre;
        subregiones = new TSB_OAHashtable();
    }

    public Region cargarRegion(String codigoSub)
    {
        Region region = (Region) subregiones.get(codigoSub);
        if (region != null)
            return region;
        else {
            subregiones.put(codigoSub, new Region(codigoSub, ""));
            return (Region) subregiones.get(codigoSub);
        }
    }


    public TSB_OAHashtable getResultados()
    {
        return resultados;
    }

    public void setResultados(TSB_OAHashtable resultados) {
        this.resultados = resultados;
    }

    public Collection getSubregiones()
    {
        return subregiones.values();
    }

    public Region getSubregion(String codigo)
    {
        return (Region) subregiones.get(codigo);
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public String toString() {
        return nombre.equals("") ? codigo : codigo+" - " + nombre;
    }

}
