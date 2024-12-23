import { CommonModule } from "@angular/common";
import { Component, OnInit, ViewChild } from "@angular/core";
import { Order } from "../../model/order.model";
import { OrderService } from "../../service/order-service.service";
import { ActivatedRoute } from "@angular/router";
import { GoBackComponent } from "../../components/go-back/go-back.component";
import { PopupComponent } from "../../components/popup/popup.component";

@Component({
  selector: "app-order",
  imports: [CommonModule, GoBackComponent, PopupComponent],
  templateUrl: "./order.component.html",
  styleUrl: "./order.component.css",
})
export class OrderComponent implements OnInit {
  orders: Order[] = [];
  customerId: number = 0;

  constructor(
    private orderService: OrderService,
    private route: ActivatedRoute
  ) {}

  @ViewChild("popup") popup!: PopupComponent;

  ngOnInit(): void {
    let customer = localStorage.getItem("customer");
    if (customer) {
      let customerObj = JSON.parse(customer);
      this.customerId = customerObj.customerId;
    }
    this.loadOrders();
  }

  // Calculate total price for an order
  calculateTotalPrice(productPrice: number, orderQty: number): number {
    return productPrice * orderQty;
  }

  loadOrders(): void {
    this.orderService.getOrdersbyCustomerId(this.customerId).subscribe({
      next: (response) => {
        this.orders = response;
      },
      error: (error) => {
        console.error("Error fetching orders:", error);
      },
    });
  }

  cancelOrder(orderId: number): void {
    if (confirm("Are you sure you want to cancel this order?")) {
      this.orderService.cancelOrder(orderId, this.customerId).subscribe({
        next: () => {
          this.popup.showPopup("Order cancelled successfully.");
          this.loadOrders(); // Reload orders after cancellation
        },
        error: (error) => {
          this.popup.showPopup("Failed to cancel the order. Please try again.");
          console.error("Error cancelling order:", error);
        },
      });
    }
  }
}
