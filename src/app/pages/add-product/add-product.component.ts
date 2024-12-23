import { Component, ViewChild } from "@angular/core";
import { ProductService } from "../../service/product.service";
import { FormsModule } from "@angular/forms";
import { CommonModule } from "@angular/common";
import { GoBackComponent } from "../../components/go-back/go-back.component";
import { PopupComponent } from "../../components/popup/popup.component";

@Component({
  selector: "app-add-product",
  imports: [FormsModule, CommonModule, GoBackComponent, PopupComponent],
  templateUrl: "./add-product.component.html",
  styleUrl: "./add-product.component.css",
})
export class AddProductComponent {
  product = {
    productName: "",
    productPrice: null, // Changed from 0 to null
    productCategory: "",
    productDescription: "",
  };

  successMessage: string | null = null;
  errorMessage: string | null = null;

  constructor(private productService: ProductService) {}
  @ViewChild("popup") popup!: PopupComponent;

  addProduct(): void {
    // Ensure productPrice is valid
    if (!this.product.productPrice || this.product.productPrice <= 0) {
      this.popup.showPopup("Please enter a valid product price.");
      return;
    }

    this.productService.addProduct(this.product).subscribe({
      next: () => {
        this.popup.showPopup("Product added successfully!");
        this.errorMessage = null;
        this.resetForm();
      },
      error: (err: string) => {
        this.successMessage = null;
        this.popup.showPopup("Failed to add product. Please try again.");
        console.error(err);
      },
    });
  }

  clearIfZero(): void {
    if (this.product.productPrice === 0) {
      this.product.productPrice = null; // Clear field when 0 is entered
    }
  }

  resetForm(): void {
    this.product = {
      productName: "",
      productPrice: null, // Reset to null so the field is blank
      productCategory: "",
      productDescription: "",
    };
  }
}
