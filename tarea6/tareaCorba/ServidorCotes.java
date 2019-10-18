
import PagarCotes.*;
import org.omg.CosNaming.*;
import org.omg.CORBA.*;
import org.omg.PortableServer.*;
import org.omg.PortableServer.POA;

class PagarImpl extends pagarPOA {
    private ORB orb;

    public void setORB(ORB orb_val) {
        orb = orb_val;
    }

    public String pagar(String idCliente) {
        return "Pagado";
    }

    public void shutdown() {
        orb.shutdown(false);
    }
}

public class ServidorCotes {
    public static void main(String args[]) {
        try {
            ORB orb = ORB.init(args, null);
            POA rootpoa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
            rootpoa.the_POAManager().activate();

            PagarImpl pagarcuenta = new PagarImpl();
            pagarcuenta.setORB(orb);

            org.omg.CORBA.Object ref = rootpoa.servant_to_reference(pagarcuenta);
            pagar href = pagar.narrow(ref);
            org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
            NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

            String name = "Pagar";
            NameComponent path[] = ncRef.to_name(name);
            ncRef.rebind(path, href);
            System.out.println("Servidor de suma listo y en espera");
            orb.run();
        } catch (Exception e) {
            System.err.println("Error: " + e);
            e.printStackTrace(System.out);
        }

        System.out.println("Adios cerrando servidor");
    }
}
