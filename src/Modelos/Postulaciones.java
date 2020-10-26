package Modelos;

import Soporte.Archivo;
import Soporte.TSB_OAHashtable;

public class Postulaciones
{
    private Archivo desAgrupaciones;
    private TSB_OAHashtable votacion;

    public Postulaciones(String carpeta)
    {
        desAgrupaciones = new Archivo(carpeta);
        votacion = desAgrupaciones.Agrupaciones();
    }

    public TSB_OAHashtable llenarAgrupaciones()
    {
        TSB_OAHashtable tabla = new TSB_OAHashtable();
        for (Object a : votacion.values())
        {
            Agrupacion agrupacion = (Agrupacion) a;
            tabla.put(agrupacion.getCodigo(), new Agrupacion(agrupacion));
        }
        return tabla;
    }

    @Override
    public String toString()
    {
        final StringBuffer sb = new StringBuffer("Agrupaciones{");
        sb.append("agrupaciones=").append(votacion);
        sb.append('}');
        return sb.toString();
    }

    public TSB_OAHashtable getVotacion()
    {
        return votacion;
    }
}
