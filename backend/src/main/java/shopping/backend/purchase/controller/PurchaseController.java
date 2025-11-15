package shopping.backend.purchase.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import shopping.backend.purchase.dto.PurchaseRequest;
import shopping.backend.purchase.dto.PurchaseResponse;
import shopping.backend.purchase.service.PurchaseService;

@Controller
@RequestMapping("/purchase")
public class PurchaseController {
    private final PurchaseService purchaseService;

    public PurchaseController(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }

    @PostMapping
    public ResponseEntity<PurchaseResponse> purchase(@RequestBody PurchaseRequest purchaseRequest) {
        return ResponseEntity.ok(purchaseService.purchase(purchaseRequest));
    }
}
