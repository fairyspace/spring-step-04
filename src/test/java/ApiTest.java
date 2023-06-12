import bean.UserDao;
import bean.UserService;
import io.github.fairyspace.beans.PropertyValue;
import io.github.fairyspace.beans.PropertyValues;
import io.github.fairyspace.beans.factory.config.BeanDefinition;
import io.github.fairyspace.beans.factory.config.BeanReference;
import io.github.fairyspace.beans.factory.support.DefaultListenableBeanFactory;
import org.junit.Test;

public class ApiTest {
   @Test
    public void test(){
      // 1.初始化 BeanFactory
      DefaultListenableBeanFactory beanFactory = new DefaultListenableBeanFactory();

      // 2. UserDao 注册
      beanFactory.registerBeanDefinition("userDao", new BeanDefinition(UserDao.class));

      // 3. UserService 设置属性[uId、userDao]
      PropertyValues propertyValues = new PropertyValues();
      propertyValues.addPropertyValue(new PropertyValue("uId", "10001"));
      propertyValues.addPropertyValue(new PropertyValue("userDao",new BeanReference("userDao")));

      // 4. UserService 注入bean
      BeanDefinition beanDefinition = new BeanDefinition(UserService.class, propertyValues);
      beanFactory.registerBeanDefinition("userService", beanDefinition);

      // 5. UserService 获取bean
      UserService userService = (UserService) beanFactory.getBean("userService");
      userService.queryUserInfo();

   }
}
