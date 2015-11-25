package util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpUtil {
	/**
	 * 就是客户端向服务器发出一条HTTP 请求，服务器收到请求之后会返回一些数据给客户端，
	 * 然后客户端再对这些数据进行解析和处理就可以了
	 * 使用到的WebView 控件，其实也就是我们向百度的服务器发起了一条HTTP 请求，
	 * 接着服务器分析出我们想要访问的是百度的首页，
	 * 于是会把该网页的HTML 代码进行返回，然后WebView 
	 * 再调用手机浏览器的内核对返回的HTML 代码进行解析，最终将页面展示出来。
	 * 
	 * 
	 * 
	 * 通常情况下我们都应该将这些通用的网络操作提取到一个公共的类里，
	 * 并提供一个静态方法，当想要发起网络请求的时候只需简单地调用一下这个方法即可
	 * 
	 * 网络请求通常都是属于耗时操作，而sendHttpRequest()方法的内部并没有开启线程，
	 * 这样就有可能导致在调用sendHttpRequest()方法的时候使得主线程被阻塞住。
	 * 
	 * 如果我们在sendHttpRequest()方法中开启了一个线程来发起HTTP 请求，
	 * 那么服务器响应的数据是无法进行返回的，所有的耗时逻辑都是
	 * 在子线程里进行的，sendHttpRequest()方法会在服务器还来得及响应的时候就执行结束了，
	 * 当然也就无法返回响应的数据了。
	 * 
	 * sendHttpRequest()方法添加了一个HttpCallbackListener 参数，
	 * 并在方法的内部开启了一个子线程，然后在子线程里去执行具体的网络操作。
	 * 注意子线程中是无法通过return 语句来返回数据的，
	 * 因此这里我们将服务器响应的数据传入了HttpCallbackListener 的onFinish()方法中，
	 * 如果出现了异常就将异常原因传入到onError()方法中
	 * 
	 * HttpUtil 类中使用到了HttpCallbackListener 接口来回调服务返回的结果
	 */
	public static void sendHttpRequest(final String address,final HttpCallbackListener listener) {
		//开启线程来发网络请求
		new Thread(new Runnable(){
			@Override
			public void run() {
				HttpURLConnection connection =null;
				try{
					//获取HttpURLConnection实例
					URL url= new URL(address);
					connection =(HttpURLConnection) url.openConnection();
					//GET 表示希望从服务器那里获取数据，而POST 则
					//表示希望提交数据给服务器
					connection.setRequestMethod("GET");
					//设置连接超时ms
					connection.setConnectTimeout(8000);
					//设置读取超时ms
					connection.setReadTimeout(8000);
					//调用getInputStream()方法就可以获取到服务器返回的输入流
					InputStream in =connection.getInputStream();
					//对获取额文件流进行读取
					BufferedReader reader =new BufferedReader(new InputStreamReader(in));
					StringBuilder response =new StringBuilder();
					String line;
					while((line=reader.readLine())!=null){
						response.append(line);
					}
					if(listener!=null){
						//回调onFinish()方法
						listener.onFinish(response.toString());
					}
				}catch (Exception e) {
					// TODO: handle exception
				if(listener!=null){
						//回调onError()方法
					listener.onError(e);
					}
				}finally{
					if(connection!=null){
						//调用disconnect()方法将这个HTTP 连接关闭
						connection.disconnect();
					}
				}
			}
		}).start();
	}
}
