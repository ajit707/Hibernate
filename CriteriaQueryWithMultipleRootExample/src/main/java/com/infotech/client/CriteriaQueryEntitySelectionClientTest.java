package com.infotech.client;

import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.infotech.entities.Employee;
import com.infotech.util.HibernateUtil;

public class CriteriaQueryEntitySelectionClientTest {

	public static void main(String[] args) {

		// getAllEmployeeDetails();
		// getAllEmployeeDetailsByEmployeeId();
		// getAllEmployeeName();
		getMultipleAttributOfEmployee();
	}

	private static void getMultipleAttributOfEmployee() {

		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Object[]> criteriaQuery = builder.createQuery(Object[].class);
			Root<Employee> root = criteriaQuery.from(Employee.class);
			
			Path<Object> empName = root.get("employeeName");
			Path<Object> email = root.get("email");
			Path<Object> empDOj = root.get("doj");
			
			//CriteriaQuery<Object[]> query = criteriaQuery.select(builder.array(empName, email, empDOj));
			
			CriteriaQuery<Object[]> query = criteriaQuery.multiselect(empName, email, empDOj);
			
			Query<Object[]> createQuery = session.createQuery(query);
			List<Object[]> empList = createQuery.list();
			for(Object[] obj : empList){
				System.out.println("Employee Name : "+(String) obj[0]);
				System.out.println("Employee emai : "+(String) obj[1]);
				System.out.println("Employee Date of joining : "+(Date) obj[2]);
			}
			
			
		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	private static void getAllEmployeeName() {

		try (Session session = HibernateUtil.getSessionFactory().openSession()) {

			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<String> criteriaQuery = builder.createQuery(String.class);
			Root<Employee> root = criteriaQuery.from(Employee.class);
			criteriaQuery.select(root.get("employeeName"));

			Query<String> createQuery = session.createQuery(criteriaQuery);
			List<String> list = createQuery.getResultList();
			list.forEach(System.out::println);
		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	private static void getAllEmployeeDetailsByEmployeeId() {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {

			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Employee> criteriaQuery = builder.createQuery(Employee.class);
			Root<Employee> root = criteriaQuery.from(Employee.class);
			criteriaQuery.select(root);

			criteriaQuery.where(builder.equal(root.get("employeeId"), 2));

			Query<Employee> query = session.createQuery(criteriaQuery);
			List<Employee> list = query.list();
			list.forEach(System.out::println);

		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	private static void getAllEmployeeDetails() {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {

			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Employee> criteriaQuery = builder.createQuery(Employee.class);
			Root<Employee> root = criteriaQuery.from(Employee.class);
			criteriaQuery.select(root);

			Query<Employee> query = session.createQuery(criteriaQuery);
			List<Employee> list = query.list();
			list.forEach(System.out::println);

		} catch (Exception e) {

			e.printStackTrace();
		}
	}

}
