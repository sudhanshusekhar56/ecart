import { Component, Input, OnInit } from "@angular/core";
import { Order } from "../../model/order.model";
import { Router } from "@angular/router";
import { GoBackComponent } from "../../components/go-back/go-back.component";

@Component({
  selector: "app-receipt",
  imports: [GoBackComponent],
  templateUrl: "./receipt.component.html",
  styleUrl: "./receipt.component.css",
})
export class ReceiptComponent implements OnInit {
  order!: Order;
  productName!: string;
  productCategory!: string;
  productPrice!: number;

  constructor(private router: Router) {
    const navigation = this.router.getCurrentNavigation();
    this.order = navigation?.extras.state?.["order"];
    this.productName = navigation?.extras.state?.["productName"];
    this.productCategory = navigation?.extras.state?.["productCategory"];
    this.productPrice = navigation?.extras.state?.["productPrice"];
  }

  ngOnInit(): void {
    if (!this.order) {
      console.error("Order data is missing!");
    }
  }
}
