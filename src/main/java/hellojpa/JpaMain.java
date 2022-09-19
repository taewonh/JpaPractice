package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/*
* Hello JPA - 애플리케이션 개발
* */
public class JpaMain {

    private EntityManagerFactory emf;
    private EntityManager em;

    public static void main(String[] args) {
        JpaMain jpaMain = new JpaMain();
        jpaMain.updateMember();
    }

    private void init() {
        emf = Persistence.createEntityManagerFactory("test-unit");
        em = emf.createEntityManager();
    }

    private void destroy() {
        em.close();
        em = null;
        emf.close();
        emf = null;
    }

    private EntityTransaction getTransaction() {
        init();
        return em.getTransaction();
    }
    private void updateMember() {
        EntityTransaction transaction = getTransaction();
        transaction.begin();
        try {
            Member findMember = em.find(Member.class, 2L);
            findMember.setName("Lotto");
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        } finally {
            destroy();
        }
    }

    private void insertMember() {
        EntityTransaction transaction = getTransaction();
        transaction.begin();
        try {
            Member member = new Member();
            member.setId(3L);
            member.setName("Taewon");
            em.persist(member);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        } finally {
            destroy();
        }
    }
}
