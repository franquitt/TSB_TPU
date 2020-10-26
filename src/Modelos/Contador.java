package Modelos;

public class Contador
{
    private int cant;

    public Contador()
    {
        cant =0;
    }

    public Contador(int cant)
    {
        this.cant = cant;
    }

    public void incrementar(int x)
    {
        cant += x;
    }

    public int getCant()
    {
        return cant;
    }

    public void setCant(int cant)
    {
        this.cant = cant;
    }

    @Override
    public String toString()
    {
        return ""+ cant;
    }
}
