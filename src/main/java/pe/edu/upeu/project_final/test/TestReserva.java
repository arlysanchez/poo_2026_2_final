package pe.edu.upeu.project_final.test;

import pe.edu.upeu.project_final.implementacion.ReservaDaoImpl;
import pe.edu.upeu.project_final.interfaces.IReservaDao;
import pe.edu.upeu.project_final.modelo.Cliente;
import pe.edu.upeu.project_final.modelo.Habitacion;
import pe.edu.upeu.project_final.modelo.Reserva;

import java.time.LocalDate;
import java.util.List;

public class TestReserva {
    static IReservaDao Dao = new ReservaDaoImpl();

    public void insert(){
        Reserva r = new Reserva();
        Cliente c = new Cliente();
        Habitacion h = new Habitacion();

        r.setFechaReserva(LocalDate.now());
        r.setFechaIngreso(LocalDate.parse("2026-11-07"));
        r.setFechaSalida(LocalDate.parse("2026-11-10"));
        r.setEstado("Activa");
        r.setTotal(160.00);

        c.setIdcliente(1);
        r.setCliente(c);

        h.setIdhabitacion(1);
        r.setHabitacion(h);

        boolean result =Dao.insert(r);
        if (result){
            System.out.println("Reserva success");
        }else{
            System.out.println("Reserva error");
        }
    }

    public void listar(){
        System.out.println("-----------------------------");
        List<Reserva> list = Dao.listAll();
        if (list !=null && !list.isEmpty()){
            for (Reserva res : list){
                System.out.println("ID_RESERVA:"+res.getIdreserva());
                System.out.println("Fecha_ingreso:"+res.getFechaIngreso());
                System.out.println("fecha_salida:"+res.getFechaSalida());
                System.out.println("NUM_HAB:"+res.getHabitacion().getNumero());
                System.out.println("PRECIO:"+res.getHabitacion().getPrecio());
                System.out.println("CLIENTE:"+res.getCliente().getNombre());
                System.out.println("TOTAL: S/."+res.getTotal());
                System.out.println("ESTADO:"+res.getEstado());
                System.out.println("-----------------------------");
            }
        }else{
            System.out.println("no hay registros");
        }
    }
    public void updateStatus(){
        Reserva r = new Reserva();
        r.setEstado("D");
        r.setIdreserva(1);
        boolean result = Dao.updateStatus(r);
        if (result){
            System.out.println("status update success");
        }else{
            System.out.println("status update error");
        }
    }

    public static void main(String[] args) {
        TestReserva t = new TestReserva();
       // t.insert();
        t.listar();
        // t.updateStatus();
    }
}
