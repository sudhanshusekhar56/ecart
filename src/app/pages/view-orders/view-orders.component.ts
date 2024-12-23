import { Component, ViewChild } from "@angular/core";
import { Order } from "../../model/order.model";
import { OrderService } from "../../service/order-service.service";
import { CommonModule } from "@angular/common";
import { FormsModule } from "@angular/forms";
import { PopupComponent } from "../../components/popup/popup.component";
import { GoBackComponent } from "../../components/go-back/go-back.component";

@Component({
  selector: "app-view-orders",
  imports: [CommonModule, FormsModule, PopupComponent, GoBackComponent],
  templateUrl: "./view-orders.component.html",
  styleUrl: "./view-orders.component.css",
})
export class ViewOrdersComponent {
  orders: Order[] = [];

  constructor(private orderService: OrderService) {}
  @ViewChild("popup") popup!: PopupComponent;

  ngOnInit(): void {
    this.orderService.getOrders().subscribe({
      next: (data: Order[]) => {
        this.orders = data;
      },
      error: (error: any) => {
        console.error("Error fetching orders:", error);
      },
    });
  }

  // Method to update the status of an order
  updateOrderStatus(orderId: number, newStatus: string): void {
    console.log(orderId);
    this.orderService.updateOrderStatus(orderId, newStatus).subscribe({
      next: () => {
        this.popup.showPopup("Order status updated successfully");
        this.ngOnInit();
      },
      error: (error: any) => {
        console.error("Error updating order status:", error);
        this.popup.showPopup("Error updating order status");
      },
    });
  }
}
