package entity;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.junit.Test;

//测试学生类
public class TestStudents {
	@Test
	public void testSchemaExport(){
		Configuration config = new Configuration().configure();
		ServiceRegistry service = new ServiceRegistryBuilder().applySettings(config.getProperties()).buildServiceRegistry();
		SessionFactory sessionFactory = config.buildSessionFactory(service);
		Session session = sessionFactory.getCurrentSession();
		SchemaExport export = new SchemaExport(config);
		export.create(true, true);//第一个true是生成表结构，第二个true是表示输出sql语句。
	}
	@Test
	public void testSaveStudents(){
		Configuration config = new Configuration().configure();
		ServiceRegistry service = new ServiceRegistryBuilder().applySettings(config.getProperties()).buildServiceRegistry();
		SessionFactory sessionFactory = config.buildSessionFactory(service);
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		Students s1 = new Students("S0000001","彭宇环","男",new Date(),"深圳");
		Students s2 = new Students("S0000002","彭宇驰","男",new Date(),"深圳");
		Students s3 = new Students("S0000003","潘曦睿","女",new Date(),"福建");
		session.save(s1);
		session.save(s2);
		session.save(s3);
		
		tx.commit();
		sessionFactory.close();
	}
}
