// order.service.ts
import { Injectable } from "@angular/core";
import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Observable } from "rxjs";
import { Order } from "../model/order.model";

@Injectable({
  providedIn: "root",
})
export class OrderService {
  private apiUrl = "http://localhost:8080/api/admin/orders";

  constructor(private http: HttpClient) {}

  getOrders(): Observable<Order[]> {
    return this.http.get<Order[]>(this.apiUrl);
  }

  getOrdersbyCustomerId(customerId: number): Observable<Order[]> {
    return this.http.get<Order[]>(
      `http://localhost:8080/api/customer/orders/${customerId}`
    );
  }

  updateOrderStatus(orderId: number, newStatus: string): Observable<any> {
    const updateUrl = `http://localhost:8080/api/admin/orders/${orderId}/${newStatus}`;
    return this.http.put<any>(updateUrl, {});
  }

  addCart(requestBody: any): Observable<any> {
    const apiUrl = "http://localhost:8080/api/customer/addCart";
    return this.http.post<any>(apiUrl, requestBody);
  }

  buyNow(order: Order, customerId: number, address: string): Observable<any> {
    order.orderAddress = address;
    const apiUrl = `http://localhost:8080/api/customer/buyItem/${customerId}`;
    return this.http.put<Order>(apiUrl, order);
  }

  cancelOrder(orderId: number, customerId: number): Observable<any> {
    const url = `http://localhost:8080/api/customer/orders/${orderId}/${customerId}`;
    return this.http.delete<any>(url);
  }

  createNewOrder(newOrder: Order, customerId: number): Observable<any> {
    return this.http.post<any>(
      `http://localhost:8080/api/customer/buyNewItem`,
      newOrder
    );
  }
}
