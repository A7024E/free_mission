package shopping.backend.purchase.dto;

import java.util.List;

public record CartPurchaseAllRequest(String memberId, List<PurchaseItem> items) {
}
