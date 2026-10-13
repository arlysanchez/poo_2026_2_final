package pe.edu.upeu.project_final.test;

import pe.edu.upeu.project_final.implementacion.HabitacionDaoImpl;
import pe.edu.upeu.project_final.interfaces.IHabitacionDao;
import pe.edu.upeu.project_final.modelo.Cliente;
import pe.edu.upeu.project_final.modelo.Habitacion;

import java.util.List;

public class TestHabitacion {
    private static IHabitacionDao hDao = new HabitacionDaoImpl();
    public static void listar() {
        List<Habitacion> lista = hDao.listAll();
        if (lista != null && !lista.isEmpty()) {
            for (Habitacion h : lista) {
                System.out.println("ID_HAB:" + h.getIdhabitacion());
                System.out.println("NUMERO:" + h.getNumero());
                System.out.println("PISO:" + h.getNumeroPiso());
                System.out.println("NUM_HAB:" + h.getNumeroHabitacion());
                System.out.println("TIPO:" + h.getTipo());
                System.out.println("PRECIO:" + h.getPrecio());
                System.out.println("ESTADO:" + h.getEstado());
            }
        } else {
            System.out.println("no hay registros");
        }
    }
    public static void insertar(){
        Habitacion h = new Habitacion();
        h.setNumero("100");
        h.setNumeroPiso("1");
        h.setNumeroHabitacion("101");
        h.setTipo("SIMPLE");
        h.setPrecio(120);
        h.setEstado("Disponible");
        boolean result = hDao.insert(h);
        if (result){
            System.out.println("insert success");
        }else{
            System.out.println("insert error");
        }
    }

    public static void searchByNumero(){
        Habitacion h = hDao.searchByNumero("100");
        if (h!=null){
            System.out.println("ID_HAB:" + h.getIdhabitacion());
            System.out.println("NUMERO:" + h.getNumero());
            System.out.println("PISO:" + h.getNumeroPiso());
            System.out.println("NUM_HAB:" + h.getNumeroHabitacion());
            System.out.println("TIPO:" + h.getTipo());
            System.out.println("PRECIO:" + h.getPrecio());
            System.out.println("ESTADO:" + h.getEstado());
        }
    }

    public static void main(String[] args) {
    //insertar();
    //    listar();
        searchByNumero();
    }
}
