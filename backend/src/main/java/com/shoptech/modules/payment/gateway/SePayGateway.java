package com.shoptech.modules.payment.gateway;

import com.shoptech.config.AppProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

/** Cổng SePay: dựng form POST (có chữ ký) để trình duyệt tự gửi sang trang thanh toán. */
@Component
@RequiredArgsConstructor
public class SePayGateway {

    private static final List<String> SIGNED_FIELDS = List.of(
            "order_amount", "merchant", "currency", "operation",
            "order_description", "order_invoice_number", "customer_id",
            "payment_method", "success_url", "error_url", "cancel_url");

    private final AppProperties props;

    public record CheckoutForm(String action, Map<String, String> fields) {
    }

    public CheckoutForm checkoutForm(String invoiceNumber, long amount, String description,
                                     String successUrl, String errorUrl, String cancelUrl) {
        var cfg = props.payment().sepay();
        Map<String, String> fields = new LinkedHashMap<>();
        fields.put("order_amount", String.valueOf(amount));
        fields.put("merchant", cfg.merchantId());
        fields.put("currency", "VND");
        fields.put("operation", "PURCHASE");
        fields.put("order_description", description);
        fields.put("order_invoice_number", invoiceNumber);
        fields.put("success_url", successUrl);
        fields.put("error_url", errorUrl);
        fields.put("cancel_url", cancelUrl);

        StringJoiner signed = new StringJoiner(",");
        for (String f : SIGNED_FIELDS) {
            if (fields.containsKey(f)) {
                signed.add(f + "=" + fields.get(f));
            }
        }
        fields.put("signature", Base64.getEncoder().encodeToString(
                Signatures.hmac("HmacSHA256", cfg.secretKey().getBytes(java.nio.charset.StandardCharsets.UTF_8),
                        signed.toString())));
        return new CheckoutForm(cfg.checkoutUrl(), fields);
    }
}
