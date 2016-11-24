
package Dao;

import Vo.ProductoVo;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;


public class ProductoDao {

public void Guardar(ProductoVo modelo) {
        FileWriter fichero = null;
        PrintWriter pw = null;
        try {
            fichero = new FileWriter("C:\\archivos\\producto.txt", true);
            pw = new PrintWriter(fichero);
            pw.println(modelo.getProducto()+ "," + modelo.getCategoria()+ "," + modelo.getPrecio()+","+modelo.getCantidad());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                // Nuevamente aprovechamos el finally para 
                // asegurarnos que se cierra el fichero.
                if (null != fichero) {
                    fichero.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public List<ProductoVo> Mostrar() {
        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;
        List<ProductoVo> lista = new ArrayList<>();
        try {
            // Apertura del fichero y creacion de BufferedReader para poder
            // hacer una lectura comoda (disponer del metodo readLine()).
            archivo = new File("C:\\archivos\\producto.txt");
            fr = new FileReader(archivo);
            br = new BufferedReader(fr);
            // Lectura del fichero
            String linea;           
            while ((linea = br.readLine()) != null) { //mientras haya linea mostramos
                ProductoVo modelo = new ProductoVo();
                String arreglo[] = linea.split(",");// metodo para partir un caracter !!! metodo CSV
                modelo.setProducto(arreglo[0]);
                modelo.setCategoria(arreglo[1]);
                modelo.setPrecio(Double.parseDouble(arreglo[2]));
                modelo.setCantidad(Integer.parseInt(arreglo[3]));
                lista.add(modelo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // En el finally cerramos el fichero, para asegurarnos
            // que se cierra tanto si todo va bien como si salta 
            // una excepcion.
            try {
                if (null != fr) {
                    fr.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return lista;
    }

    
}
