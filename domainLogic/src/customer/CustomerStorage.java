package customer;

import Interfaces.CustomerStorageInterface;
import administration.Customer;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class CustomerStorage implements CustomerStorageInterface, Serializable {

    private static final long serialVersionUID = 11111L;

    private Map<String, Customer> storage; // to store customer name -> Customer Obj
    public CustomerStorage(){
        storage = new HashMap<String, Customer>();
    }




    //todo fix functionality
    public static void persistJBP(String filepath,CustomerStorageInterface customerStorage) throws IOException {
        File customerFile = new File(filepath);
        FileOutputStream customerFos = new FileOutputStream(customerFile);
        XMLEncoder customerEncoder = new XMLEncoder(customerFos);
        customerEncoder.writeObject(customerStorage);
        customerEncoder.close();
        customerFos.close();
    }

    public static CustomerStorageInterface initializeJBP(String filepath) throws IOException {
        File file  = new File(filepath);
        if (!file.exists()) {
            throw new FileNotFoundException("File not found: " + filepath);
        }
        FileInputStream customerFis = new FileInputStream(file);
        XMLDecoder customerDecoder = new XMLDecoder(customerFis);
        CustomerStorage customerStorage = (CustomerStorage) customerDecoder.readObject();
        customerDecoder.close();
        customerFis.close();
        return customerStorage;
    }


    @Override
    public void addCustomer(Customer customer) {
        storage.put(customer.getName(), customer);
    }

    @Override
    public void updateCustomer(Customer customer) {
        storage.put(customer.getName(),customer);
    }

    @Override
    public void deleteCustomer(Customer customer) {
        storage.remove(customer.getName());
    }

    @Override
    public Customer getCustomer(String name) {
        return storage.get(name);
    }

    @Override
    public Collection<Customer> getAllCustomers() {
        return storage.values();
    }

    public Map<String, Customer> getStorage(){
        return storage;
    }

    public void setStorage(Map<String, Customer> storage){
        this.storage = storage;
    }

}
