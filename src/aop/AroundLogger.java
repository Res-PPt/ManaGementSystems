package aop;

import java.util.Arrays;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
  
@Aspect   
public class AroundLogger {
	private static final Logger log = Logger.getLogger(AroundLogger.class); 
	@Around("execution(* service..*.*(..))")
	public Object aroundLogger(ProceedingJoinPoint jp) throws Throwable{ 
		log.info("璋冪敤"+jp.getTarget()+"鐨�"
				+jp.getSignature().getName()+"鏂规硶銆傛柟娉曞弬鏁�:" 
				+Arrays.toString(jp.getArgs()));
		try{
			Object result = jp.proceed();//鎵ц鐩爣鏂规硶骞惰幏寰楀叾杩斿洖鍊�
			log.info("璋冪敤"+jp.getTarget()+"鐨�" 
					+jp.getSignature().getName()+"鏂规硶銆傛柟娉曡繑鍥炲��:"+result);
			return result;
		}catch (Throwable e) {
			log.error(jp.getSignature().getName()+"鏂规硶鍙戠敓寮傚父"+e);
			throw e;
		}finally{
			log.error(jp.getSignature().getName()+"鏂规硶缁撴潫鎵ц銆�");
		}
	}
}
