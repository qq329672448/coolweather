package util;
/**
 * 使用Java 的回调机制
 * onFinish()方法表示当服务器成功响应我们请求的时候调用，
 * onError()表示当进行网络操作出现错误的时候调用。
 * 这两个方法都带有参数，onFinish()方法中的参数代表着服务器返回的数据，
 * 而onError()方法中的参数记录着错误的详细信息。
 * 
 */
public interface HttpCallbackListener {
	void onFinish(String response);
	void onError(Exception e);
}
