package com.site.webapp.bean;

/**
 *
 * @author Arturs
 */

import com.site.webapp.service.CarService;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name="car")
@SessionScoped
public class Car implements Serializable{

    private Long id;
    private String Name;
    private Integer Price;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public Integer getPrice() {
        return Price;
    }

    public void setPrice(Integer Price) {
        this.Price = Price;
    }
    
    // Method To Fetch The Existing Cars From The Database
    public List<Car> getListOfCars() {
        return CarService.getCars();
    }
    
    public String clearFields() {
        Name = "";
        Price = 0;
        return "newCar.xhtml?faces-redirect=true";
    }
    
    
    // Method To Create new Car in DB
    public String addNewCar(Car car) {
        Car newCar = new Car();
        newCar.setName(car.getName());
        newCar.setPrice(car.getPrice());
        CarService.save(newCar);
        return "carList.xhtml?faces-redirect=true";
    }

    public String editCarDetailsById() {
        Car carToUpdate = CarService.getCarById(Long.valueOf(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("selectedCarId")));
        id = carToUpdate.getId();
        Name = carToUpdate.getName();
        Price = carToUpdate.getPrice();
        return "carEdit.xhtml?faces-redirect=true&includeViewParams=true";
    }

    public String updateCarDetails(Car car) {
        Car carToUpdate = CarService.getCarById(car.getId());
        carToUpdate.setName(car.getName());
        carToUpdate.setPrice(car.getPrice());
        CarService.update(carToUpdate);
        FacesContext.getCurrentInstance().addMessage("editCarForm:carId", new FacesMessage("Car Record #" + car.getId() + " Is Successfully Updated In DB"));  
        return "carList.xhtml";    
    }
    
    public String deleteCarById(Long id){
        Car carToDelete = CarService.getCarById(id);
        CarService.delete(carToDelete);
        return "carList.xhtml?faces-redirect=true";
    }
    
    @Override
    public String toString() {
        return String.format("Car Id: %d Name: %s; Price: %d", 
                id, Name, Price);
    }    
}