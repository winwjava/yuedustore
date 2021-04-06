package cn.itcast.itcaststore.utils;

import java.util.UUID;
/**
 * 生成注册激活码的工具类
 * 提供激活参数
 */
public class ActiveCodeUtils {
	public static String createActiveCode() {
		return UUID.randomUUID().toString();
	}
}
