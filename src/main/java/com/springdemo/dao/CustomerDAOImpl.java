package com.springdemo.dao;

import com.springdemo.entity.Customer;
//import jdk.javadoc.internal.doclets.toolkit.util.ClassUseMapper;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Repository
public class CustomerDAOImpl implements CustomerDAO{

    //inject session factory
    @Autowired
    private SessionFactory sessionFactory;

//    @Transactional -- defined it in service
    @Override
    public List<Customer> getCustomers(){

        //get current hibernate session
        Session currentSession = sessionFactory.getCurrentSession();
        //create a query
        Query<Customer> theQuery = currentSession.createQuery("from Customer",Customer.class);
        //executing query & get result list
        List<Customer> customers = theQuery.getResultList();
        //return result
        return customers ;
    }

    public void saveCustomer(Customer theCustomer){
        //get current hibernate session
        Session currentSession = sessionFactory.getCurrentSession();
        //save the customer to hibernate
        currentSession.saveOrUpdate(theCustomer);
    }

    public Customer getCustomer(int theId){

        //get current hibernate session
        Session currentSession = sessionFactory.getCurrentSession();
        //now retrieve data from database using primary key
        Customer theCustomer = currentSession.get(Customer.class,theId);

        return theCustomer;
    }

    public void deleteCustomer(int theId){
        Session currentSession = sessionFactory.getCurrentSession();

       Query theQuery = currentSession.createQuery
               ("delete from Customer where id=:customerId");

       theQuery.setParameter("customerId",theId);

       theQuery.executeUpdate();
    }


}
