package ie.cit.adf.aspect;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TracingAspect {

    Log log = LogFactory.getLog(TracingAspect.class);
  
    @Before("execution(* ie.cit.adf.domain.dao..*.*(..))")
    public void traceDAO(JoinPoint jp) {
	String clazz = jp.getTarget().getClass().getName();
	String method = jp.getSignature().getName();
	log.trace("DAO method invoked:" + clazz + "#" + method);
    }

    @Before("execution(* ie.cit.adf.web.DashboardRestController*.*(..))")
    public void traceWeb(JoinPoint jp) {
	String clazz = jp.getTarget().getClass().getName();
	String method = jp.getSignature().getName();
	log.trace("REST Controller method invoked:" + clazz + "#" + method);
    }
}