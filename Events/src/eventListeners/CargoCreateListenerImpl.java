package eventListeners;


import eventSystem.Listener;
import eventSystem.CargoCreateEvent;
import warehouse.WarehouseManagement;


public class CargoCreateListenerImpl implements Listener<CargoCreateEvent> {
    private WarehouseManagement warehouseManagement;

    public CargoCreateListenerImpl(WarehouseManagement warehouseManagement) {
        this.warehouseManagement = warehouseManagement;
    }



    @Override
    public void onCRUDevent(CargoCreateEvent event) throws ClassNotFoundException {

        try {
            this.warehouseManagement.insertCargo(event.getCargoDetails());
        }
        catch (Exception e) {
            System.out.println("someting went wrong in adding cargo");
        }
    }
}
