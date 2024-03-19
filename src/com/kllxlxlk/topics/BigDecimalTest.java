package com.kllxlxlk.topics;

import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class BigDecimalTest {

    /**
     * 解决浮点数计算的误差
     */
    @Test
    public void test() {
        System.out.println("0.01 + 0.05 = " + 0.01 + 0.05);

        BigDecimal result = BigDecimal.valueOf(0.05).add(BigDecimal.valueOf(0.01));
        System.out.println("0.01 + 0.05 = " + result);
    }

    /**
     * 使用 valueOf 方法新建
     */
    @Test
    public void test01() {
        // 314, 0
        BigDecimal a = BigDecimal.valueOf(314);
        System.out.println("==========" + " BigDecimal.valueOf(314) " + "=========");
        System.out.println("值: " + a.toPlainString());
        System.out.println("小数位数: " + a.scale());

        // 3.14, 2
        BigDecimal b = BigDecimal.valueOf(314, 2);
        System.out.println("==========" + " BigDecimal.valueOf(314, 2) " + "=========");
        System.out.println("值: " + b.toPlainString());
        System.out.println("小数位数: " + b.scale());

        // 0.00314, 5
        BigDecimal c = BigDecimal.valueOf(314, 5);
        System.out.println("==========" + " BigDecimal.valueOf(314, 5) " + "=========");
        System.out.println("值: " + c.toPlainString());
        System.out.println("小数位数: " + c.scale());

        // 0.31400, 5
        BigDecimal d = BigDecimal.valueOf(31400, 5);
        System.out.println("==========" + " BigDecimal.valueOf(31400, 5) " + "=========");
        System.out.println("值: " + d.toPlainString());
        System.out.println("小数位数: " + d.scale());
    }

    /**
     * 使用构造函数新建
     */
    @Test
    public void test02() {
        // 314, 0
        BigDecimal a = new BigDecimal(314);
        System.out.println("==========" + " new BigDecimal(314) " + "=========");
        System.out.println("值: " + a.toPlainString());
        System.out.println("小数位数: " + a.scale());

        // 314, 0
        BigDecimal b = new BigDecimal("314");
        System.out.println("==========" + " new BigDecimal(\"314\") " + "=========");
        System.out.println("值: " + b.toPlainString());
        System.out.println("小数位数: " + b.scale());

        // 3.14, 2
        BigDecimal c = new BigDecimal("3.14");
        System.out.println("==========" + " new BigDecimal(\"3.14\") " + "=========");
        System.out.println("值: " + c.toPlainString());
        System.out.println("小数位数: " + c.scale());

        // 3.140, 3
        BigDecimal d = new BigDecimal("3.140");
        System.out.println("==========" + " new BigDecimal(\"3.140\") " + "=========");
        System.out.println("值: " + d.toPlainString());
        System.out.println("小数位数: " + d.scale());
    }

    /**
     * 加减法的精度，最终结果位数等于两个加数中较大的那个
     */
    @Test
    public void test03() {
        // 5 位小数，0.01020
        BigDecimal a = new BigDecimal("0.01");
        BigDecimal b = new BigDecimal("0.00020");
        BigDecimal c = a.add(b);

        System.out.println("==========" + " 0.01 + 0.00020 " + "=========");
        System.out.println("值: " + c.toPlainString());
        System.out.println("小数位数: " + c.scale());
    }

    /**
     * 乘法的位数，最终结果位数等于两个乘数的小数位数相加
     */
    @Test
    public void test04() {
        // 5 位小数，0.00500
        BigDecimal a = new BigDecimal("0.500");
        BigDecimal b = new BigDecimal("0.01");
        BigDecimal c = a.multiply(b);

        System.out.println("==========" + " 0.500 * 0.01 " + "=========");
        System.out.println("值: " + c.toPlainString());
        System.out.println("小数位数: " + c.scale());
    }

    /**
     * 除法的位数，不指定精度和舍入模式
     */
    @Test
    public void test05() {
        // 如果除不尽会报错
        try {
            BigDecimal a = BigDecimal.ONE.divide(BigDecimal.valueOf(3));
            System.out.println("==========" + " BigDecimal.ONE.divide(BigDecimal.valueOf(3)) " + "=========");
            System.out.println("值: " + a.toPlainString());
            System.out.println("小数位数: " + a.scale());
        } catch (ArithmeticException e) {
            System.out.println(e.getMessage());
        }

        // 如果能保证精度，结果小数位数 = 被除数的小数位数 - 除数的小数位数
        BigDecimal b = new BigDecimal("3.14000");
        BigDecimal c = new BigDecimal("0.2");
        BigDecimal d = b.divide(c);
        System.out.println("==========" + " 3.14000 / 0.2 " + "=========");
        System.out.println("值: " + d.toPlainString());
        System.out.println("小数位数: " + d.scale());

        // 如果影响精度，结果小数位数等于维持精度的最小位数
        BigDecimal e = new BigDecimal("1.11");
        BigDecimal f = new BigDecimal("0.2");
        BigDecimal g = e.divide(f);
        System.out.println("==========" + " 1.11 / 0.2 " + "=========");
        System.out.println("值: " + g.toPlainString());
        System.out.println("小数位数: " + g.scale());
    }

    /**
     * 指定舍入模式和精度，最终等于指定的精度
     */
    @Test
    public void test06() {
        BigDecimal a = BigDecimal.ONE.divide(BigDecimal.valueOf(3), 4, RoundingMode.HALF_UP);
        System.out.println("==========" + " 1 / 3 " + "=========");
        System.out.println("值: " + a.toPlainString());
        System.out.println("小数位数: " + a.scale());

        BigDecimal b = new BigDecimal("3.14");
        BigDecimal c = new BigDecimal("2");
        BigDecimal d = b.divide(c, 0, RoundingMode.HALF_UP);
        System.out.println("==========" + " 3.14 / 2 " + "=========");
        System.out.println("值: " + d.toPlainString());
        System.out.println("小数位数: " + d.scale());
    }
}
