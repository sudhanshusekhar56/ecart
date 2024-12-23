import { Component, OnInit, ViewChild } from "@angular/core";
import { FormsModule } from "@angular/forms";
import { ActivatedRoute, Router } from "@angular/router";
import { Order } from "../../model/order.model";
import { OrderService } from "../../service/order-service.service";
import { GoBackComponent } from "../../components/go-back/go-back.component";
import { CommonModule } from "@angular/common";
import { PopupComponent } from "../../components/popup/popup.component";

@Component({
  selector: "app-payments",
  imports: [FormsModule, CommonModule, GoBackComponent, PopupComponent],
  templateUrl: "./payments.component.html",
  styleUrl: "./payments.component.css",
})
export class PaymentsComponent implements OnInit {
  paymentDetails = {
    cardNumber: "",
    cardHolderName: "",
    expiryDate: "",
    cvv: "",
  };

  customerId: number = 0;
  order: Order | null = null;
  newOrder!: Order;
  address: string = "";
  expiryError: boolean = false;

  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private orderService: OrderService
  ) {}

  @ViewChild("popup") popup!: PopupComponent;

  ngOnInit(): void {
    this.route.queryParams.subscribe((params) => {
      this.customerId = +params["customerId"] || 0;
      this.address = params["address"] || "";
      if (params["order"]) {
        try {
          this.order = JSON.parse(params["order"]);
        } catch (error) {
          console.error("Error parsing order:", error);
        }
      }
      if (params["newOrder"]) {
        try {
          this.newOrder = JSON.parse(params["newOrder"]);
          console.log("payments init" + this.newOrder);
        } catch (error) {
          console.error("Error parsing newOrder:", error);
        }
      }
    });
  }

  validateExpiryDate(): void {
    const today = new Date();
    const [year, month] = this.paymentDetails.expiryDate.split("-").map(Number);

    if (!year || !month) {
      this.expiryError = true;
      return;
    }

    const expiryDate = new Date(year, month - 1);
    this.expiryError = expiryDate < today;
  }

  submitPayment(): void {
    if (this.expiryError) {
      this.popup.showPopup("Please enter a valid expiry date.");
      return;
    }

    if (!this.customerId) {
      alert("Missing customer details. Please try again.");
      return;
    }

    if (this.newOrder) {
      console.log("submit payment" + this.newOrder);
      this.orderService
        .createNewOrder(this.newOrder, this.customerId)
        .subscribe({
          next: (response: any) => {
            console.log("New Order Created:", response);
            this.popup.showPopup(
              "New order with " +
                response.orderId +
                " has been successfully created."
            );
            this.router.navigate(["/receipt"], {
              state: {
                order: response,
                productName: response.productName,
                productCategory: response.productCategory,
                productPrice: response.productPrice,
              },
            });
          },
          error: (error: any) => {
            this.popup.showPopup(
              "Failed to create new order. Please try again."
            );
            console.error("Error creating new order:", error);
          },
        });
      return;
    }

    if (this.order) {
      this.orderService
        .buyNow(this.order, this.customerId, this.address)
        .subscribe({
          next: (response: any) => {
            console.log(response);
            this.router.navigate(["/receipt"], {
              state: {
                order: response,
                productName: this.order?.productName,
                productCategory: this.order?.productCategory,
                productPrice: this.order?.productPrice,
              },
            });
          },
          error: (error: any) => {
            this.popup.showPopup("Payment failed. Please try again.");
            console.error("Payment error:", error);
          },
        });
    }
  }
}
