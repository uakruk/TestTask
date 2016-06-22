import com.canyoncapital.PaymentWorker;
import com.canyoncapital.util.Currency;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Yaroslav Kruk on 6/22/16.
 *         e-mail: yakruck@gmail.com
 *         GitHub: https://github.com/uakruk
 * @version 1.0
 * @since 1.7
 */
public class Main {
    public static void main(String[] args) {
        PaymentWorker worker = new PaymentWorker("sk_test_Gs3rdwfHS7q792XiWtoennrx");
        worker.doTask();
    }
}
