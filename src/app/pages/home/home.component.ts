import { Component, OnInit, ViewChild } from "@angular/core";
import { ProductService } from "../../service/product.service";
import { CommonModule } from "@angular/common";
import { Product } from "../../model/product.model";
import { OrderService } from "../../service/order-service.service";
import { Router } from "@angular/router";
import { Customer } from "../../model/customer.model";
import { OrderPopupComponent } from "../../components/order-popup/order-popup.component";
import { PopupComponent } from "../../components/popup/popup.component";

@Component({
  selector: "app-home",
  imports: [CommonModule, OrderPopupComponent, PopupComponent],
  templateUrl: "./home.component.html",
  styleUrl: "./home.component.css",
})
export class HomeComponent {
  //states for popup
  order!: any;
  isPopupVisible: boolean = false;
  selectedProduct: Product | null = null;

  customerObj!: Customer;

  @ViewChild("popup") popup!: PopupComponent;

  constructor(
    private productService: ProductService,
    private orderService: OrderService,
    private router: Router
  ) {}
  products: Product[] = [];
  addToCart(product: Product) {
    // Create request body
    const requestBody = {
      customerId: this.customerObj.customerId,
      productId: product.productId,
      orderQty: 1,
    };

    // Call the service method
    this.orderService.addCart(requestBody).subscribe({
      next: (response: any) => {
        console.log("Product added to cart successfully:", response);
        this.popup.showPopup("Product added to cart successfully!");
      },
      error: (error: any) => {
        console.error("Error adding product to cart:", error);
        this.popup.showPopup("Error adding to cart.");
      },
    });
  }

  buyNow(product: Product): void {
    this.selectedProduct = product;
    this.isPopupVisible = true;
  }

  handleConfirmOrder(orderDetails: { address: string; phone: string }): void {
    if (this.selectedProduct && this.customerObj) {
      this.order = {
        customerId: this.customerObj.customerId,
        productId: this.selectedProduct.productId,
        orderAddress: orderDetails.address,
        orderQty: 1,
      };

      this.router.navigate(["/payment"], {
        queryParams: {
          customerId: this.customerObj.customerId,
          newOrder: JSON.stringify(this.order),
          address: orderDetails.address,
        },
      });
    }
  }

  handleCancelOrder(): void {
    this.isPopupVisible = false;
    this.selectedProduct = null;
  }

  ngOnInit(): void {
    if (typeof localStorage !== "undefined") {
      const customer = localStorage.getItem("customer");
      if (customer) {
        this.customerObj = JSON.parse(customer);
      }
    }
    this.productService.getProducts().subscribe({
      next: (data: Product[]) => {
        this.products = data;
      },
      error: (error: any) => {
        console.error("Error fetching customers:", error);
      },
    });
  }
}
