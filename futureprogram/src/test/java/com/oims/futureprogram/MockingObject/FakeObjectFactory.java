package com.oims.futureprogram.MockingObject;

import com.oims.futureprogram.model.Employee;
import com.oims.futureprogram.model.Inventory;
import com.oims.futureprogram.model.Supervisor;

public class FakeObjectFactory {
    public static Employee getFakeEmployee(){
        Employee employee = new Employee();
        employee.setId((long) 1);
        employee.setNama("Samad");
        employee.setHp("081234567890");
        employee.setEmail("samad@gmail.com");

        return employee;
    }

    public static Supervisor getFakeSupervisor(){
        Supervisor supervisor = new Supervisor();
        supervisor.setId((long) 2);
        supervisor.setNama("Ahel");
        supervisor.setHp("082345678901");
        supervisor.setEmail("ahel@gmail.com");

        return supervisor;
    }

    public static Inventory getFakeInventory(){
        Inventory inventory = new Inventory();
        inventory.setId((long)3);
        inventory.setNama("Kertas");
        inventory.setHarga((long)30000);
        inventory.setJumlah((long)1000);
        inventory.setDeskripsi("Kertas A4");

        return inventory;
    }
}