package com.infotech.client;

import java.util.List;

import javax.persistence.Tuple;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;

import com.infotech.entities.Employee;
import com.infotech.entities.Partner;
import com.infotech.entities.Person;
import com.infotech.entities.Phone;
import com.infotech.util.HibernateUtil;

public class CriteriaQuerySelectMultipleRootClientTestUsingTuple {

	public static void main(String[] args) {

		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Tuple> criteriaQuery = builder.createQuery(Tuple.class);
			Root<Person> personRoot = criteriaQuery.from(Person.class);
			Root<Partner> PartnerRoot = criteriaQuery.from(Partner.class);

			criteriaQuery.multiselect(personRoot, PartnerRoot);

			Predicate perSonRestriction = builder.and(
					builder.equal(personRoot.get("address"), "Bank of Canada, 234 Wellington Street"),
					builder.isNotEmpty(personRoot.get("phones")));

			Predicate partnerRestriction = builder.and(builder.like(PartnerRoot.get("name"), "%mur%"),
					builder.equal(PartnerRoot.get("version"), 1));

			CriteriaQuery<Tuple> query = criteriaQuery.where(perSonRestriction, partnerRestriction);

			List<Tuple> tuples = session.createQuery(query).list();

			tuples.forEach(tuple -> {
				Person person = (Person) tuple.get(0);
				if (person != null) {
					System.out.println(person);
					List<Phone> phones = person.getPhones();
					phones.forEach(phone -> {
						System.out.println(phone.getId() + "\t" + phone.getNumber() + "\t" + phone.getType());
					});
				}
				Partner partner = (Partner) tuple.get(1);
				System.out.println(partner);
			});

		} catch (Exception e) {

			e.printStackTrace();
		}
	}

}
