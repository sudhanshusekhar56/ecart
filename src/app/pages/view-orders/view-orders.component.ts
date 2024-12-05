import { Component } from '@angular/core';
import { Order } from '../../model/order.model';
import { OrderService } from '../../service/order-service.service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-view-orders',
  imports: [CommonModule, FormsModule],
  templateUrl: './view-orders.component.html',
  styleUrl: './view-orders.component.css',
})
export class ViewOrdersComponent {
  orders: Order[] = [];

  constructor(private orderService: OrderService) {}

  ngOnInit(): void {
    this.orderService.getOrders().subscribe({
      next: (data: Order[]) => {
        this.orders = data;
      },
      error: (error: any) => {
        console.error('Error fetching orders:', error);
      },
    });
  }

  // Method to update the status of an order
  updateOrderStatus(orderId: number, newStatus: string): void {
    this.orderService.updateOrderStatus(orderId, newStatus).subscribe({
      next: () => {
        alert('Order status updated successfully');
        // Refetch the orders to reflect the updated status
        this.ngOnInit();
      },
      error: (error: any) => {
        console.error('Error updating order status:', error);
        alert('Error updating order status');
      },
    });
  }
}
