import com.yj.testdemo.model.http.HttpCallback;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

public class MainTest {
    public static void main(String[] args) {
        CloseableHttpAsyncClient client = HttpAsyncClients.createDefault();
        client.start();
        HttpCallback callback = new HttpCallback("http://www.baidu.com");
        final CountDownLatch latch = new CountDownLatch(1);
        for(int i=0;i<100;i++) {
            client.execute(new HttpGet("http://www.baidu.com"), callback);
        }
        try {
            latch.await();
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}