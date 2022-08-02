package com.coforge.training.springjdbc;

import java.util.List;
import java.util.Scanner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class JdbcTemplateDemoApp {

	public static void main(String[] args) {
		
		ApplicationContext ctx=new ClassPathXmlApplicationContext("applicationContext.xml");

        CarsDao dao=(CarsDao)ctx.getBean("cdao");

        int n;
        Scanner s=new Scanner(System.in);
        System.out.println("Press 1.Insert Cars\t 2.Update Cars \t 3.Delete Cars\t 4.View Cars\t"
                + " 5. View Cars byId ");
        n=s.nextInt();

        switch(n)
        {
        case 1:    int status=dao.saveCar(new Cars("Maruti",70000));
                System.out.println(status+" Records Inserted");
               dao.countCars();
                break;

        case 2: int status1=dao.updateCar(new Cars(6,25000)); // update car price
                System.out.println(status1+" Car price Updated");
                break;

        case 3: {
                    Cars c1=new Cars() ;
                    c1.setId(8);
                    int stat=dao.deleteCar(c1);
                    System.out.println(stat+" Car Deleted");
                }

                break;

        case 4: List<Cars> lst=dao.getAllCars();
                for(Cars c:lst)
                {
                    System.out.println(c);
                }
                break;

       case 5: Scanner scan=new Scanner(System.in);
                System.out.println("Enter Car Id to be Searched: ");
                int carId=scan.nextInt();
                Cars c=dao.findCarById(carId);

                System.out.println(c.getId()+" "+c.getName()+" "+c.getPrice());
                break;
                
               

        default: System.out.println("Invalid Selection");
                break;
        }

          ((ClassPathXmlApplicationContext) ctx).close();

	}

}
