package test;

import datos.UsuarioDaoJDBC;
import dominio.Usuario;
import java.util.List;


public class UsuarioMainTest {
    public static void main(String[] args) {
        UsuarioDaoJDBC usuarioDao = new UsuarioDaoJDBC();
//
//        // Insertar
//        Usuario nuevoUsuario = new Usuario();
//        nuevoUsuario.setUser("NuevoUsuario");
//        nuevoUsuario.setPassword("password123");
//        usuarioDao.insertar(nuevoUsuario);
//        System.out.println("Insertar: " + nuevoUsuario);

        // Listar
        List<Usuario> usuarios = usuarioDao.listar();
        System.out.println("Listar:");
        for (Usuario usuario : usuarios) {
            System.out.println(usuario);
        }

        // Buscar por ID
        Usuario usuarioEncontrado;
        usuarioEncontrado = usuarioDao.encontrarPorId(-1);
        System.out.println("Buscar por ID: " + usuarioEncontrado);
        
//        // Buscar por ID
//        Usuario usuarioEncontrado = new Usuario(1, "", "");
//        usuarioEncontrado = usuarioDao.encontrar(usuarioEncontrado);
//        System.out.println("Buscar por ID: " + usuarioEncontrado);

        
        
//        // Actualizar
//        if (usuarioEncontrado != null) {
//            usuarioEncontrado.setUser("UsuarioActualizado");
//            usuarioEncontrado.setPassword("passwordActualizado");
//            usuarioDao.actualizar(usuarioEncontrado);
//            System.out.println("Actualizar: " + usuarioEncontrado);
//        }
//
//        // Eliminar
//        if (usuarioEncontrado != null) {
//            usuarioDao.eliminar(usuarioEncontrado);
//            System.out.println("Eliminar: " + usuarioEncontrado);
//        }
    }
}
