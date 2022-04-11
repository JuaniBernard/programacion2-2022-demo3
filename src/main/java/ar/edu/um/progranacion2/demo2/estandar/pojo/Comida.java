package ar.edu.um.progranacion2.demo2.estandar.pojo;

import ar.edu.um.progranacion2.demo2.estandar.excepciones.NoMasComidaException;
import ar.edu.um.progranacion2.demo2.estandar.excepciones.StockNegativoException;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
public class Comida {
    protected String comida;
    protected String descripcion;
    protected Double precio;
    protected int stock;

    public Comida(String comida, String descripcion, Double precio, int stock) throws StockNegativoException {
        if(this.stock < 0) {
            throw new StockNegativoException("No se puede cargar comida sin stock");
        }
        this.comida = comida;
        this.descripcion = descripcion;
        this.precio = precio;
        this.stock = stock;
    }

    public void descontarStock() throws NoMasComidaException {
        if(this.stock == 0) {
            throw new NoMasComidaException("No se puede vender esa comida porque no hay mas stock");
        }
        this.stock--;
    }
}
