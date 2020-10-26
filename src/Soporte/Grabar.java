package Soporte;

import java.io.FileWriter;

public class Grabar
{

    private String arch = "resultados.dsv";

    public Grabar()
    {
    }

    public Grabar(String nom)
    {
        arch = nom + ".txt";
    }

    public void write (String sl) throws NullPointerException
    {
        try
        {
            FileWriter myWriter = new FileWriter(arch);
            myWriter.write(sl);
            myWriter.close();
        }
        catch ( Exception e )
        {
            throw new NullPointerException("No se pudo grabar la lista...");
        }
    }
}
