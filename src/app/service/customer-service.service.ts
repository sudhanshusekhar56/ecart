import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";
import { Customer } from "../model/customer.model";

@Injectable({
  providedIn: "root",
})
export class CustomerService {
  private apiUrl = "http://localhost:8080/api/admin/customers";

  constructor(private http: HttpClient) {}

  getCustomers(): Observable<Customer[]> {
    return this.http.get<Customer[]>(this.apiUrl);
  }

  // Update the customer's profile
  updateProfile(customer: Customer): Observable<Customer> {
    const url = `http://localhost:8080/api/customer/updateProfile`;
    return this.http.put<Customer>(url, customer);
  }

  // Change the customer's password
  changePassword(
    customerId: number,
    oldPassword: string,
    newPassword: string
  ): Observable<any> {
    const url = `http://localhost:8080/api/customer/changePassword`;
    const body = { customerId, oldPassword, newPassword };
    return this.http.put<any>(url, body);
  }
}
