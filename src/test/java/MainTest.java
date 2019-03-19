import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.IOException;

public class MainTest {
    public static void main(String[] args) throws InterruptedException {
        int i = 50;
        Observable.create(
                observableEmitter -> {
                    for (int i1 = 1; i1 <= i; i1++) {
                        http(i1);
                        if(i1%5==0){
                            observableEmitter.onNext(i1);
                        }
                        if (i1%21==0){
                            observableEmitter.onError(new Throwable("error"));
                            return;
                        }
                    }
                }
        )
                .subscribeOn(Schedulers.io())
                .subscribe(System.out::println, Throwable::printStackTrace);
        Thread.sleep(1000*100);
    }

    public static void http(Integer count) throws IOException {
        System.out.println("doing:"+count);
        CloseableHttpClient c = HttpClientBuilder.create().build();
        HttpGet get = new HttpGet("http://www.pydyun.com");
        CloseableHttpResponse r = c.execute(get);
        System.out.println(r.getStatusLine().getStatusCode());
        c.close();
    }
}