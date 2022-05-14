package com.example.demo.serveces;

import com.example.demo.model.*;
import com.example.demo.repositries.CustomerRepo;
import com.example.demo.repositries.FinancialRepo;
import com.example.demo.repositries.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class FinancialService {
    private final FinancialRepo financialRepo;
    private final EmployeeService employeeService;
    private final OrderRepo orderRepo;
    private final CustomerRepo customerRepo;

    @Autowired
    public FinancialService(FinancialRepo financialRepo, EmployeeService employeeService, OrderRepo orderRepo, CustomerRepo customerRepo) {
        this.financialRepo = financialRepo;
        this.employeeService = employeeService;
        this.orderRepo = orderRepo;



        this.customerRepo = customerRepo;
    }
    public Integer getCount(Date date){
        LocalDate now=date.toLocalDate();
        LocalDate lastDay = now.with(TemporalAdjusters.lastDayOfMonth());

        LocalDate localDate= LocalDate.of(now.getYear(), now.getMonth(), lastDay.getDayOfMonth());
        List<Customer>customers=customerRepo.findAll();
        Integer count=0;
        for(Customer customer : customers){
            if(customer.getRegistrationDate().after(date) || customer.getRegistrationDate().before(Date.valueOf(localDate))){
                count++;
            }

        }
        return count;
    }

    public List<Order> getOrdersForTheMonth(Date date){
        Boolean x=true;
        LocalDate now=date.toLocalDate();
        LocalDate lastDay = now.with(TemporalAdjusters.lastDayOfMonth());

        LocalDate localDate= LocalDate.of(now.getYear(), now.getMonth(), lastDay.getDayOfMonth());

        List<Order>orders=orderRepo.findAllByActivate(x);

        for(Order order : orders){
            if(order.getOrderDate().before(date) || order.getOrderDate().after(Date.valueOf(localDate))){
                orders.remove(order);
            }

        }
        if(orders.isEmpty()){
            throw new IllegalStateException("there is nno orders in progress");
        }
        return orders;
    }



    public void updateFinancial(){
        LocalDate now=LocalDate.now();

        LocalDate localDate= LocalDate.of(now.getYear(), now.getMonth(), 1);
        Optional<Financial> financial=financialRepo.findByMonth(Date.valueOf(localDate));

        if(!financial.isPresent()){
            createNewFinancial(Date.valueOf(localDate));
        }
        Financial financial1=financial.get();

        //calculate outcome for salary
        Double salaries=0.0;
        List<Employee> employees=employeeService.getEmployees();
        for(Employee employee:employees){
            salaries+= employee.getSalary();
        }
        //calculate materials and product cost
        Double costOfProducts=0.0;
        List<Order> orders=getOrdersForTheMonth(Date.valueOf(localDate));
        for(Order order:orders){
            Set<OrderProducts> products=order.getOrderProducts();
            for(OrderProducts orderProducts:products){
                Product product=orderProducts.getProduct();
                costOfProducts+=((product.getPrice())-(product.getPrice()*product.getPercent()))*orderProducts.getCount();
            }



        }
        financial1.setOutcome(salaries+costOfProducts);
        //outCome
        double inCome=0.0;
        for(Order order:orders) {
            Set<OrderProducts> products = order.getOrderProducts();
            for (OrderProducts orderProducts : products) {
                Product product = orderProducts.getProduct();
                inCome += (product.getPrice()) * orderProducts.getCount();
            }
        }
        financial1.setIncome(inCome);

        //profits
        financial1.setProfitsOfTheMonth(financial1.getIncome()-financial1.getOutcome());


        //profitsPercent
        Double profitPercent=financial1.getProfitsOfTheMonth()/financial1.getOutcome();
        financial1.setProfitPercent(profitPercent);
        //customer count
        financial1.setCustomerCount(getCount(Date.valueOf(localDate)));

        //profits to lastMonth
        LocalDate localDateLast= LocalDate.of(now.getYear(), now.getMonth().minus(1), 1);
        Optional<Financial> lastMonth=financialRepo.findByMonth(Date.valueOf(localDateLast));
        if(lastMonth.isPresent()){
            Double profitsToLastMonth= ((financial1.getProfitsOfTheMonth()-lastMonth.get().getProfitsOfTheMonth())/lastMonth.get().getProfitsOfTheMonth())*100;
            financial1.setProfitsToLastMonth(profitsToLastMonth);

            financial1.setCustomerToLastMonth(financial1.getCustomerCount()-lastMonth.get().getCustomerCount());
        }

        financialRepo.save(financial1);

    }

    private void createNewFinancial(Date date) {
        Financial financial=new Financial();
        financial.setMonth(date);
        financialRepo.save(financial);
        updateFinancial();
    }

    public Financial getFinacial() {
        updateFinancial();
        LocalDate now=LocalDate.now();

        LocalDate localDate= LocalDate.of(now.getYear(), now.getMonth(), 1);
        Optional<Financial> financial=financialRepo.findByMonth(Date.valueOf(localDate));

        if(!financial.isPresent()){
            throw new IllegalStateException("no financial");
        }
       return  financial.get();

    }

    public List<Financial> getLastTenRecords() {
        return financialRepo.getLastTenRecords();
    }
}
