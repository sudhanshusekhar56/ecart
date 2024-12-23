import { Component, ViewChild } from "@angular/core";
import { Order } from "../../model/order.model";
import { CartServiceService } from "../../service/cart-service.service";
import { CommonModule } from "@angular/common";
import { FormsModule } from "@angular/forms";
import { Router } from "@angular/router";
import { OrderPopupComponent } from "../../components/order-popup/order-popup.component";
import { GoBackComponent } from "../../components/go-back/go-back.component";
import { PopupComponent } from "../../components/popup/popup.component";

@Component({
  selector: "app-cart",
  imports: [
    CommonModule,
    FormsModule,
    OrderPopupComponent,
    GoBackComponent,
    GoBackComponent,
    PopupComponent,
  ],
  templateUrl: "./cart.component.html",
  styleUrl: "./cart.component.css",
})
export class CartComponent {
  cartItems: Order[] = [];
  customerId: number = 0;
  quantity: number = 0;
  isCheckoutPopupVisible: boolean = false;
  selectedProduct: Order | null = null;

  constructor(
    private cartService: CartServiceService,
    private router: Router
  ) {}

  @ViewChild("popup") popup!: PopupComponent;

  ngOnInit(): void {
    if (typeof window !== "undefined" && window.localStorage) {
      const customer = JSON.parse(localStorage.getItem("customer") || "{}");
      this.customerId = customer?.customerId || 0;
      this.fetchCartItems();
    } else {
      console.warn("localStorage is not available.");
      this.customerId = 0;
    }
  }

  fetchCartItems(): void {
    this.cartService.getCartItems(this.customerId).subscribe({
      next: (items: Order[]) => {
        this.cartItems = items;
      },
      error: (error: any) => {
        console.error("Error fetching cart items:", error);
      },
    });
  }

  // Update the item quantity in the cart
  updateOrderQty(orderId: number, quantityInput: HTMLInputElement): void {
    const updatedQty = parseInt(quantityInput.value, 10);

    if (!updatedQty || updatedQty < 1) {
      this.popup.showPopup("Please enter a valid quantity greater than 0");
      return;
    }

    this.cartService.updateCartItem(orderId, updatedQty).subscribe({
      next: (response: any) => {
        this.popup.showPopup(response.message);
        this.fetchCartItems();
      },
      error: (error: any) => {
        this.popup.showPopup(
          "Failed to update item in cart. Please try again."
        ); // Show error message
        console.error("Error updating cart item:", error);
      },
    });
  }

  deleteCartItem(orderId: number): void {
    this.cartService.deleteCartItem(orderId, this.customerId).subscribe({
      next: () => {
        this.fetchCartItems();
        this.popup.showPopup("Cart item deleted successfully!");
      },
      error: (error: any) => {
        console.error("Error deleting cart item:", error);
      },
    });
  }

  openCheckoutPopup(item: Order): void {
    this.selectedProduct = item;
    this.isCheckoutPopupVisible = true;
  }

  closeCheckoutPopup(): void {
    this.isCheckoutPopupVisible = false;
  }

  onConfirmOrder(orderDetails: any): void {
    // Close the popup
    this.closeCheckoutPopup();

    // Navigate to the payment page
    this.router.navigate(["/payment"], {
      queryParams: {
        customerId: this.customerId,
        order: JSON.stringify(this.selectedProduct),
        address: orderDetails.address,
      },
    });
  }
}
