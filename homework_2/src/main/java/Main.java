import entity.Course;
import entity.LinkedPurchase;
import entity.Purchase;
import entity.Student;
import entity.compositeKeys.SubscriptionKey;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import utils.HibernateUtil;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();

            session.getTransaction().begin();

            List<Purchase> purchase = loadAllData(Purchase.class, session);
            purchase.forEach( p -> {
                Student student = (Student) findData(p.getStudentName(), "name", Student.class, session);
                Long studentId = student.getId();
                Course course = (Course) findData(p.getCourseName(), "name", Course.class, session);
                Long courseId = course.getId();

                if(studentId != null && courseId != null) {
                    LinkedPurchase linkedPurchase = new LinkedPurchase();
                    linkedPurchase.setCourseId(courseId);
                    linkedPurchase.setStudentId(studentId);
                    linkedPurchase.setId(new SubscriptionKey(studentId, courseId));
                    session.persist(linkedPurchase);
                }

            });


            session.getTransaction().commit();
            session.close();
            HibernateUtil.shutdown();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static <T> List<T> loadAllData(Class<T> type, Session session) {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<T> query = builder.createQuery(type);
        Root<T> rootEntry = query.from(type);
        CriteriaQuery<T> all = query.select(rootEntry);

        TypedQuery<T> allQuery = session.createQuery(all);
        return allQuery.getResultList();
    }

    public static <T> Object findData(String data, String field, Class<T> type, Session session) {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<T> query = builder.createQuery(type);
        Root<T> rootEntry = query.from(type);
        query.select(rootEntry).where(builder.equal(rootEntry.<String>get(field), data));
        List<T> ls = session.createQuery(query).getResultList();
        return ls.get(0);
    }
}