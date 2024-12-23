import { CommonModule } from "@angular/common";
import { Component } from "@angular/core";
import { Router } from "@angular/router";
import { Customer } from "../../model/customer.model";

@Component({
  selector: "app-header",
  imports: [CommonModule],
  templateUrl: "./header.component.html",
  styleUrls: ["./header.component.css"],
})
export class HeaderComponent {
  userName!: string | null;
  isAdmin: boolean = false;
  customerObj!: Customer | null;

  constructor(private router: Router) {}

  ngOnInit(): void {
    if (typeof window !== "undefined") {
      const customer = localStorage.getItem("customer");
      if (customer) {
        this.customerObj = JSON.parse(customer);
        this.userName = this.customerObj?.customerName || null;
        this.isAdmin = this.customerObj?.isAdmin || false;
      }
    }
  }

  goLogin(): void {
    if (!this.customerObj) {
      this.router.navigate(["/login"]);
    }
  }

  goCart(): void {
    if (!this.customerObj) {
      this.router.navigate(["/login"]);
    } else {
      this.router.navigate(["/cart"]);
    }
  }

  goOrders(): void {
    if (!this.customerObj) {
      this.router.navigate(["/login"]);
    } else {
      this.router.navigate(["/orders"]);
    }
  }

  goHome(): void {
    if (this.isAdmin) {
      this.router.navigate(["/admin-dashboard"]);
    } else {
      this.router.navigate([""]);
    }
  }

  goProfile(): void {
    if (!this.customerObj) {
      this.router.navigate(["/login"]);
    } else {
      this.router.navigate(["/userProfile"]);
    }
  }

  logout(): void {
    localStorage.removeItem("customer"); // Clears session data
    this.userName = null; // Clears the user info
    this.router.navigate(["/login"]); // Redirects to login page
  }
}
