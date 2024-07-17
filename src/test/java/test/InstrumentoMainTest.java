package test;

import datos.InstrumentoDaoJDBC;
import dominio.Instrumento;
import java.util.List;

public class InstrumentoMainTest {
    public static void main(String[] args) {
        InstrumentoDaoJDBC instrumentoDao = new InstrumentoDaoJDBC();

        // Insertar
        Instrumento nuevoInstrumento = new Instrumento();
        nuevoInstrumento.setNombre("Nuevo Instrumento");
        instrumentoDao.insertar(nuevoInstrumento);
        System.out.println("Insertar: " + nuevoInstrumento);

        // Listar
        List<Instrumento> instrumentos = instrumentoDao.listar();
        System.out.println("Listar:");
        for (Instrumento instrumento : instrumentos) {
            System.out.println(instrumento);
        }

        // Buscar por ID
        Instrumento instrumentoEncontrado = new Instrumento(1, "");
        instrumentoEncontrado = instrumentoDao.encontrar(instrumentoEncontrado);
        System.out.println("Buscar por ID: " + instrumentoEncontrado);

        // Actualizar
        if (instrumentoEncontrado != null) {
            instrumentoEncontrado.setNombre("Instrumento Actualizado");
            instrumentoDao.actualizar(instrumentoEncontrado);
            System.out.println("Actualizar: " + instrumentoEncontrado);
        }

        // Eliminar
        if (instrumentoEncontrado != null) {
            instrumentoDao.eliminar(instrumentoEncontrado);
            System.out.println("Eliminar: " + instrumentoEncontrado);
        }
    }
}
