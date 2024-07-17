package test;

import datos.ResultadoDaoJDBC;
import dominio.Resultado;
import java.util.List;
import java.util.Scanner;

public class ResultadoMainTest {
    public static void main(String[] args) {
        ResultadoDaoJDBC resultadoDao = new ResultadoDaoJDBC();
        Scanner scanner = new Scanner(System.in);

//        // Insertar un resultado SISCO
//        System.out.println("Ingrese el id de la sesion existente para SISCO:");
//        int sesionId = scanner.nextInt();
//        System.out.println("Ingrese el nivel de estres:");
//        String nivelDeEstres = scanner.next();
//        System.out.println("Ingrese la puntuacion de estres:");
//        int estresPuntuacion = scanner.nextInt();
//
//        Resultado nuevoResultadoSisco = new Resultado(0, sesionId, nivelDeEstres, estresPuntuacion);
//        resultadoDao.insertar(nuevoResultadoSisco);
//        System.out.println("Insertar SISCO: " + nuevoResultadoSisco);
//
//        // Insertar un resultado DASS
//        System.out.println("Ingrese el id de la sesion existente para DASS:");
//        sesionId = scanner.nextInt();
//        System.out.println("Ingrese el nivel de estres:");
//        nivelDeEstres = scanner.next();
//        System.out.println("Ingrese la puntuacion de estres:");
//        estresPuntuacion = scanner.nextInt();
//        System.out.println("Ingrese el nivel de ansiedad:");
//        String nivelDeAnsiedad = scanner.next();
//        System.out.println("Ingrese la puntuacion de ansiedad:");
//        int ansiedadPuntuacion = scanner.nextInt();
//        System.out.println("Ingrese el nivel de depresion:");
//        String nivelDeDepresion = scanner.next();
//        System.out.println("Ingrese la puntuacion de depresion:");
//        int depresionPuntuacion = scanner.nextInt();
//
//        Resultado nuevoResultadoDass = new Resultado(0, sesionId, nivelDeEstres, estresPuntuacion, nivelDeAnsiedad, ansiedadPuntuacion, nivelDeDepresion, depresionPuntuacion);
//        resultadoDao.insertar(nuevoResultadoDass);
//        System.out.println("Insertar DASS: " + nuevoResultadoDass);

        // Listar
        List<Resultado> resultados = resultadoDao.listar();
        System.out.println("Listar:");
        for (Resultado resultado : resultados) {
            System.out.println(resultado);
        }

//        // Buscar por ID
//        System.out.println("Ingrese el id del resultado a buscar:");
//        int idResultado = scanner.nextInt();
//        Resultado resultadoEncontrado = new Resultado(idResultado, 0, null, 0);
//        resultadoEncontrado = resultadoDao.encontrar(resultadoEncontrado);
//        System.out.println("Buscar por ID: " + resultadoEncontrado);
//
//        // Buscar por ID de sesion
//        System.out.println("Ingrese el id de la sesion a buscar:");
//        sesionId = scanner.nextInt();
//        List<Resultado> resultadosPorSesion = resultadoDao.encontrarPorSesionId(sesionId);
//        System.out.println("Buscar por ID de sesion:");
//        for (Resultado resultado : resultadosPorSesion) {
//            System.out.println(resultado);
//        }
//
//        // Buscar por nivel de estres
//        System.out.println("Ingrese el nivel de estres a buscar:");
//        nivelDeEstres = scanner.next();
//        List<Resultado> resultadosPorNivelDeEstres = resultadoDao.encontrarPorNivelDeEstres(nivelDeEstres);
//        System.out.println("Buscar por nivel de estres:");
//        for (Resultado resultado : resultadosPorNivelDeEstres) {
//            System.out.println(resultado);
//        }
//
//        // Buscar por puntuacion de estres
//        System.out.println("Ingrese la puntuacion de estres a buscar:");
//        estresPuntuacion = scanner.nextInt();
//        List<Resultado> resultadosPorPuntuacionDeEstres = resultadoDao.encontrarPorPuntuacionDeEstres(estresPuntuacion);
//        System.out.println("Buscar por puntuacion de estres:");
//        for (Resultado resultado : resultadosPorPuntuacionDeEstres) {
//            System.out.println(resultado);
//        }
//
//        // Buscar por nivel de ansiedad
//        System.out.println("Ingrese el nivel de ansiedad a buscar:");
//        String nivelDeAnsiedadBusqueda = scanner.next();
//        List<Resultado> resultadosPorNivelDeAnsiedad = resultadoDao.encontrarPorNivelDeAnsiedad(nivelDeAnsiedadBusqueda);
//        System.out.println("Buscar por nivel de ansiedad:");
//        for (Resultado resultado : resultadosPorNivelDeAnsiedad) {
//            System.out.println(resultado);
//        }
//
//        // Buscar por puntuacion de ansiedad
//        System.out.println("Ingrese la puntuacion de ansiedad a buscar:");
//        int ansiedadPuntuacionBusqueda = scanner.nextInt();
//        List<Resultado> resultadosPorPuntuacionDeAnsiedad = resultadoDao.encontrarPorPuntuacionDeAnsiedad(ansiedadPuntuacionBusqueda);
//        System.out.println("Buscar por puntuacion de ansiedad:");
//        for (Resultado resultado : resultadosPorPuntuacionDeAnsiedad) {
//            System.out.println(resultado);
//        }
//
//        // Buscar por nivel de depresion
//        System.out.println("Ingrese el nivel de depresion a buscar:");
//        String nivelDeDepresionBusqueda = scanner.next();
//        List<Resultado> resultadosPorNivelDeDepresion = resultadoDao.encontrarPorNivelDeDepresion(nivelDeDepresionBusqueda);
//        System.out.println("Buscar por nivel de depresion:");
//        for (Resultado resultado : resultadosPorNivelDeDepresion) {
//            System.out.println(resultado);
//        }
//
//        // Buscar por puntuacion de depresion
//        System.out.println("Ingrese la puntuacion de depresion a buscar:");
//        int depresionPuntuacionBusqueda = scanner.nextInt();
//        List<Resultado> resultadosPorPuntuacionDeDepresion = resultadoDao.encontrarPorPuntuacionDeDepresion(depresionPuntuacionBusqueda);
//        System.out.println("Buscar por puntuacion de depresion:");
//        for (Resultado resultado : resultadosPorPuntuacionDeDepresion) {
//            System.out.println(resultado);
//        }
//
//        // Actualizar
//        if (resultadoEncontrado != null) {
//            resultadoEncontrado.setNivelDeEstres("Moderado");
//            resultadoEncontrado.setEstresPuntuacion(15);
//            resultadoDao.actualizar(resultadoEncontrado);
//            System.out.println("Actualizar: " + resultadoEncontrado);
//        }
//
//        // Eliminar
//        if (resultadoEncontrado != null) {
//            resultadoDao.eliminar(resultadoEncontrado);
//            System.out.println("Eliminar: " + resultadoEncontrado);
//        }

        scanner.close();
    }
}
