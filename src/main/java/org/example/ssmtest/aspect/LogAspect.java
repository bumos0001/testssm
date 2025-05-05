package org.example.ssmtest.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import java.util.Arrays;

@Aspect
@Component
public class LogAspect {

    /*
     * 定義切點：
     *   @Before: 方法執行前
     *   @AfterReturning: 返回之前
     *   @AfterThrowing: 方法拋出異常時
     *   @After: 方法執行後（不管是正常結束還是拋出異常）
     */
    @Pointcut("execution(* org.example.ssmtest.service.ComputeImpl.*(..))")
    public void allMethod(){}

    // 這個方法會在目標方法執行之前被調用
    @Before("allMethod()")
    public void logStart(JoinPoint joinPoint) {
        System.out.println("AOP [日誌] 開始...");
        // 獲取方法名稱和參數
        System.out.println("執行方法: " + joinPoint.getSignature().getName());
        System.out.println("參數: " + Arrays.toString(joinPoint.getArgs()));
    }

    // 這個方法會在目標方法執行後（正常返回）被調用
    @After("allMethod()")
    public void logEnd(JoinPoint joinPoint) {
        System.out.println("AOP [日誌] 結束...");
        System.out.println("執行方法: " + joinPoint.getSignature().getName());
    }

    // 這個方法會在目標方法執行後（正常返回）被調用，並且可以取得返回值
    @AfterReturning(pointcut = "allMethod()", returning = "result")
    public void logReturn(Object result) {
        System.out.println("AOP [日誌] 返回值: " + result);
    }

    // 這個方法會在目標方法拋出異常時被調用，並且可以取得異常
    @AfterThrowing(pointcut = "allMethod()", throwing = "ex")
    public void logThrowing(JoinPoint joinPoint, Throwable ex) {
        // 獲取方法名稱
        String methodName = joinPoint.getSignature().getName();

        // 輸出方法名稱、參數及異常訊息
        System.out.println("AOP [日誌] 拋異常於方法: " + methodName);
        System.out.println("異常訊息: " + ex.getMessage());


        System.out.println("AOP [日誌] 拋異常: " + ex.getMessage());
        throw new RuntimeException("AOP : "+methodName+" 方法 出現異常" + ex.getMessage(),ex);
    }
}
